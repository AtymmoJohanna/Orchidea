<template>
  <div class="camera-container">
    <div class="content">
      <div class="image-container">
        <img src="@/assets/img-fond.png" alt="Fleur" class="top-image" />
      </div>

      <p class="text">Appuyez pour identifier</p>

      <div class="buttons">
        <button class="gallery-button">📷 Galerie</button>
        <button class="camera-button">📸</button>
      </div>
    </div>
  </div>
</template>

<script>
import FooterVue from "@/components/FooterVue.vue";

export default {
  components: {
    FooterVue, // ✅ Ajout du footer pour cohérence
  },
};
</script>

<style scoped>
.camera-container {
  position: relative;
  padding-bottom: 60px; /* Pour éviter que le footer ne cache les éléments */
  background-color: #dcead2;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

.content {
  width: 90%;
  max-width: 400px;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.input-field {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.search-button {
  padding: 10px;
  border: none;
  background-color: #4caf50;
  color: white;
  border-radius: 5px;
  cursor: pointer;
}

.image-container {
  width: 100%;
  text-align: center;
  margin-top: 20px;
}

.top-image {
  width: 100%;
  max-height: 200px;
  object-fit: cover;
  border-radius: 10px;
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
