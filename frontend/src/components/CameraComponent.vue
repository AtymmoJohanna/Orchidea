<template>
  <div class="camera-container">
    <div class="content">
      <img src="@/assets/img-fond.png" alt="Fleur" class="top-image" />
      <!-- Affichage de l'image prise -->
      <div class="image-container" v-if="photo">
        <img :src="photo" alt="Captured Image" class="top-image" />
      </div>
      <p class="text" v-else>Appuyez pour identifier</p>

      <div class="buttons">
        <!-- Bouton Galerie -->
        <button class="gallery-button">
          <img src="@/assets/galerie.png" alt="Galerie" class="button-img" />
        </button>

        <!-- Bouton Caméra -->
        <button class="camera-button" @click="openCamera">
          <img src="@/assets/cam-logo.png" alt="Camera" class="button-img" />
        </button>
      </div>

      <!-- Input file caché pour capturer une image -->
      <input type="file" ref="fileInput" accept="image/*" capture="environment" @change="handleImage" hidden />
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import { useRouter } from "vue-router"; // Pour la redirection après la prise de photo

export default {
  setup() {
    const photo = ref(null);
    const fileInput = ref(null);
    const router = useRouter();

    // Fonction pour ouvrir la caméra
    const openCamera = () => {
      fileInput.value.click();
    };

    // Fonction pour gérer l’image prise
    const handleImage = (event) => {
      const file = event.target.files[0];
      if (file) {
        photo.value = URL.createObjectURL(file); // Affichage immédiat de l’image
        setTimeout(() => {
          router.push("/formulaire"); // Rediriger vers l’interface de saisie
        }, 1000);
      }
    };

    return { photo, fileInput, openCamera, handleImage };
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

.button-img {
  width: 50px;
  height: 50px;
}
</style>
