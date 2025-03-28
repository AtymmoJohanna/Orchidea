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
          <img src="@/assets/cam-logo.png" alt="Camera" class="button-img" />
        </button>
      </div>

      <!-- Bouton pour valider et continuer -->
      <button v-if="photos.length" class="validate-button" @click="goToForm">Valider</button>

      <!-- Input file caché pour capturer une image -->
      <input type="file" ref="fileInput" accept="image/*" capture="environment" @change="handleImage" hidden />
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
        const reader = new FileReader();
        reader.readAsDataURL(file); // Convertit l'image en Base64
        reader.onload = () => {
          photos.value.push(reader.result); // Stocker l'image en Base64
        };
      }
    };

    const removePhoto = (index) => {
      photos.value.splice(index, 1); // Supprimer une photo spécifique
    };

    const savePhotoToDB = async (photoBase64) => {
      try {
        const response = await fetch("http://localhost:8080/api/photos", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            url: photoBase64, // Envoi de la photo en Base64
            datePriseVue: new Date().toISOString().split("T")[0], // Date du jour
            auteurId: 1, // Remplace par l'ID réel de l'utilisateur
            specimenId: 1, // Remplace par l'ID réel de l'orchidée
          }),
        });

        if (!response.ok) {
          throw new Error("Erreur lors de l'enregistrement de la photo");
        }

        console.log("Photo enregistrée avec succès !");
      } catch (error) {
        console.error("Erreur:", error);
      }
    };

    const goToForm = async () => {
      if (photos.value.length > 0) {
        await savePhotoToDB(photos.value[0]); // Enregistrer la première photo
        router.push("/formulaire"); // Redirection après enregistrement
      }
    };

    return { photos, fileInput, openCamera, handleImage, removePhoto, goToForm };
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
  margin-top: 3vh; /* Add margin-top of 3vh */
}
.text {
  font-size: 3.5vh;
  margin: 5vh 0;
  text-align: center; /* Center the text horizontally */
  font-weight: bold;
}

.buttons {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr; /* Three equal columns */
  width: 80vw; /* Set the width to 80vw */
  margin: 2vh auto 0; /* Center the grid horizontally and add margin-top of 2vh */
  gap: 20px;
}

.gallery-button,
.camera-button {
  padding: 10px;
  border-radius: 50%;
  background: white;
  border: 1px solid #ccc;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
}


.button-img {
  width: 6vh;
  height: 6vh;
}
.camera-button {
  width: 14vh; /* Make the camera button larger */
  height: 14vh; /* Make the camera button larger */
}
.camera-img {
  width: 10vh; /* Make the camera image larger */
  height: 10vh; /* Make the camera image larger */
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
  background-color: #2E7D32;
  color: #fff;
  padding: 0.75rem;
  border: none;
  border-radius: 4px;
  width: 50%;
  font-size: 1rem;
  cursor: pointer;
}

</style>
