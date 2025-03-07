<template>
  <div class="map-container">
    <input v-model="address" placeholder="Entrez une adresse" />
    <button @click="geocodeAddress">Rechercher</button>
    <h2>Localisation</h2>
    <div id="map"></div>

  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import L from "leaflet";
import "leaflet/dist/leaflet.css";
import FooterVue from "@/components/FooterVue.vue";

export default {
  components: {
    FooterVue, // ✅ Déclaration du footer
  },
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
  background-color: #dcead2;/* Pour éviter que le footer cache la carte */
}

input {
  margin-bottom: 10px;
  padding: 5px;
  width: 60%;
}

button {
  margin-left: 10px;
  padding: 5px 10px;
  cursor: pointer;
}

#map {
  margin-top: 10px;
  width: 100%;
  height: 400px;
  border: 1px solid #ccc;
}
</style>
