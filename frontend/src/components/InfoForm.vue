<template>
  <v-container class="info-form">
    <h2 class="form-title">Plus d'informations !</h2>

    <v-form @submit.prevent="submitForm">
      <v-row class="form-row">
        <v-col cols="12" v-for="(field, index) in fields" :key="index">
          <v-select
            v-model="field.model"
            :label="field.label"
            :items="field.items"
            variant="outlined"
            density="comfortable"
            class="custom-select"
            menu-icon="mdi-chevron-down"
          />
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-btn type="submit" color="primary" class="submit-button">Enregistrer</v-btn>
      </v-row>
    </v-form>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      // Champs du formulaire avec leurs labels et options
      fields: [
        { label: "Nom de l'orchidée", model: "", items: ['Orchidée 1', 'Orchidée 2', 'Orchidée 3'] },
        { label: "État d'inflorescence", model: "", items: ['Bouton', 'Floraison', 'Fanée'] },
        { label: "Nombre d'individus", model: "", items: [1, 2, 3, 4, 5, '6+'] },
        { label: "État", model: "", items: ['Sain', 'Malade'] },
        { label: "Couleur", model: "", items: ['Rouge', 'Bleu', 'Jaune', 'Vert'] },
        { label: "Motif", model: "", items: ['Rayé', 'Uni', 'Tacheté'] },
        { label: "Forme", model: "", items: ['Ronde', 'Allongée', 'Irrégulière'] },
        { label: "Commentaire", model: "", items: ['Bon état', 'Moyen', 'Mauvais'] },
      ],
    };
  },
  methods: {
    submitForm() {
      console.log('Formulaire soumis avec :', this.fields.map(field => ({ [field.label]: field.model })));
    },
  },
};
</script>

<style scoped>
.info-form {
  background-color: #dcead2;
  padding: 20px;
  border-radius: 10px;
}

.form-title {
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;
}

.form-row {
  display: flex;
  flex-direction: column;
}

.custom-select {
  background-color: white;
  border-radius: 10px;
  font-size: 16px;
  border: 1px solid black;
}

.submit-button {
  margin-top: 15px;
  background-color: #4caf50;
  color: white;
}
</style>
