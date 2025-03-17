<template>
  <div class="camera-container">
    <div class="content">
      <img src="@/assets/img-fond.png" alt="Fleur" class="top-image" />

      <!-- Affichage de l'image prise -->
      <div class="image-container" v-if="photo">
        <img :src="photo" alt="Captured Image" class="captured-image" />
        <p class="location" v-if="location">
          📍 {{ location.latitude }}, {{ location.longitude }}
        </p>
      </div>
      <p class="text" v-else>Appuyez pour identifier</p>

      <div class="buttons">
        <!-- Bouton Galerie -->
        <button class="gallery-button" @click="openGallery">
          <img src="@/assets/galerie.png" alt="Galerie" class="button-img" />
        </button>

        <!-- Bouton Caméra -->
        <button class="camera-button" @click="openCamera">
          <img src="@/assets/cam-logo.png" alt="Camera" class="button-img" />
        </button>
      </div>

      <!-- Input file caché pour capturer une image -->
      <input
        type="file"
        ref="fileInput"
        accept="image/*"
        capture="environment"
        @change="handleImage"
        hidden
      />

      <!-- Input file caché pour sélectionner une image depuis la galerie -->
      <input
        type="file"
        ref="galleryInput"
        accept="image/*"
        @change="handleImage"
        hidden
      />
    </div>
  </div>
</template>

<script>
import { ref, onUnmounted } from "vue";
import { useRouter } from "vue-router";

export default {
  setup() {
    const photo = ref(null);
    const fileInput = ref(null);
    const galleryInput = ref(null);
    const location = ref(null);
    const router = useRouter();

    // Fonction pour ouvrir la caméra
    const openCamera = () => {
      getLocation();
      fileInput.value.click();
    };

    // Fonction pour sélectionner une image depuis la galerie
    const openGallery = () => {
      getLocation();
      galleryInput.value.click();
    };

    // Fonction pour gérer l’image prise ou sélectionnée
    const handleImage = (event) => {
      const file = event.target.files[0];
      if (file) {
        const imageURL = URL.createObjectURL(file);
        photo.value = imageURL;

        setTimeout(() => {
          router.push("/formulaire");
        }, 1000);
      }
    };

    // Fonction pour récupérer la localisation GPS
    const getLocation = () => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          (position) => {
            location.value = {
              latitude: position.coords.latitude,
              longitude: position.coords.longitude,
            };
          },
          (error) => {
            console.error("Erreur de localisation:", error);
          }
        );
      } else {
        console.error("La géolocalisation n'est pas supportée par ce navigateur.");
      }
    };

    // Nettoyer l’URL pour éviter les fuites de mémoire
    onUnmounted(() => {
      if (photo.value) {
        URL.revokeObjectURL(photo.value);
      }
    });

    return { photo, fileInput, galleryInput, location, openCamera, openGallery, handleImage };
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
  width: 90%;
  max-width: 400px;
  text-align: center;
  margin-top: 20px;
}

.top-image {
  width: 100%;
  max-height: 150px;
  object-fit: cover;
  border-radius: 10px;
}

.captured-image {
  width: 100%;
  max-width: 300px;
  max-height: 300px;
  border-radius: 10px;
}

.text {
  font-size: 16px;
  margin: 10px 0;
}

.location {
  font-size: 14px;
  color: #555;
  margin-top: 10px;
}

.buttons {
  display: flex;
  gap: 20px;
  margin-top: 20px;
}

.gallery-button,
.camera-button {
  padding: 10px;
  border-radius: 50%;
  background: white;
  border: 1px solid #ccc;
  cursor: pointer;
  width: 60px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.button-img {
  width: 40px;
  height: 40px;
}
</style>
