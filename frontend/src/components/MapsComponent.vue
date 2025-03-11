<template>
  <div class="map-container">
    <div class="search-bar">
      <input v-model="address" placeholder="Entrez une adresse" />
      <button @click="geocodeAddress">Rechercher</button>
    </div>
    <h2>Localisation</h2>
    <div id="map"></div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import L from "leaflet";
import "leaflet/dist/leaflet.css";

export default {
  setup() {
    const address = ref("");
    const map = ref(null);
    const marker = ref(null);

    const initMap = () => {
      map.value = L.map("map").setView([48.8566, 2.3522], 12); // Paris par défaut

      L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
        attribution: "&copy; OpenStreetMap contributors",
      }).addTo(map.value);
    };

    const geocodeAddress = async () => {
      if (!address.value) {
        alert("Veuillez entrer une adresse.");
        return;
      }

      const response = await fetch(
        `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(address.value)}`
      );
      const data = await response.json();

      if (data.length > 0) {
        const location = [parseFloat(data[0].lat), parseFloat(data[0].lon)];
        map.value.setView(location, 12);

        if (marker.value) {
          marker.value.remove();
        }

        marker.value = L.marker(location).addTo(map.value)
          .bindPopup(`📍 <strong>Coordonnées :</strong><br>Lat: ${data[0].lat}, Lon: ${data[0].lon}`)
          .openPopup();
      } else {
        alert("Adresse introuvable");
      }
    };

    onMounted(() => {
      initMap();
    });

    return { address, geocodeAddress };
  },
};
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
</style>
