<template>
  <form @submit.prevent="inscrireUtilisateur" class="inscription-form">
    <h2>Bienvenue !</h2>
    <h3>Créer un compte</h3>

    <label>
      Nom*
      <input v-model="nom" type="text" placeholder="Entrez votre nom" required />
    </label>
    <label>
      Prénom*
      <input v-model="prenom" type="text" placeholder="Entrez votre prénom" required />
    </label>
    <label>
      Email*
      <input v-model="email" type="email" placeholder="Entrez votre email" required />
    </label>
    <label>
      Mot de passe*
      <input v-model="password" type="password" placeholder="Entrez votre mot de passe" required />
    </label>

    <button type="submit" :disabled="!nom.trim() || !prenom.trim() || !email.trim() || !password.trim()">Créer un compte</button>
    <button type="button" @click="navigateTo('/connexion')">Vous avez un compte ?</button>

    <p v-if="message" class="message">{{ message }}</p>
  </form>
</template>

<script>
import axios from "axios";
import { useRouter } from "vue-router";
import { ref } from "vue";

export default {
  name: "Inscription",
  setup() {
    const router = useRouter();
    const nom = ref("");
    const prenom = ref("");
    const email = ref("");
    const password = ref("");

    const message = ref("");

    const navigateTo = (path) => {
      router.push(path);
    };

    const inscrireUtilisateur = async () => {
  try {
    await axios.post("http://localhost:8989/api/utilisateurs/inscription", {
      nom: nom.value,
      prenom: prenom.value,
      mail: email.value,
      pwd: password.value
    });

    message.value = "Compte créé avec succès ! Redirection...";
    setTimeout(() => navigateTo("/connexion"), 2000);
  } catch (error) {
    message.value = error.response?.data || "Erreur lors de l'inscription";
  }
};


    return { nom, prenom, email, password, message, navigateTo, inscrireUtilisateur };
  },
};
</script>

<style scoped>
.inscription-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 400px;
  margin: auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h2 {
  color: #333;
  font-size: 24px;
  margin-bottom: 10px;
}

h3 {
  color: #555;
  font-size: 18px;
  margin-bottom: 20px;
}

label {
  display: flex;
  flex-direction: column;
  font-weight: 500;
  margin-bottom: 1rem;
  width: 100%;
}

input {
  padding: 0.5rem;
  margin-top: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 100%;
}

button {
  background-color: #4caf50;
  color: #fff;
  padding: 0.75rem;
  border: none;
  border-radius: 4px;
  width: 100%;
  font-size: 1rem;
  cursor: pointer;
  margin-top: 10px;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.message {
  margin-top: 15px;
  color: green;
  font-weight: bold;
  font-size: 14px;
}
</style>
