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
      <button class="gallery-button">
        <img src="@/assets/galerie.png" alt="Galerie" class="button-img" />
      </button>

      <button class="camera-button" @click="openCamera">
        <img src="@/assets/cam-logo.png" alt="Camera" class="camera-img" />
      </button>
    </div>

    <!-- Bouton pour valider et continuer -->
    <button v-if="photos.length" class="validate-button" @click="savePhoto">Valider</button>

    <!-- Zone pour afficher le flux vidéo de la caméra -->
    <div v-if="isCameraOpen">
      <video class="video" ref="video" width="320" height="240" autoplay></video>
      <button class="validate-button" @click="capturePhoto">Prendre une photo</button>
    </div>

    <canvas ref="canvas" width="320" height="340" style="display: none;"></canvas>
  </div>
</template>

<script>
import { ref } from "vue";
import { useRouter } from "vue-router";

export default {
  setup() {
    const user = JSON.parse(localStorage.getItem("user"));
    const photos = ref([]); // Store captured photos
    const isCameraOpen = ref(false); // Track if the camera is open
    const router = useRouter();

    const openCamera = () => {
      isCameraOpen.value = true;
      navigator.mediaDevices.getUserMedia({ video: true })
        .then((stream) => {
          const videoElement = document.querySelector("video");
          videoElement.srcObject = stream;
        })
        .catch((error) => {
          console.error("Erreur d'accès à la caméra: ", error);
        });
    };

    const capturePhoto = () => {
      const canvas = document.querySelector("canvas");
      const video = document.querySelector("video");
      const context = canvas.getContext("2d");

      // Capture the current frame from the video stream and draw it to the canvas
      context.drawImage(video, 0, 0, canvas.width, canvas.height);

      // Convert the image from the canvas to a data URL
      const imageDataUrl = canvas.toDataURL('image/png');

      // Add the captured photo to the gallery
      photos.value.push(imageDataUrl);

      // Close the camera after taking a photo
      isCameraOpen.value = false;
    };

    const removePhoto = (index) => {
      photos.value.splice(index, 1); // Remove the photo from the gallery
    };

    const savePhoto = async () => {
      if (!photos.value.length) return;

      try {
        const photoData = {
          url: photos.value[0], // Première photo capturée
          auteur: user,
        };

        // Stocker la photo dans localStorage pour la récupérer dans le formulaire
        localStorage.setItem("photo", JSON.stringify(photoData));

        // Rediriger vers le formulaire sans passer par `route.params`
        router.push({ name: "formulaire" });

      } catch (error) {
        console.error("Erreur lors de l'enregistrement de la photo :", error);
        alert("Une erreur est survenue lors de l'enregistrement de la photo.");
      }
    };




    return { photos, openCamera, capturePhoto, removePhoto, savePhoto, isCameraOpen };
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
.video{
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
  background-color: #2E7D32;
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
