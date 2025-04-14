<template>
  <div class="container info-container">
    <div class="card">
      <h2 class="title">Plus d'informations !</h2>

      <!-- Aperçu des photos -->
      <div class="photo-preview" v-if="photos.length">
        <h3>Photos sélectionnées :</h3>
        <div class="image-gallery">
          <div v-for="(photo, index) in photos" :key="index" class="image-container">
            <img :src="photo.url" alt="Photo" class="preview-image" />
            <button class="delete-button" @click="removePhoto(index)">❌</button>
          </div>
        </div>
      </div>

      <!-- Message de chargement ou d'erreur -->
      <div v-if="isLoadingEnums" class="loading-message">Chargement des options...</div>
      <div v-else-if="data.messageErreur && !data.especesOrchidee.length" class="error-message">
        {{ data.messageErreur }}
      </div>

      <form @submit.prevent="soumettreFormulaire">
        <div class="expansion-panels">
          <details class="expansion-panel">
            <summary>Famille</summary>
            <div class="expansion-content">
              <label>Famille</label>
              <select v-model="userFormData.especeOrchidee" required>
                <option value="" disabled>Choisissez une Famille</option>
                <option v-for="name in data.especesOrchidee" :key="name" :value="name">{{ name }}</option>
              </select>
            </div>
          </details>

          <details class="expansion-panel">
            <summary>État d’inflorescence</summary>
            <div class="expansion-content">
              <label>
                État d’inflorescence
                <select v-model="userFormData.etatInflorescence" required>
                  <option value="" disabled>Choisissez un état</option>
                  <option v-for="etat in data.etatsInflorescence" :key="etat" :value="etat">{{ etat }}</option>
                </select>
              </label>
            </div>
          </details>

          <details class="expansion-panel">
            <summary>Nombre d’individus</summary>
            <div class="expansion-content">
              <label>
                Nombre d’individus
                <select v-model="userFormData.nombreIndividus" required>
                  <option value="" disabled>Nombre d'individus</option>
                  <option v-for="nbre in data.nombresIndividus" :key="nbre" :value="nbre">{{ nbre }}</option>
                </select>
              </label>
            </div>
          </details>

          <details class="expansion-panel">
            <summary>État</summary>
            <div class="expansion-content">
              <label>
                Variabilité taxon
                <select v-model="userFormData.varTaxon" required>
                  <option value="" disabled>Variabilité taxon</option>
                  <option v-for="etat in data.varTaxon" :key="etat" :value="etat">{{ etat }}</option>
                </select>
              </label>
            </div>
          </details>

          <details class="expansion-panel">
            <summary>Couleur</summary>
            <div class="expansion-content">
              <label>
                Couleur dominante
                <select v-model="userFormData.couleur" required>
                  <option value="" disabled>Choisissez les couleurs</option>
                  <option v-for="col in data.couleurs" :key="col" :value="col">{{ col }}</option>
                </select>
              </label>
            </div>
          </details>

          <details class="expansion-panel">
            <summary>Motif</summary>
            <div class="expansion-content">
              <label>
                Motif pétale
                <select v-model="userFormData.motif" required>
                  <option value="" disabled>Choisissez un motif</option>
                  <option v-for="pat in data.motifs" :key="pat" :value="pat">{{ pat }}</option>
                </select>
              </label>
            </div>
          </details>

          <details class="expansion-panel">
            <summary>Forme</summary>
            <div class="expansion-content">
              <label>
                Forme florale
                <select v-model="userFormData.forme" required>
                  <option value="" disabled>Choisissez une forme</option>
                  <option v-for="sh in data.formes" :key="sh" :value="sh">{{ sh }}</option>
                </select>
              </label>
            </div>
          </details>

          <details class="expansion-panel">
            <summary>Espéce/Commentaire</summary>
            <div class="expansion-content">
              <label>
                Notes d'observation
                <textarea v-model="userFormData.commentaire" rows="3"></textarea>
              </label>
            </div>
          </details>
        </div>

        <button type="submit" :disabled="isSubmitting || !data.especesOrchidee.length">
          Enregistrer
        </button>
      </form>

      <div v-if="data.messageSucces" class="success-message">{{ data.messageSucces }}</div>
      <div v-if="data.messageErreur && data.especesOrchidee.length" class="error-message">
        {{ data.messageErreur }}
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

const router = useRouter();

const userFormData = reactive({
  especeOrchidee: "",
  etatInflorescence: "",
  nombreIndividus: "",
  varTaxon: "",
  couleur: "",
  motif: "",
  forme: "",
  commentaire: "",
  latitude: null,
  longitude: null,
});

const data = reactive({
  especesOrchidee: [],
  etatsInflorescence: [],
  nombresIndividus: [],
  varTaxon: [],
  couleurs: [],
  motifs: [],
  formes: [],
  messageSucces: "",
  messageErreur: "",
});

const photos = ref([]);
const isSubmitting = ref(false);
const isLoadingEnums = ref(false);

const resetFormData = () => {
  Object.assign(userFormData, {
    especeOrchidee: "",
    etatInflorescence: "",
    nombreIndividus: "",
    varTaxon: "",
    couleur: "",
    motif: "",
    forme: "",
    commentaire: "",
    latitude: null,
    longitude: null,
  });
  console.log("Formulaire réinitialisé");
};

const getLocation = () => {
  return new Promise((resolve, reject) => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          userFormData.latitude = position.coords.latitude;
          userFormData.longitude = position.coords.longitude;
          resolve();
        },
        (error) => {
          console.error("Erreur de géolocalisation :", error);
          reject(new Error("Impossible de récupérer votre position."));
        },
        { enableHighAccuracy: true, timeout: 5000, maximumAge: 0 }
      );
    } else {
      reject(new Error("La géolocalisation n'est pas supportée."));
    }
  });
};

const removePhoto = (index) => {
  photos.value.splice(index, 1);
  localStorage.setItem("photos", JSON.stringify(photos.value));
  console.log("Photo supprimée, restantes :", photos.value.length);
};

const soumettreFormulaire = async () => {
  if (isSubmitting.value) return;
  isSubmitting.value = true;

  if (!photos.value.length) {
    data.messageErreur = "Aucune photo sélectionnée.";
    isSubmitting.value = false;
    return;
  }

  try {
    await getLocation();
    console.log("Géolocalisation récupérée :", userFormData.latitude, userFormData.longitude);

    const newOrchidee = {
      commentaire: userFormData.commentaire || "",
      etat: userFormData.etatInflorescence,
      couleur: userFormData.couleur ? [userFormData.couleur] : [],
      varTaxon: userFormData.varTaxon,
      nbreIndividu: userFormData.nombreIndividus,
      motif: userFormData.motif ? [userFormData.motif] : [],
      forme: userFormData.forme,
      espece: userFormData.especeOrchidee,
      auteur: photos.value[0].auteur,
      latitude: userFormData.latitude,
      longitude: userFormData.longitude,
    };
    console.log("Nouvelle orchidée à envoyer :", newOrchidee);

    const orchideeResponse = await axios.post("/api/orchidees", newOrchidee, {
      withCredentials: false,
    });
    console.log("Orchidée enregistrée, ID :", orchideeResponse.data.id);

    for (const photo of photos.value) {
      const photoData = {
        url: photo.url,
        auteur: photo.auteur,
        specimen: orchideeResponse.data,
      };
      console.log("Envoi de la photo :", photoData);
      await axios.post("/api/photos", photoData);
    }

    localStorage.removeItem("photos");
    resetFormData();
    photos.value = [];

    data.messageSucces = "Orchidée et photos enregistrées avec succès !";
    router.push("/home");
  } catch (error) {
    console.error("Erreur lors de l'enregistrement :", error);
    data.messageErreur =
      error.response?.data?.message || "Erreur lors de l'enregistrement.";
  } finally {
    isSubmitting.value = false;
  }
};

const getEnumData = async () => {
  isLoadingEnums.value = true;
  data.messageErreur = ""; // Réinitialiser le message d'erreur

  try {
    // Charger chaque endpoint séquentiellement
    console.log("Chargement des espèces...");
    const especes = await axios.get("/api/especeOrchidees", {
      headers: { "Cache-Control": "no-cache" },
    });
    data.especesOrchidee = especes.data.map(espece => ({
      code: espece.code,
      nomScientifique: espece.nomScientifique
    }));
    console.log("Espèces chargées :", data.especesOrchidee);

    console.log("Chargement des états d'inflorescence...");
    const etatsInflorescence = await axios.get("/api/enums/etats-inflorescence", {
      headers: { "Cache-Control": "no-cache" },
    });
    data.etatsInflorescence = etatsInflorescence.data;
    console.log("États d'inflorescence chargés :", data.etatsInflorescence);

    console.log("Chargement des nombres d'individus...");
    const nbreIndividus = await axios.get("/api/enums/nbre-individus", {
      headers: { "Cache-Control": "no-cache" },
    });
    data.nombresIndividus = nbreIndividus.data;
    console.log("Nombres d'individus chargés :", data.nombresIndividus);

    console.log("Chargement des variabilités taxons...");
    const varTaxon = await axios.get("/api/enums/variabilites-taxons", {
      headers: { "Cache-Control": "no-cache" },
    });
    data.varTaxon = varTaxon.data;
    console.log("Variabilités taxons chargées :", data.varTaxon);

    console.log("Chargement des couleurs...");
    const couleurs = await axios.get("/api/enums/couleurs", {
      headers: { "Cache-Control": "no-cache" },
    });
    data.couleurs = couleurs.data;
    console.log("Couleurs chargées :", data.couleurs);

    console.log("Chargement des motifs...");
    const motifs = await axios.get("/api/enums/motifs", {
      headers: { "Cache-Control": "no-cache" },
    });
    data.motifs = motifs.data;
    console.log("Motifs chargés :", data.motifs);

    console.log("Chargement des formes...");
    const formes = await axios.get("/api/enums/formes", {
      headers: { "Cache-Control": "no-cache" },
    });
    data.formes = formes.data;
    console.log("Formes chargées :", data.formes);

    console.log("Enums chargés avec succès");
  } catch (error) {
    console.error("Erreur lors de la récupération des enums :", error);
    data.messageErreur = "Impossible de charger les options. Vérifiez votre connexion ou le serveur.";
  } finally {
    isLoadingEnums.value = false;
  }
};

onMounted(async () => {
  console.log("Début du montage de InfoForm");
  resetFormData();
  photos.value = [];

  try {
    const storedPhotos = localStorage.getItem("photos");
    if (storedPhotos) {
      photos.value = JSON.parse(storedPhotos);
      console.log("Photos chargées depuis localStorage :", photos.value);
    } else {
      console.log("Aucune photo dans localStorage");
    }

    if (!photos.value.length) {
      data.messageErreur = "Aucune photo trouvée.";
      console.log("Redirection vers CameraComponent : aucune photo");
      router.push("/camera");
      return;
    }

    await getEnumData();
    console.log("Montage de InfoForm terminé");
  } catch (error) {
    console.error("Erreur dans onMounted :", error);
    data.messageErreur = "Erreur lors du chargement des données.";
  }
});
</script>

<style scoped>
.info-container {
  background: linear-gradient(145deg, #f5f5f5 0%, #e8f5e9 100%);
  min-height: 100vh;
  padding: 2rem;
  display: flex;
  justify-content: center;
  align-items: center;
}

.card {
  background: #fff;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 600px;
}

.title {
  color: #2e7d32;
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 1.5rem;
}

.expansion-panels details {
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-bottom: 1rem;
  padding: 0.5rem;
}

.expansion-panels summary {
  font-weight: bold;
  cursor: pointer;
  outline: none;
}

.expansion-content {
  margin-top: 0.5rem;
}

label {
  display: flex;
  flex-direction: column;
  font-weight: 500;
  margin-bottom: 1rem;
}

input,
select,
textarea {
  padding: 0.5rem;
  margin-top: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  background-color: #2e7d32;
  color: #fff;
  padding: 0.75rem;
  border: none;
  border-radius: 4px;
  width: 100%;
  font-size: 1rem;
  cursor: pointer;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.photo-preview {
  margin-bottom: 1.5rem;
}

.image-gallery {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.image-container {
  position: relative;
}

.preview-image {
  max-width: 100px;
  max-height: 100px;
  object-fit: cover;
  border-radius: 4px;
}

.delete-button {
  position: absolute;
  top: 5px;
  right: 5px;
  background: white;
  color: red;
  border: 2px solid red;
  cursor: pointer;
  font-size: 14px;
  border-radius: 50%;
  width: 24px;
  height: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.success-message {
  color: #2e7d32;
  margin-top: 1rem;
  text-align: center;
}

.error-message {
  color: red;
  margin-top: 1rem;
  text-align: center;
}

.loading-message {
  color: #2e7d32;
  margin-bottom: 1rem;
  text-align: center;
}
</style>
