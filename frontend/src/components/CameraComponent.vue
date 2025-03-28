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
