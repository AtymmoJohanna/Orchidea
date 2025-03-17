<template>
  <div class="photo-gallery">
    <div v-for="(photos, orchidName) in groupedPhotos" :key="orchidName" class="photo-group">
      <h3>{{ orchidName }}</h3>
      <div class="photo-list">
        <img v-for="(photo, index) in photos" :key="index" :src="photo" alt="Orchid Photo" class="photo-item" />
      </div>
    </div>
  </div>
</template>

<script>
import { ref, computed } from "vue";

export default {
  props: {
    capturedPhotos: Array, // Tableau contenant { url, orchidName }
  },
  setup(props) {
    // Regrouper les photos par nom d'orchidée
    const groupedPhotos = computed(() => {
      return props.capturedPhotos.reduce((acc, photo) => {
        if (!acc[photo.orchidName]) {
          acc[photo.orchidName] = [];
        }
        acc[photo.orchidName].push(photo.url);
        return acc;
      }, {});
    });

    return { groupedPhotos };
  },
};
</script>

<style scoped>
.photo-gallery {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 20px;
}

.photo-group {
  background: #f0f0f0;
  padding: 15px;
  border-radius: 8px;
}

h3 {
  margin-bottom: 10px;
  font-size: 1.2rem;
  font-weight: bold;
}

.photo-list {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
}

.photo-item {
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 5px;
}
</style>
