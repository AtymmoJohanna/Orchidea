<template>
  <div class="map-container">
    <div class="search-bar">
      <input v-model="address" placeholder="Entrez une adresse" />
      <button @click="geocodeAddress">Rechercher</button>
    </div>
    <h2>Localisation</h2>
    <div id="mapcarto" class="map-container">
    </div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
// --- importer Leaflet et le css associé
import L from "leaflet";
import "leaflet/dist/leaflet.css";
// --  datas à afficher sur la carte
const cabinetsMedical = [
  { "nom" : "Maison de Santé Carré Gambetta", "lat" : 43.60411, "lng" : 2.23862 },
  { "nom" : "Cabinet Médical Gaillac et Luguet", "lat" : 43.60883, "lng" : 2.23078 },
  { "nom" : "Cabinet Médical de Lameilhé", "lat" : 43.59259, "lng" : 2.25492 },
  { "nom" : "Cabinet Médical du Saillenc", "lat" :  43.60928, "lng" : 2.25946 }
]
// -- Facultatif : définir une icône personnalisée pour les centres médicaux
const myIcon = L.icon({
  iconUrl: 'https://cdn-icons-png.flaticon.com/512/387/387561.png',
  iconSize: [48, 48],
});

// ------------  Fonction pour afficher la carte
function afficheCarto(){
  // Initialisation de la carte avec leaftlet
  //   - la placer dans la div d'id 'mapcarto'
  //   - la centrer sur Castres avec un zoom de 14
  let mapcarto = L.map('mapcarto').setView([43.60548, 2.24167], 14); // France

  // Ajouter un fond de carte (OpenStreetMap)
  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors'
  }).addTo(mapcarto);

  // Ajouter les markers :  1 pour chaque centre médical
  cabinetsMedical.forEach((cabinet) => {
    L.marker([cabinet.lat, cabinet.lng], {icon: myIcon}).addTo(mapcarto)
      .bindPopup(cabinet.nom); // Ajouter une popup au marker
  });

  // Ajouter un cercle sur la carte
  L.circle([43.60548, 2.24167], {
    color: 'blue', fillOpacity: 0.1, radius: 2000
  }).addTo(mapcarto);
}
// -- afficher la carte à la création du composant
onMounted(afficheCarto);
</script>

<style scoped>
.map-container {
  position: relative;
  padding-bottom: 60px;
  background-color: #dcead2;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  padding: 15px;
}

.search-bar {
  display: flex;
  flex-direction: column;
  width: 100%;
  max-width: 400px;
  gap: 10px;
}

input {
  padding: 10px;
  width: 100%;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  padding: 10px;
  font-size: 16px;
  background-color: #2e7d32;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #1b5e20;
}

#map {
  margin-top: 15px;
  width: 100%;
  max-width: 600px;
  height: 300px;
  border-radius: 10px;
  border: 1px solid #ccc;
}

/* 📱 Responsive mobile */
@media (max-width: 600px) {
  .map-container {
    padding: 10px;
  }

  .search-bar {
    width: 100%;
  }

  #map {
    height: 250px;
  }
}
.map-container { width: 80%; height: 700px; margin: 20px;}
</style>
