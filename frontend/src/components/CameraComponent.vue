<template>
  <div class="content">
    <img src="@/assets/img-fond.png" alt="Fleur" class="top-image" />

    <!-- Galerie des images prises -->
    <div class="image-gallery" v-if="photos.length">
      <div v-for="(photo, index) in photos" :key="index" class="image-container">
        <img :src="photo" alt="Captured Image" class="top-image" />
        <button class="delete-button" @click="removePhoto(index)">❌</button>
      </div>
    </div>

    <p class="text" v-if="!photos.length">Appuyez pour identifier</p>

    <div class="buttons">
      <button class="gallery-button" @click="triggerFileInput">
        <img src="@/assets/galerie.png" alt="Galerie" class="button-img" />
      </button>
      <button class="camera-button" @click="openCamera">
        <img src="@/assets/cam-logo.png" alt="Camera" class="camera-img" />
      </button>
    </div>

    <input type="file" ref="fileInput" @change="handleFileSelect" accept="image/*" style="display: none;" />
    <button v-if="photos.length" class="validate-button" @click="savePhotos">Valider</button>

    <div v-if="isCameraOpen">
      <video class="video" ref="video" width="320" height="240" autoplay></video>
      <button class="validate-button" @click="capturePhoto">Prendre une photo</button>
    </div>

    <canvas ref="canvas" width="320" height="340" style="display: none;"></canvas>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";

export default {
  setup() {
    const user = JSON.parse(localStorage.getItem("user"));
    const photos = ref([]);
    const isCameraOpen = ref(false);
    const router = useRouter();

    onMounted(() => {
      photos.value = []; // Réinitialisation explicite
      localStorage.removeItem("photos"); // Nettoyer les photos précédentes
      console.log("CameraComponent monté : photos réinitialisées, localStorage nettoyé");
    });

    const openCamera = () => {
      isCameraOpen.value = true;
      navigator.mediaDevices
        .getUserMedia({ video: true })
        .then((stream) => {
          const videoElement = document.querySelector("video");
          videoElement.srcObject = stream;
        })
        .catch((error) => {
          console.error("Erreur d'accès à la caméra: ", error);
          alert("Impossible d'accéder à la caméra.");
        });
    };

    const capturePhoto = () => {
      const canvas = document.querySelector("canvas");
      const video = document.querySelector("video");
      const context = canvas.getContext("2d");

      context.drawImage(video, 0, 0, canvas.width, canvas.height);
      const imageDataUrl = canvas.toDataURL("image/png");
      photos.value.push(imageDataUrl);
      isCameraOpen.value = false;
      console.log("Photo capturée :", photos.value.length, "photos au total");
    };

    const removePhoto = (index) => {
      photos.value.splice(index, 1);
      console.log("Photo supprimée, restantes :", photos.value.length);
    };

    const savePhotos = async () => {
      if (!photos.value.length) {
        alert("Aucune photo à enregistrer.");
        return;
      }
      if (!user) {
        alert("Utilisateur non authentifié.");
        return;
      }

      try {
        const photosData = photos.value.map((photoUrl) => ({
          url: photoUrl,
          auteur: user,
        }));
        localStorage.setItem("photos", JSON.stringify(photosData));
        console.log("Photos sauvegardées dans localStorage :", photosData);
        router.push({ name: "formulaire" });
      } catch (error) {
        console.error("Erreur lors de l'enregistrement des photos :", error);
        alert("Une erreur est survenue lors de l'enregistrement des photos.");
      }
    };

    const handleFileSelect = (event) => {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          photos.value.push(e.target.result);
          console.log("Photo ajoutée depuis la galerie :", photos.value.length);
        };
        reader.readAsDataURL(file);
      }
    };

    const triggerFileInput = () => {
      const fileInput = document.querySelector('input[type="file"]');
      fileInput.click();
    };

    return {
      photos,
      openCamera,
      capturePhoto,
      removePhoto,
      savePhotos,
      isCameraOpen,
      handleFileSelect,
      triggerFileInput,
    };
  },
};
</script>

<style scoped>
.image-gallery {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 10px;
  margin-top: 20px;
}

.image-container {
  position: relative;
  max-height: 150px;
  object-fit: cover;
  border-radius: 10px;
  margin-top: 3vh;
}

.top-image {
  width: 100%;
  max-height: 150px;
  object-fit: cover;
  border-radius: 10px;
  margin-top: 3vh;
}

.text {
  font-size: 3.5vh;
  margin: 5vh 0;
  text-align: center;
  font-weight: bold;
}

.buttons {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  width: 80vw;
  margin: 5vh auto 0;
  gap: 20px;
}

.video {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  width: 80vw;
  margin: 5vh auto 0;
  gap: 20px;
}

.camera-button {
  padding: 10px;
  border-radius: 50%;
  background: white;
  border: 1px solid #ccc;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 14vh;
  height: 14vh;
}

.gallery-button {
  padding: 10px;
  border-radius: 50%;
  background: white;
  border: 1px solid #ccc;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 10vh;
  height: 10vh;
}

.button-img {
  width: 6vh;
  height: 6vh;
}

.camera-img {
  width: 10vh;
  height: 10vh;
}

.delete-button {
  position: absolute;
  top: 5px;
  right: 5px;
  background: white;
  color: red;
  border: 2px solid red;
  cursor: pointer;
  font-size: 18px;
  border-radius: 50%;
  width: 4vh;
  height: 4vh;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

.validate-button {
  background-color: #2e7d32;
  color: #fff;
  padding: 0.75rem;
  border: none;
  border-radius: 4px;
  width: 50%;
  font-size: 1rem;
  cursor: pointer;
  margin: 5vh auto 0;
  display: block;
  text-align: center;
}
</style>
