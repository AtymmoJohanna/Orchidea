<template>
  <div class="stat-container">
    <h2>Statistiques des orchidées par forme</h2>
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

const series = ref([]);
const options = ref({
  chart: {
    type: "bar",
    height: 400,
  },
  plotOptions: {
    bar: {
      horizontal: false,
      columnWidth: "55%",
    },
  },
  xaxis: {
    categories: [],
    title: {
      text: "Forme",
      style: {
        fontSize: "14px",
        fontWeight: "bold",
      },
    },
    labels: {
      rotate: -45,
      style: {
        fontSize: "12px",
      },
    },
  },
  yaxis: {
    title: {
      text: "Nombre d'orchidées",
      style: {
        fontSize: "14px",
        fontWeight: "bold",
      },
    },
    labels: {
      style: {
        fontSize: "12px",
      },
    },
  },
  dataLabels: {
    enabled: true,
    style: {
      fontSize: "12px",
      colors: ["#000"],
    },
  },
  colors: ["#28a745"],
  fill: {
    opacity: 0.8,
  },
  tooltip: {
    y: {
      formatter: (val) => `${val} orchidée${val > 1 ? "s" : ""}`,
    },
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

    // Comptage par forme
    const countByForme = orchidées.value.reduce((acc, orchidee) => {
      const forme = orchidee.forme || "Inconnu"; // Gérer les formes null/undefined
      acc[forme] = (acc[forme] || 0) + 1;
      return acc;
    }, {});

    console.log("Comptage par forme : ", countByForme);

    // Mettre à jour les séries et les catégories
    const formes = Object.keys(countByForme); // Formes pour l'axe X
    const counts = Object.values(countByForme); // Nombres pour l'axe Y

    console.log("Formes (axe X) : ", formes);
    console.log("Comptages (axe Y) : ", counts);

    // Mettre à jour les données du graphique
    series.value = [
      {
        name: "Nombre d'Orchidées",
        data: counts,
      },
    ];

    // Mettre à jour les catégories de l'axe X de manière réactive
    options.value = {
      ...options.value,
      xaxis: {
        ...options.value.xaxis,
        categories: formes,
      },
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
.stat-container {
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
.stat-container h2 {
  text-align: center;
  margin-bottom: 20px;
}

.error-message {
  color: red;
  margin-bottom: 10px;
}
</style>
