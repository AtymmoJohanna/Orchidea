<template>
  <div class="trust-section">
    <h3 class="trust-title">Ils nous ont fait confiance</h3>
    <div class="trust-logos">
      <img src="@/assets/insa.png" alt="Logo 1" class="trust-logo" />
      <img src="@/assets/inu-champollion.png" alt="Logo 2" class="trust-logo" />
      <img src="@/assets/logo(1).png" alt="Logo 3" class="trust-logo" />
    </div>
  </div>
  <div class="gallery-container">
    <h2 class="title">Galerie d'Orchidées</h2>

    <!-- 🔍 Search Bar -->
    <div class="search-bar">
      <input
        v-model="filterNomScientifique"
        type="text"
        placeholder="Rechercher par nom scientifique..."
        class="search-input"
      />
    </div>

    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>

    <div v-if="isPhotoLoading" class="loading-state">Chargement des orchidées...</div>

    <div v-else>
      <div
        v-for="orchidee in filteredOrchidees"
        :key="orchidee.id"
        class="orchidee-info"
      >
        <h3 class="section-title"> {{ orchidee.espece.nomScientifique }}
        </h3>

        <!-- 📸 Photos -->
        <div class="image-container">
          <div
            v-for="(photo, index) in orchidee.photos"
            :key="index"
            class="image-card"
          >
            <img
              :src="photo.url"
              alt="Image de l'orchidée"
              class="image"
            />
          </div>
          <div v-if="orchidee.photos.length === 0" class="no-photos">
            Aucune photo disponible.
          </div>
        </div>

        <!-- 🌱 Espece & ✍️ Auteur -->


        <!-- ✅ Autres infos -->
        <div class="info-grid">

          <p><strong>Motifs:</strong> {{ orchidee.motif?.join(', ') }}</p>
          <p><strong>État:</strong> {{ orchidee.etat }}</p>
          <p><strong>Var. Taxon:</strong> {{ orchidee.varTaxon }}</p>
          <p><strong>Nombre individus:</strong> {{ orchidee.nbreIndividu }}</p>
          <p><strong>Forme:</strong> {{ orchidee.forme }}</p>
          <p><strong>Couleurs:</strong> {{ orchidee.couleur?.join(', ') }}</p>
          <p><strong>Longitude:</strong> {{ orchidee.longitude }}</p>
          <p><strong>Latitude:</strong> {{ orchidee.latitude }}</p>
          <p><strong>Commentaire:</strong> {{ orchidee.commentaire }}</p>
          <p><strong>Date:</strong> {{ orchidee.dateEnreg }}</p>
          <p><strong>Auteur :</strong> {{ orchidee.auteur?.prenom }} {{ orchidee.auteur?.nom }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted, computed } from "vue";
import axios from "axios";

export default {
  setup() {
    const allOrchidees = ref([]);
    const isPhotoLoading = ref(true);
    const errorMessage = ref(null);
    const filterNomScientifique = ref("");

    const fetchAllOrchidees = async () => {
      try {
        const response = await axios.get("/api/orchidees", {
          headers: {"Cache-Control": "no-cache"},
        });

        const rawOrchidees = response.data._embedded?.orchidees || response.data;

        const enrichedOrchidees = await Promise.all(
          rawOrchidees.map(async (orchidee) => {
            try {
              const [photosRes, especeRes, auteurRes] = await Promise.all([
                axios.get(`/api/orchidees/${orchidee.id}/photos`),
                axios.get(`/api/orchidees/${orchidee.id}/espece`),
                axios.get(`/api/orchidees/${orchidee.id}/auteur`),
              ]);

              const photos = photosRes.data._embedded?.photos || photosRes.data;
              const espece = especeRes.data;
              const auteur = auteurRes.data;

              return {
                ...orchidee,
                photos,
                espece,
                auteur,
              };
            } catch (err) {
              console.error(`Erreur enrichissement orchidée ${orchidee.id}`, err);
              return {...orchidee, photos: [], espece: null, auteur: null};
            }
          })
        );

        allOrchidees.value = enrichedOrchidees;
      } catch (error) {
        console.error("Erreur chargement orchidées :", error);
        errorMessage.value = "Impossible de charger les orchidées.";
      } finally {
        isPhotoLoading.value = false;
      }
    };

    const filteredOrchidees = computed(() => {
      const search = filterNomScientifique.value.trim().toLowerCase();
      if (!search) return allOrchidees.value;

      return allOrchidees.value.filter((orchidee) =>
        orchidee.espece?.nomScientifique?.toLowerCase().includes(search)
      );
    });

    onMounted(fetchAllOrchidees);

    return {
      allOrchidees,
      filteredOrchidees,
      isPhotoLoading,
      errorMessage,
      filterNomScientifique,
    };
  },
};
</script>

<style scoped>
.gallery-container {
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

.search-bar {
  text-align: center;
  margin-bottom: 1rem;
}

.search-input {
  padding: 8px;
  font-size: 1rem;
  border-radius: 4px;
  width: 300px;
  border: 1px solid #ccc;
}

.loading-state, .no-photos, .error-message {
  text-align: center;
  font-size: 1.2rem;
  color: #888;
  padding: 10px;
}

.image-container {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
  margin-bottom: 1rem;
  justify-content: center; /* 👈 Ajoute cette ligne */
}


.image-card {
  width: 200px;
  height: 150px;
}

.image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
}

.orchidee-info {
  background: white;
  margin-bottom: 2rem;
  padding: 1rem;
  border-radius: 8px;
  box-shadow: 0 0 10px #ddd;
}

.section-title {
  color: #2C6B2F;
  font-size: 1.3rem;
  margin-bottom: 1rem;
}

.additional-info {
  margin-bottom: 1rem;
}

.info-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 0.5rem;
}

@media (max-width: 768px) {
  .info-grid {
    grid-template-columns: 1fr;
  }

  .image-card {
    width: 100%;
    height: auto;
  }
}
.trust-section {
  text-align: center;
  margin-bottom: 2rem;
}

.trust-title {
  font-size: 1.5rem;
  color: black;
  margin-bottom: 1rem;
}

.trust-logos {
  display: flex;
  justify-content: center;
  gap: 2rem;
  flex-wrap: wrap;
}

.trust-logo {
  height: 60px;
  object-fit: contain;
}

</style>
