<template>
  <div class="stat-container">
    <h2>Statistiques des orchidées par état</h2>
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
    <div>
      <apexchart width="100%" :options="options" :series="series"></apexchart>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import axios from "axios";
import apexchart from "vue3-apexcharts";

const series = ref([]);
const options = ref({
  chart: {
    type: "bar",
    height: 400, // Augmenter la hauteur pour une meilleure lisibilité
  },
  plotOptions: {
    bar: {
      horizontal: false,
      columnWidth: "55%", // Ajuster la largeur des barres
    },
  },
  xaxis: {
    categories: [],
    title: {
      text: "État des orchidées",
      style: {
        fontSize: "14px",
        fontWeight: "bold",
      },
    },
    labels: {
      rotate: -45, // Rotation des étiquettes pour éviter le chevauchement
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
  colors: ["#28a745"], // Couleur verte pour les barres
  fill: {
    opacity: 0.8,
  },
  tooltip: {
    y: {
      formatter: (val) => `${val} orchidée${val > 1 ? "s" : ""}`, // Personnaliser l'infobulle
    },
  },
});

const orchidées = ref([]);
const errorMessage = ref(null); // Ajouter une variable pour les messages d'erreur

// 🔍 Fonction pour récupérer les données depuis l'API (toutes les orchidées)
const fetchOrchidees = async () => {
  try {
    const response = await axios.get("/api/orchidees"); // URL relative avec proxy Vite
    orchidées.value = response.data;
    console.log("Orchidées récupérées :", orchidées.value);

    if (!orchidées.value || orchidées.value.length === 0) {
      console.warn("Aucune donnée d'orchidée disponible !");
      errorMessage.value = "Aucune orchidée trouvée.";
      return;
    }

    // Comptage par état
    const countByState = orchidées.value.reduce((acc, orchidee) => {
      const state = orchidee.etat || "Inconnu"; // Gérer les états null/undefined
      acc[state] = (acc[state] || 0) + 1;
      return acc;
    }, {});

    console.log("Comptage par état : ", countByState);

    // Mettre à jour les séries et les catégories
    const states = Object.keys(countByState); // États pour l'axe X
    const counts = Object.values(countByState); // Nombres pour l'axe Y

    console.log("États (axe X) : ", states);
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
        categories: states,
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
  height: 100vh;
  width: 100%;
  max-width: 800px; /* Limite la largeur maximale pour une meilleure lisibilité */
  margin: 0 auto;
}

.error-message {
  color: red;
  margin-bottom: 10px;
}
</style>
