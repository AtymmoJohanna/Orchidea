<template>
  <div class="map-container">
    <h2>Localisation de l'orchidée</h2>
    <div id="mapcarto" class="map-container"></div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import axios from "axios"; // Importer Axios
import L from "leaflet";
import "leaflet/dist/leaflet.css";

const orchidee = ref(null); // Stocker les données récupérées

// 🔍 Fonction pour récupérer les données depuis l'API
const fetchOrchidee = async () => {
  try {
    const response = await axios.get("api/orchidees/1");
    orchidee.value = response.data;
    console.log("Données récupérées :", orchidee.value);
    afficheCarto(); // Appeler la fonction pour afficher la carte avec les données
  } catch (error) {
    console.error("Erreur lors de la récupération des données :", error);
  }
};

// 🌍 Fonction pour afficher la carte avec les coordonnées récupérées
function afficheCarto() {
  if (!orchidee.value) {
    console.warn("Aucune donnée d'orchidée disponible !");
    return;
  }

  const { latitude, longitude } = orchidee.value;

  let mapcarto = L.map("mapcarto").setView([latitude, longitude], 14);

  // Ajouter un fond de carte (OpenStreetMap)
  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: "© OpenStreetMap contributors",
  }).addTo(mapcarto);

  // Ajouter un marqueur pour l'orchidée
  L.marker([latitude, longitude]).addTo(mapcarto).bindPopup("Orchidée");

  // Ajouter un cercle autour de l'orchidée
  L.circle([latitude, longitude], {
    color: "blue",
    fillOpacity: 0.1,
    radius: 2000,
  }).addTo(mapcarto);
}

// 🔥 Exécuter la récupération des données et l'affichage au montage du composant
onMounted(fetchOrchidee);
</script>

<style scoped>
.map-container {
  width: 80%;
  height: 700px;
  margin: 20px;
}
</style>
