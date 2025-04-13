<template>
  <div class="map-container">
    <h2>Localisation des orchidées</h2>
    <div id="mapcarto" class="map-container"></div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import axios from "axios"; // Importer Axios
import L from "leaflet";
import "leaflet/dist/leaflet.css";

const orchidées = ref([]); // Stocker les données récupérées (toutes les orchidées)

// 🔍 Fonction pour récupérer les données depuis l'API (toutes les orchidées)
const fetchOrchidees = async () => {
  try {
    const response = await axios.get("api/orchidees"); // Assurez-vous que cette URL est correcte
    orchidées.value = response.data;
    console.log("Données récupérées :", orchidées.value);
    afficheCarto(); // Appeler la fonction pour afficher la carte avec les données
  } catch (error) {
    console.error("Erreur lors de la récupération des données :", error);
  }
};

// 🌍 Fonction pour afficher la carte avec les coordonnées récupérées
function afficheCarto() {
  if (orchidées.value.length === 0) {
    console.warn("Aucune donnée d'orchidée disponible !");
    return;
  }

  // Initialisation de la carte au premier endroit trouvé (de préférence)
  const {latitude, longitude} = orchidées.value[0];
  let mapcarto = L.map("mapcarto").setView([latitude, longitude], 14);

  // Ajouter un fond de carte (OpenStreetMap)
  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: "© OpenStreetMap contributors",
  }).addTo(mapcarto);

  // Variables pour déterminer les bornes géographiques
  let minLat = latitude, maxLat = latitude;
  let minLon = longitude, maxLon = longitude;

  // Ajouter un marqueur pour chaque orchidée
  orchidées.value.forEach(orchidee => {
    const {latitude, longitude} = orchidee;

    // Ajouter un marqueur pour l'orchidée
    L.marker([latitude, longitude]).addTo(mapcarto).bindPopup(`Orchidée ${orchidee.id}`);

    // Mise à jour des bornes géographiques
    if (latitude < minLat) minLat = latitude;
    if (latitude > maxLat) maxLat = latitude;
    if (longitude < minLon) minLon = longitude;
    if (longitude > maxLon) maxLon = longitude;
  });

  // Calculer les coordonnées du centre du rectangle qui couvre toutes les orchidées
  const centerLat = (minLat + maxLat) / 2;
  const centerLon = (minLon + maxLon) / 2;

  // Ajouter un cercle bleu autour de toutes les orchidées
  const radius = Math.max(maxLat - minLat, maxLon - minLon) * 100000; // Rayon approximatif
  L.circle([centerLat, centerLon], {
    color: "blue",
    fillOpacity: 0.1,
    radius: radius,
  }).addTo(mapcarto);
}

// 🔥 Exécuter la récupération des données et l'affichage au montage du composant
onMounted(fetchOrchidees);
</script>

<style scoped>
.map-container {
  width: 100%;
  height: 50vh;
  padding: 1rem;
  box-sizing: border-box;
}


</style>
