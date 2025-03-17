<template>
  <div class="container info-container">
    <div class="card">
      <h2 class="title">Plus d'informations !</h2>
      <form @submit.prevent="submitForm">
        <div class="expansion-panels">
          <!-- Nom de l'orchidée -->
          <details class="expansion-panel">
            <summary>Nom de l'orchidée</summary>
            <div class="expansion-content">
              <label>
                Nom de l'orchidée
                <select v-model="orchidName" required>
                  <option value="" disabled>Choisissez une orchidée</option>
                  <option v-for="name in orchidNames" :key="name" :value="name">{{ name }}</option>
                </select>
              </label>
            </div>
          </details>

          <!-- État d’inflorescence -->
          <details class="expansion-panel">
            <summary>État d’inflorescence</summary>
            <div class="expansion-content">
              <label>
                État d’inflorescence
                <select v-model="influenceState" required>
                  <option value="" disabled>Choisissez un état</option>
                  <option v-for="state in influenceStates" :key="state" :value="state">{{ state }}</option>
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
                <input v-model="individualCount" type="number" min="0" required />
              </label>
            </div>
          </details>

          <!-- État général de la plante -->
          <details class="expansion-panel">
            <summary>État</summary>
            <div class="expansion-content">
              <label>
                État général de la plante
                <select v-model="plantState" required>
                  <option value="" disabled>Choisissez l'état</option>
                  <option v-for="state in plantStates" :key="state" :value="state">{{ state }}</option>
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
                <select v-model="color">
                  <option value="" disabled>Choisissez une couleur</option>
                  <option v-for="col in colors" :key="col" :value="col">{{ col }}</option>
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
                <select v-model="pattern">
                  <option value="" disabled>Choisissez un motif</option>
                  <option v-for="pat in patterns" :key="pat" :value="pat">{{ pat }}</option>
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
                <select v-model="shape">
                  <option value="" disabled>Choisissez une forme</option>
                  <option v-for="sh in shapes" :key="sh" :value="sh">{{ sh }}</option>
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
                <textarea v-model="comment" rows="3"></textarea>
              </label>
            </div>
          </details>
        </div>

        <button type="submit" :disabled="!formIsValid">Enregistrer</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      orchidName: '',
      influenceState: '',
      individualCount: '',
      plantState: '',
      color: '',
      pattern: '',
      shape: '',
      comment: '',
      orchidNames: ['Orchis militaris', 'Ophrys apifera', 'Cypripedium calceolus'],
      influenceStates: ['Bouton', 'Floraison', 'Fanée'],
      plantStates: ['Vigoureuse', 'Stressée', 'Malade', 'Morte'],
      colors: ['Blanc', 'Rose', 'Pourpre', 'Jaune', 'Vert', 'Bicolore'],
      patterns: ['Uni', 'Rayé', 'Tacheté', 'Dégradé'],
      shapes: ['Étoilé', 'En capuchon', 'En épi', 'En grappe']
    };
  },
  computed: {
    formIsValid() {
      return this.orchidName && this.influenceState && this.individualCount && this.plantState;
    }
  },
  methods: {
    submitForm() {
      const observationData = {
        orchid: this.orchidName,
        inflorescence: this.influenceState,
        count: Number(this.individualCount),
        state: this.plantState,
        characteristics: {
          color: this.color,
          pattern: this.pattern,
          shape: this.shape
        },
        notes: this.comment
      };

      console.log('Données validées :', observationData);
      // Placez ici votre logique d’envoi (par ex. axios.post)
    }
  }
};
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
