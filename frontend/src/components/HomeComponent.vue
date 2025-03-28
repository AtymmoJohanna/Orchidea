<template>
  <div class="gallery-container">
    <h2>Galerie d'Images</h2>
    <div v-if="photos.length === 0">Aucune image disponible.</div>
    <div v-else class="photo-grid">
      <div v-for="(urls, orchidName) in groupedPhotos" :key="orchidName" class="photo-group">
        <h3>{{ orchidName }}</h3>
        <div class="photo-list">
          <img v-for="url in urls" :key="url" :src="url" class="photo-item" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, computed, onMounted } from "vue";
import axios from "axios";

export default {
  setup() {
    const photos = ref([]);

    // Fonction pour récupérer les photos depuis l’API
    const fetchPhotos = async () => {
      try {
        const response = await axios.get("https://monserveur.com/api/photos"); // Remplace par l'URL de ton API
        photos.value = response.data;
      } catch (error) {
        console.error("Erreur lors du chargement des photos :", error);
      }
    };

    // Regrouper les photos par nom d’orchidée
    const groupedPhotos = computed(() => {
      return photos.value.reduce((acc, photo) => {
        if (!acc[photo.orchidName]) {
          acc[photo.orchidName] = [];
        }
        acc[photo.orchidName].push(photo.url);
        return acc;
      }, {});
    });

    // Charger les photos dès que le composant est monté
    onMounted(fetchPhotos);

    return { groupedPhotos, fetchPhotos };
  },
};
</script>

<style scoped>
.gallery-container {
  text-align: center;
  padding: 20px;
}

.photo-grid {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.photo-group {
  background: #f5f5f5;
  padding: 10px;
  border-radius: 8px;
}

.photo-list {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  justify-content: center;
}

.photo-item {
  width: 120px;
  height: 120px;
  object-fit: cover;
  border-radius: 5px;
  border: 1px solid #ddd;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
}
</style>
