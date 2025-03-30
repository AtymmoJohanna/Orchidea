<template>
  <div class="content">
    <img src="@/assets/img-fond.png" alt="Fleur" class="top-image" />

    <!-- Galerie des images prises -->
    <div class="image-gallery" v-if="photos.length">
      <div v-for="(photo, index) in photos" :key="index" class="image-container">
        <img :src="photo" alt="Captured Photo" class="captured-photo" />
        <button class="delete-button" @click="removePhoto(index)">X</button>
      </div>
    </div>

    <p class="text" v-if="!photos.length">Appuyez pour identifier</p>

    <div class="buttons">
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
/* Mise en forme pour le paragraphe .text */
.text {
  font-size: 2.5vh; /* Taille du texte ajustée en fonction de la hauteur de l'écran */
  text-align: center; /* Centrer le texte horizontalement */
  font-weight: bold; /* Mettre le texte en gras */
  color: #333; /* Couleur sombre pour le texte */
  margin: 20px 0; /* Marge autour du texte */
  line-height: 1.5; /* Espacement entre les lignes pour améliorer la lisibilité */
}
/* Image d'entête : Ne prend que la hauteur nécessaire */
.top-image {
  width: 100%;
  height: auto; /* Garde la hauteur proportionnelle */
  object-fit: cover;
  margin: 0; /* Pas de marge au-dessus */
  max-height: 300px; /* Ajuste la hauteur maximale si nécessaire */
}

/* Galerie des images : Centrer les images sélectionnées horizontalement */
.image-gallery {
  display: flex;
  justify-content: center; /* Centrer horizontalement */
  gap: 10px;
  margin-top: 20px;
  flex-wrap: wrap; /* Permet de passer à la ligne suivante si nécessaire */
  overflow-x: auto; /* Défilement horizontal si trop d'images */
}

/* Conteneur des images : Largeur et hauteur adaptées */
.image-container {
  position: relative;
  width: 120px; /* Largeur fixe pour les images */
  height: 120px; /* Hauteur fixe pour les images */
}

/* Styles pour la croix de suppression sur les images */
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


/* Boutons : Centrer les boutons de caméra et galerie, taille ajustée */
.buttons {
  display: flex;
  justify-content: center; /* Centrer horizontalement */
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
  display: flex;
  align-items: center;
  justify-content: center;
}

/* Pour le bouton Valider : Centré et taille ajustée */
.validate-button {
  background-color: #2E7D32;
  color: #fff;
  padding: 0.75rem;
  border: none;
  border-radius: 4px;
  width: auto; /* Taille ajustée à son contenu */
  font-size: 1rem;
  cursor: pointer;
  margin: 20px auto;
  display: block;
}
</style>
