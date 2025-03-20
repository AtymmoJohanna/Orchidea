<template>
  <div class="camera-container">
    <div class="content">
      <!--<img src="@/assets/img-fond.png" alt="Fleur" class="top-image" />-->

      <!-- Galerie des images prises -->
      <div class="image-gallery" v-if="photos.length">
        <div v-for="(photo, index) in photos" :key="index" class="image-container">
          <img :src="photo" alt="Captured Image" class="top-image" />
          <button class="delete-button" @click="removePhoto(index)">❌</button>
        </div>
      </div>

      <p class="text" v-if="!photos.length">Appuyez pour identifier</p>

      <div class="buttons">
        <button class="gallery-button">
          <img src="@/assets/galerie.png" alt="Galerie" class="button-img" />
        </button>

        <button class="camera-button" @click="openCamera">
          <img src="@/assets/cam-logo.png" alt="Camera" class="button-img" />
        </button>
      </div>

      <!-- Bouton pour valider et continuer -->
      <button v-if="photos.length" class="validate-button" @click="goToForm">Valider</button>

      <!-- Input file caché pour capturer une image -->
      <input type="file" ref="fileInput" accept="image/*" capture="environment" @change="handleImage" hidden />
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import { useRouter } from "vue-router";

export default {
  setup() {
    const photos = ref([]); // Stocke plusieurs photos
    const fileInput = ref(null);
    const router = useRouter();

    const openCamera = () => {
      fileInput.value.click();
    };

    const handleImage = (event) => {
      const file = event.target.files[0];
      if (file) {
        const imageUrl = URL.createObjectURL(file);
        photos.value.push(imageUrl); // Ajouter la photo au tableau
      }
    };

    const removePhoto = (index) => {
      photos.value.splice(index, 1); // Supprimer une photo spécifique
    };

    const goToForm = () => {
      router.push("/formulaire"); // Rediriger vers le formulaire
    };

    return { photos, fileInput, openCamera, handleImage, removePhoto, goToForm };
  },
};
</script>

<style scoped>
.camera-container {
  position: relative;
  padding-bottom: 60px;
  background-color: #dcead2;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

.image-gallery {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 10px;
  margin-top: 20px;
}

.image-container {
  position: relative;
  display: inline-block;
  width: 100px;
  height: 100px;
}

.top-image {
  width: 100%;
  height: 100%;
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
  padding: 10px;
  border-radius: 50%;
  background: white;
  border: 1px solid #ccc;
  cursor: pointer;
}

.button-img {
  width: 50px;
  height: 50px;
}

.delete-button {
  position: absolute;
  top: 5px;
  right: 5px;
  background: red;
  color: white;
  border: none;
  cursor: pointer;
  font-size: 14px;
  border-radius: 50%;
  width: 20px;
  height: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.validate-button {
  margin-top: 20px;
  background: green;
  color: white;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  font-size: 16px;
  border-radius: 5px;
}
</style>
