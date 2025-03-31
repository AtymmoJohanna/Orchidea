<template>
  <div class="container info-container">
    <div class="card">
      <h2 class="title">Plus d'informations !</h2>
      <form @submit.prevent="soumettreFormulaire">
        <div class="expansion-panels">
          <!-- Nom de l'orchidée -->
          <details class="expansion-panel">
            <summary>Nom de l'orchidée</summary>
            <div class="expansion-content">
              <label>Nom de l'orchidée</label>
                <select v-model="userFormData.especeOrchidee" required>
                  <option value="" disabled>Choisissez une orchidée</option>
                  <option v-for="name in data.especesOrchidee" :key="name" :value="name">{{ name }}</option>
                </select>
            </div>
          </details>

          <!-- État d’inflorescence -->
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

          <!-- Nombre d’individus -->
          <details class="expansion-panel">
            <summary>Nombre d’individus</summary>
            <div class="expansion-content">
              <label>
                Nombre d’individus
                <!--<input v-model="data.nombresIndividus" type="number" min="0" required /> -->
                <select v-model="userFormData.nombreIndividus" required>
                  <option value="" disabled>Nombre d'individus</option>
                  <option v-for="nbre in data.nombresIndividus" :key="nbre" :value="nbre">{{ nbre }}</option>
                </select>
              </label>
            </div>
          </details>

          <!-- Variabilité taxon -->
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

          <!-- Couleur dominante -->
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

          <!-- Motif pétale -->
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

          <!-- Forme florale -->
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

          <!-- Commentaire -->
          <details class="expansion-panel">
            <summary>Commentaire</summary>
            <div class="expansion-content">
              <label>
                Notes d'observation
                <textarea v-model="userFormData.commentaire" rows="3"></textarea>
              </label>
            </div>
          </details>
        </div>

        <button @click="soumettreFormulaire">Enregistrer</button>
      </form>

      <!-- Messages de statut -->
      <div v-if="data.messageSucces" class="success-message">{{ data.messageSucces }}</div>
      <div v-if="data.messageErreur" class="error-message">{{ data.messageErreur }}</div>
    </div>
  </div>
</template>

<script setup>

import { onMounted, reactive } from "vue";
import axios from "axios";
import { useRoute } from 'vue-router'
import router from '@/router/index.js'
const route = useRoute();

// données saisies par l'utilisateur
const userFormData = reactive({
  especeOrchidee: '', // Nom de l'orchidée
  etatInflorescence: '', // État d’inflorescence
  nombreIndividus: '', // Nombre d’individus
  varTaxon: '', // Variabilité taxon
  couleur: '', // Couleur dominante
  motif: '', // Motif pétale
  forme: '', // Forme florale
  commentaire: '' // Commentaire
});

// Données recuperées du back
const data = reactive({
  especesOrchidee: [],
  etatsInflorescence: [],
  nombresIndividus: [],
  varTaxon: [],
  couleurs: [],
  motifs: [],
  formes: [],
  messageSucces: '',
  messageErreur: ''
});

const photo = route.params.photo ? JSON.parse(route.params.photo) : null;
let isSubmitting = false;
const soumettreFormulaire = async () => {
  if (isSubmitting) return; // Empêcher une deuxième soumission
  isSubmitting = true;
  try {

    // Créer un objet Orchidee à partir des données de l'utilisateur
    const newOrchidee = {
      commentaire: userFormData.commentaire,
      etat: userFormData.etatInflorescence,
      couleur: userFormData.couleur ? [userFormData.couleur] : [],
      varTaxon: userFormData.varTaxon,
      nbreIndividu: userFormData.nombreIndividus,
      motif: userFormData.motif ? [userFormData.motif] : [],
      forme: userFormData.forme,
      espece: userFormData.especeOrchidee,
      auteur: photo.auteur,
      latitude: 1,
      longitude: 1,
    };


    // Enregistrer l'objet Orchidee dans la base de données
    const orchideeResponse = await axios.post("/api/orchidees", newOrchidee,{ withCredentials: false });

    // Après avoir obtenu l'Orchidee enregistré, créer l'objet photo
    const photoData = {
      url: photo.url,
      auteur: photo.auteur,
      specimen: orchideeResponse.data, // Associer l'orchidée enregistrée à la photo
    };

    await axios.post("/api/photos", photoData, {
      headers: {
        "Content-Type": "application/json",
      },
    });

    router.replace({ params: { photo: null } });

    alert("Photo enregistrée avec succès !");
    router.push("home");
  } catch (error) {
    console.error("Erreur lors de l'enregistrement de la photo :", error);
    alert("Une erreur est survenue lors de l'enregistrement de la photo.");
  }finally {
    isSubmitting = false; // Réinitialiser après la requête
  }
};

// Fonction pour récupérer les données des enums
const getEnumData = async () => {
  try {
    // Remplace l'URL par ton API backend pour chaque enum
    const [especes, etatsInflorescence, nbreIndividus, varTaxon, couleurs, motifs, formes] = await Promise.all([
      axios.get("/api/especeOrchidees"),
      axios.get('/api/enums/etats-inflorescence'),
      axios.get('/api/enums/nbre-individus'),
      axios.get('/api/enums/variabilites-taxons'),
      axios.get('/api/enums/couleurs'),
      axios.get('/api/enums/motifs'),
      axios.get('/api/enums/formes')
    ]);

    // Remplir les données dans le modèle réactif
    //data.especesOrchidee = especes.data.map(e => e.nomScientifique);
    data.especesOrchidee = especes.data.map(espece => ({
      code: espece.code,
      nomScientifique: espece.nomScientifique
    }));
    console.log(especes.data);
    data.etatsInflorescence = etatsInflorescence.data;
    data.nombresIndividus = nbreIndividus.data;
    data.varTaxon = varTaxon.data;
    data.couleurs = couleurs.data;
    data.motifs = motifs.data;
    data.formes = formes.data;

  } catch (error) {
    console.error('Erreur lors de la récupération des enums:', error);
    data.messageErreur = 'Impossible de charger les options. Veuillez réessayer plus tard.';
  }
};

// Utiliser onMounted pour charger les enums au chargement du composant
onMounted(() => {
  getEnumData();
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
  box-shadow: 0px 2px 4px rgba(0,0,0,0.1);
  width: 100%;
  max-width: 600px;
}

.title {
  color: #2E7D32;
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

input, select, textarea {
  padding: 0.5rem;
  margin-top: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  background-color: #2E7D32;
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
</style>
