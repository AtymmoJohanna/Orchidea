<template>
  <div class="gallery-container">
    <h2>Galerie d'Images</h2>
    <div v-if="photos.length === 0">Aucune image disponible.</div>
    <div v-else>
      <div v-for="(photo, index) in photos" :key="index" class="photo-item-container">
        <p><strong>URL de l'image:</strong> {{ photo.url }}</p>
        <p><strong>Auteur:</strong> {{ photo.auteur }}</p>
          <!-- Ajouter la description si nécessaire -->
      </div>
    </div>

    <div v-if="orchidee">
      <h3>Informations sur l'Orchidée</h3>
      <p><strong>Nom:</strong> {{ orchidee.nom }}</p>  <!-- Assurez-vous que les données d'orchidée existent -->
      <p><strong>Latitude:</strong> {{ orchidee.latitude }}</p>
      <p><strong>Longitude:</strong> {{ orchidee.longitude }}</p>
    </div>
  </div>
</template>



<script>
import { ref, onMounted } from "vue";
import axios from "axios";

export default {
  setup() {
    const photos = ref([]);  // Tableau pour stocker les photos et leurs informations
    const orchidee = ref(null);  // Stocker les données de l'orchidée

    // Fonction pour récupérer les données des photos et de l'orchidée
    const fetchPhotosAndOrchidee = async () => {
      try {
        // Récupérer les photos depuis l'API
        const responsePhotos = await axios.get("/api/photos");
        photos.value = responsePhotos.data;

        // Récupérer les informations sur l'orchidée
        const responseOrchidee = await axios.get("api/orchidees");
        orchidee.value = responseOrchidee.data;

        console.log("Photos récupérées :", photos.value);
        console.log("Données d'orchidée récupérées :", orchidee.value);
      } catch (error) {
        console.error("Erreur lors de la récupération des données :", error);
      }
    };

    // Charger les données au montage du composant
    onMounted(fetchPhotosAndOrchidee);

    return { photos, orchidee };
  },
};
</script>



<style scoped>
.gallery-container {
  text-align: center;
  padding: 20px;
}

.photo-item-container {
  margin: 10px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
}

h3 {
  margin-top: 20px;
}

p {
  margin: 5px 0;
  font-size: 14px;
  color: #333;
}
</style>


