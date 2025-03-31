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

    <!-- Input file caché pour capturer une image -->
    <input type="file" ref="fileInput" accept="image/*" capture="environment" @change="handleImage" hidden />
  </div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

export default {
  setup() {
    const user = JSON.parse(localStorage.getItem("user"));
    const photos = ref([]); // Store captured photos
    const fileInput = ref(null);
    const router = useRouter();

    const openCamera = () => {
      fileInput.value.click(); // Trigger the file input
    };

    const handleImage = (event) => {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          photos.value.push(e.target.result); // Add the photo to the gallery
        };
        reader.readAsDataURL(file);
      }
    };

    const removePhoto = (index) => {
      photos.value.splice(index, 1); // Remove the photo from the gallery
    };

    const savePhoto = async () => {
      if (!photos.value.length) return;

      try {
        const photoData = {
          url: photos.value[0],
          auteur: user,
        };

        // Pass photo data in the route params
        router.push({ name: "formulaire", params: { photo: JSON.stringify(photoData) } });

      } catch (error) {
        console.error("Erreur lors de l'enregistrement de la photo :", error);
        alert("Une erreur est survenue lors de l'enregistrement de la photo.");
      }
    };


    return { photos, fileInput, openCamera, handleImage, removePhoto, savePhoto };
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
  position: relative; /* Make this container the reference for positioning */
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
   /* Set the width to 80vw */
    width: 80vw; /* Set the width to 80vw */
   margin: 5vh auto 0; /* Center the grid horizontally and add margin-top of 2vh */
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
  width: 14vh; /* Make the camera button larger */
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
  width: 10vh; /* Make the gallery button larger */
  height: 10vh;
}


.button-img {
  width: 6vh;
  height: 6vh;
}

.camera-img {
  width: 10vh; /* Make the camera image larger */
  height: 10vh; /* Make the camera image larger */
}

.delete-button {
  position: absolute; /* Position relative to the .image-container */
  top: 5px; /* Distance from the top of the image */
  right: 5px; /* Distance from the right of the image */
  background: white; /* Set the background color to white */
  color: red; /* Set the text color to red */
  border: 2px solid red; /* Add a red border */
  cursor: pointer;
  font-size: 18px; /* Increase the font size */
  border-radius: 50%; /* Make the button circular */
  width: 4vh; /* Increase the width */
  height: 4vh; /* Increase the height */
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* Add a subtle shadow */
}


.validate-button {
  background-color: #2E7D32;
  color: #fff;
  padding: 0.75rem;
  border: none;
  border-radius: 4px;
  width: 50%; /* Set the width to 50% */
  font-size: 1rem;
  cursor: pointer;
  margin: 5vh auto 0; /* Add margin-top of 5vh and center horizontally */
  display: block; /* Ensure the button is treated as a block-level element */
  text-align: center; /* Center the text inside the button */
}

</style>
