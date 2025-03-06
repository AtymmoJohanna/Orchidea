<template>
  <div class="camera-view">
    <div class="image-container">
      <img src="@/assets/img-fond.png" alt="Fleur" class="top-image" />
    </div>

    <p class="text">Appuyez pour identifier</p>

    <div class="buttons">
      <button class="gallery-button">📷 Galerie</button>
      <button class="camera-button">📸</button>
    </div>

    <!-- Footer inclus -->
    <FooterVue />
  </div>
</template>

<script>
import FooterVue from "@/components/FooterVue.vue";

export default {
  components: {
    FooterVue,
  },
};
</script>

<style scoped>
.camera-view {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: #dcead2;
}

.image-container {
  width: 100%;
  text-align: center;
}

.top-image {
  width: 100%;
  max-height: 200px;
  object-fit: cover;
}

.text {
  font-size: 18px;
  margin: 10px 0;
}

.buttons {
  display: flex;
  gap: 20px;
  margin-top: 20px;
}

.gallery-button,
.camera-button {
  font-size: 16px;
  padding: 10px 20px;
  border-radius: 50%;
  background: white;
  border: 1px solid #ccc;
  cursor: pointer;
}

.camera-button {
  width: 80px;
  height: 80px;
  font-size: 24px;
}
</style>
