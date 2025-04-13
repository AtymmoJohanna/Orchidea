<template>
  <div class="pie-container">
    <h2>Pourcentage des orchidées par couleur</h2>
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
    <div>
      <apexchart width="100%" :options="options" :series="series"></apexchart>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import axios from "axios";
import apexchart from "vue3-apexcharts";

// Variables réactives pour les données du graphique
const series = ref([]);
const options = ref({
  chart: {
    type: "pie",
    height: 400,
  },
  labels: [],
  colors: [], // Sera mis à jour dynamiquement
  responsive: [
    {
      breakpoint: 480,
      options: {
        chart: {
          width: 300,
        },
        legend: {
          position: "bottom",
        },
      },
    },
  ],
  dataLabels: {
    enabled: true,
    formatter: (val) => `${val.toFixed(1)}%`,
  },
  tooltip: {
    y: {
      formatter: (val, { series, seriesIndex, dataPointIndex, w }) => {
        const total = series.reduce((a, b) => a + b, 0);
        const percentage = (val / total) * 100;
        return `${percentage.toFixed(1)}% (${val} orchidée${val > 1 ? "s" : ""})`;
      },
    },
  },
  legend: {
    position: "right",
    fontSize: "14px",
  },
});

const orchidées = ref([]);
const errorMessage = ref(null);

// 🔍 Fonction pour récupérer les données depuis l'API (toutes les orchidées)
const fetchOrchidees = async () => {
  try {
    const response = await axios.get("/api/orchidees", {
      headers: {
        "Cache-Control": "no-cache",
      },
    });
    orchidées.value = response.data;
    console.log("Orchidées récupérées :", JSON.stringify(orchidées.value, null, 2));

    if (!orchidées.value || orchidées.value.length === 0) {
      console.warn("Aucune donnée d'orchidée disponible !");
      errorMessage.value = "Aucune orchidée trouvée.";
      return;
    }

    // Comptage par couleur (chaque orchidée peut avoir plusieurs couleurs)
    const countByColor = orchidées.value.reduce((acc, orchidee) => {
      const colors = orchidee.couleur || ["Inconnu"];
      colors.forEach((color) => {
        acc[color] = (acc[color] || 0) + 1;
      });
      return acc;
    }, {});

    console.log("Comptage par couleur : ", countByColor);

    // Mettre à jour les séries et les labels
    const colors = Object.keys(countByColor);
    const counts = Object.values(countByColor);

    console.log("Couleurs (labels) : ", colors);
    console.log("Comptages (séries) : ", counts);

    // Définir des couleurs spécifiques pour chaque label
    const colorMap = {
      Noir: "#000000",
      Vert: "#28a745",
      Rouge: "#ff0000",
      Jaune: "#ffff00",
      Rose: "#ff69b4",
      Violet: "#800080",
      Pourpre: "#4b0082",
      Inconnu: "#808080",
    };
    const chartColors = colors.map((color) => colorMap[color] || "#000000");

    // Mettre à jour les données du graphique
    series.value = counts;

    // Mettre à jour les options de manière réactive
    options.value = {
      ...options.value,
      labels: colors,
      colors: chartColors,
    };
  } catch (error) {
    console.error("Erreur lors de la récupération des orchidées :", error);
    errorMessage.value =
      "Impossible de charger les données des orchidées. Veuillez réessayer plus tard.";
  }
};

// 🔥 Exécuter la récupération des données au montage du composant
onMounted(fetchOrchidees);
</script>

<style scoped>
.pie-container {
  position: relative;
  padding-bottom: 60px;
  background-color: #dcead2;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  max-width: 800px;
  margin: 20px 0;
}
.pie-container h2 {
  text-align: center;
  margin-bottom: 20px;
}

.error-message {
  color: red;
  margin-bottom: 10px;
}
</style>
