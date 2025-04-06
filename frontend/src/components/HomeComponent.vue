<template>
  <div class="gallery-container">
    <h2>Galerie d'Images</h2>

    <div v-if="isPhotoLoading">Chargement de la photo...</div>
    <div v-else-if="photos && photos.url">
      <img :src="photos.url" alt="Image Base64" style="max-width: 100%; height: auto;" />
    </div>

    <div v-if="orchidee">
      <h3>Informations sur l'Orchidée</h3>
      <p><strong>ID:</strong> {{ orchidee.id }}</p>
      <p><strong>Commentaire:</strong> {{ orchidee.commentaire }}</p>
      <p><strong>Latitude:</strong> {{ orchidee.latitude }}</p>
      <p><strong>Longitude:</strong> {{ orchidee.longitude }}</p>
      <p><strong>Date d'enregistrement:</strong> {{ orchidee.dateEnreg }}</p>
      <p><strong>État de l'inflorescence:</strong> {{ orchidee.etat }}</p>
      <p><strong>Variabilité du taxon:</strong> {{ orchidee.varTaxon }}</p>
      <p><strong>Nombre d'individus:</strong> {{ orchidee.nbreIndividu }}</p>
      <p><strong>Forme:</strong> {{ orchidee.forme }}</p>
      <p><strong>Couleurs:</strong> {{ orchidee.couleur?.join(', ') }}</p>
      <p><strong>Motifs:</strong> {{ orchidee.motif?.join(', ') }}</p>

      <div v-if="espece">
        <p><strong>Espèce :</strong> {{ espece.nomScientifique }}</p>
      </div>
      <div v-if="auteur">
        <p><strong>Auteur :</strong> {{ auteur.nom }} {{ auteur.prenom }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios";

export default {
  setup() {
    const photos = ref(null);
    const orchidee = ref(null);
    const espece = ref(null);
    const auteur = ref(null);
    const isPhotoLoading = ref(true);

    const getPhoto = async (id) => {
      isPhotoLoading.value = true;
      try {
        const response = await axios.get(`api/photos/${id}`);
        photos.value = response.data;
      } catch (error) {
        console.error("Erreur lors de la récupération de la photo :", error);
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
        console.log(orchidee.value);
        // Récupération des liens vers l'auteur et l'espèce
        const especeUrl = orchidee.value._links?.espece?.href;
        const auteurUrl = orchidee.value._links?.auteur?.href;

        if (especeUrl) await fetchEspece(especeUrl);
        if (auteurUrl) await fetchAuteur(auteurUrl);
      } catch (error) {
        console.error("Erreur lors de la récupération des données :", error);
      }
    };

    onMounted(() => {
      fetchPhotosAndOrchidee();
      getPhoto(1);
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
