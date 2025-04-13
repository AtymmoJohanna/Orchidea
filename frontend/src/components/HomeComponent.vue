<template>
  <div class="gallery-container">
    <h2 class="title">Galerie d'Images</h2>

    <!-- Loading State -->
    <div v-if="isPhotoLoading" class="loading-state">
      <p>Chargement des photos...</p>
    </div>

    <!-- Display All Images -->
    <div v-else-if="photos.length > 0" class="image-container">
      <div v-for="(photo, index) in photos" :key="index" class="image-card">
        <img :src="photo.url" alt="Image Base64" class="image" />
      </div>
    </div>

    <!-- Orchidée Information -->
    <div v-if="orchidee" class="orchidee-info">
      <h3 class="section-title">Informations sur l'Orchidée</h3>
      <div class="info-grid">
        <div v-if="espece" class="additional-info">
          <p><strong>Espèce :</strong> {{ espece.nomScientifique }}</p>
        </div>

        <div v-if="auteur" class="additional-info">
          <p><strong>Auteur :</strong> {{ auteur.nom }} {{ auteur.prenom }}</p>
        </div>
        <p><strong>Commentaire:</strong> {{ orchidee.commentaire }}</p>
        <p><strong>État de l'inflorescence:</strong> {{ orchidee.etat }}</p>
        <p><strong>Variabilité du taxon:</strong> {{ orchidee.varTaxon }}</p>
        <p><strong>Nombre d'individus:</strong> {{ orchidee.nbreIndividu }}</p>
        <p><strong>Forme:</strong> {{ orchidee.forme }}</p>
        <p><strong>Couleurs:</strong> {{ orchidee.couleur?.join(', ') }}</p>
        <p><strong>Motifs:</strong> {{ orchidee.motif?.join(', ') }}</p>
        <p><strong>Latitude:</strong> {{ orchidee.latitude }}</p>
        <p><strong>Longitude:</strong> {{ orchidee.longitude }}</p>
        <p><strong>Date d'enregistrement:</strong> {{ orchidee.dateEnreg }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios";

export default {
  setup() {
    const photos = ref([]);
    const orchidee = ref(null);
    const espece = ref(null);
    const auteur = ref(null);
    const isPhotoLoading = ref(true);

    const getAllPhotos = async () => {
      isPhotoLoading.value = true;
      try {
        const response = await axios.get("api/photos");
        photos.value = response.data._embedded?.photos || response.data;
      } catch (error) {
        console.error("Erreur lors de la récupération des photos :", error);
      } finally {
        isPhotoLoading.value = false;
      }
    };

    const fetchEspece = async (url) => {
      try {
        const response = await axios.get(url);
        espece.value = response.data;
      } catch (error) {
        console.error("Erreur lors de la récupération de l'espèce :", error);
      }
    };

    const fetchAuteur = async (url) => {
      try {
        const response = await axios.get(url);
        auteur.value = response.data;
      } catch (error) {
        console.error("Erreur lors de la récupération de l'auteur :", error);
      }
    };

    const fetchPhotosAndOrchidee = async () => {
      try {
        const responseOrchidee = await axios.get("api/orchidees/1");
        orchidee.value = responseOrchidee.data;

        const especeUrl = orchidee.value._links?.espece?.href;
        const auteurUrl = orchidee.value._links?.auteur?.href;

        if (especeUrl) await fetchEspece(especeUrl);
        if (auteurUrl) await fetchAuteur(auteurUrl);
      } catch (error) {
        console.error("Erreur lors de la récupération des données :", error);
      }
    };

    onMounted(() => {
      getAllPhotos();
      fetchPhotosAndOrchidee();
    });

    return {
      photos,
      orchidee,
      espece,
      auteur,
      isPhotoLoading,
    };
  },
};
</script>

<style scoped>
.gallery-container {
  font-family: 'Arial', sans-serif;
  padding: 2rem;
  background-color: #f4f6f9;
  max-width: 1200px;
  margin: auto;
}

.title {
  text-align: center;
  color: #2C6B2F;
  font-size: 2rem;
  margin-bottom: 1.5rem;
}

.loading-state {
  text-align: center;
  font-size: 1.2rem;
  color: #888;
  padding: 20px;
  background-color: #f1f1f1;
  border-radius: 8px;
  width: 100%;
  margin-bottom: 2rem;
}

.image-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 1rem;
  margin-bottom: 2rem;
}

.image-card {
  max-width: 300px;
  flex: 1 1 300px;
}

.image {
  width: 100%;
  height: auto;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.orchidee-info {
  background-color: #fff;
  padding: 1.5rem;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.section-title {
  color: #2C6B2F;
  font-size: 1.5rem;
  margin-bottom: 1rem;
}

.info-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1rem;
  margin-bottom: 1.5rem;
}

.info-grid p,
.additional-info {
  font-size: 1rem;
  color: #555;
}

@media (max-width: 768px) {
  .info-grid {
    grid-template-columns: 1fr;
  }

  .title {
    font-size: 1.5rem;
  }

  .section-title {
    font-size: 1.25rem;
  }
}
</style>
