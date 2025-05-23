<template>
  <div class="container chat-container">
    <div class="card">
      <h2 class="title">Donne-nous ton avis !</h2>

      <!-- Liste des avis -->
      <div v-for="(review, index) in reviews" :key="index" class="review-card">
        <div class="review-header">
          <span class="avatar">👤</span>
          <span class="name">{{ review.author }}</span>
          <span class="date">{{ review.date }}</span>
          <span class="heure">{{ review.heure }}</span>
        </div>
        <div class="review-content">
          {{ review.message }}
        </div>
      </div>

      <!-- Message de confirmation -->
      <p v-if="showConfirmation" class="confirmation-message">Merci ! Ton avis est enregistré.</p>

      <!-- Formulaire de chat -->
      <details class="expansion-panel" open>
        <summary>Rédiger un avis</summary>
        <div class="expansion-content">
          <form @submit.prevent="submitMessage">
            <label>
              Votre Avis
              <textarea v-model="message" rows="3" placeholder="Écrivez votre avis..." required></textarea>
            </label>
            <button type="submit" :disabled="!message.trim()">Valider</button>
          </form>
        </div>
      </details>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      message: "",
      showConfirmation: false,
      reviews: [],
      utilisateurId: 1, // Remplacer par l’ID réel de l’utilisateur connecté
    };
  },
  methods: {
    async fetchReviews() {
      try {
        const response = await axios.get("http://localhost:8989/api/avis");

        this.reviews = response.data.map(avis => ({
          author: avis.emetteurNom || "Utilisateur",
          date: new Date(avis.date).toLocaleDateString("fr-FR", { day: "2-digit", month: "short" }),
          heure: new Date(avis.date).toLocaleTimeString("fr-FR", { hour: "2-digit", minute: "2-digit", second: "2-digit" }),
          message: avis.commentaire,
        }));
      } catch (error) {
        console.error("Erreur lors de la récupération des avis :", error);
      }
    },

    async submitMessage() {
      if (!this.message.trim()) return;

      try {
        const avisDTO = {
          commentaire: this.message,
          emetteurId: this.utilisateurId,
        };

        const response = await axios.post("http://localhost:8989/api/avis", avisDTO, {
          headers: { "Content-Type": "application/json" },
        });

        if (response.status === 200 || response.status === 201) {
          this.message = "";
          this.showConfirmation = true;

          await this.fetchReviews();

          setTimeout(() => {
            this.showConfirmation = false;
          }, 2000);
        }
      } catch (error) {
        console.error("Erreur lors de l'enregistrement de l'avis :", error);
      }
    },
  },

  mounted() {
    this.fetchReviews();
  },
};
</script>



<style scoped>
.chat-container {
  background: linear-gradient(145deg, #f5f5f5 0%, #e8f5e9 100%);
  min-height: 50vh;
  padding: 2rem;
  display: flex;
  justify-content: center;
  align-items: center;
}

.card {
  background: #fff;
  padding: 1.5rem;
  border-radius: 8px;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
  text-align: center;
}

.title {
  color: #2E7D32;
  font-size: 1.4rem;
  font-weight: bold;
  margin-bottom: 1rem;
}

/* Carte de l'avis */
.review-card {
  background: #fff;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 1rem;
  margin: 0.5rem 0;
  text-align: left;
}

.review-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-weight: bold;
}

.avatar {
  font-size: 20px;
}

.review-content {
  background: #ebd3d3;
  padding: 10px;
  border-radius: 5px;
  margin-top: 5px;
}

/* Message de confirmation */
.confirmation-message {
  color: green;
  font-weight: bold;
  margin: 10px 0;
}

/* Expansion panel */
.expansion-panel {
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-top: 1rem;
  padding: 0.5rem;
}

.expansion-panel summary {
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

textarea {
  padding: 0.5rem;
  margin-top: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: none;
}

button {
  background-color: #2E7D32;
  color: #fff;
  padding: 0.75rem;
  border: none;
  border-radius: 4px;
  width: 90%;
  font-size: 1rem;
  cursor: pointer;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
</style>
