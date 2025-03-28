<template>
  <form @submit.prevent="submitLogin" class="connexion-form">
    <h2>Bienvenue !</h2>
    <h3>Se connecter</h3>
    <label>
      Email*
      <input v-model="email" type="email" placeholder="Entrez votre email" required />
    </label>
    <label>
      Mot de passe*
      <input v-model="password" type="password" placeholder="Entrez votre mot de passe" required />
    </label>
    <button type="submit" :disabled="!email.trim() || !password.trim()">Se connecter</button>

  </form>

</template>

<script>

import axios from "axios";

import { useRouter } from "vue-router";
import { ref } from "vue";

export default {
  name: "Connexion",
  setup() {
    const router = useRouter();
    const email = ref("");
    const password = ref("");
    const message = ref("");

    const submitLogin = async () => {
  try {
    const response = await fetch("http://localhost:8989/api/utilisateurs/connexion", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ mail: email.value, pwd: password.value }),
    });

    if (!response.ok) {
      throw new Error("Échec de la connexion");
    }

    const user = await response.json();
    localStorage.setItem("user", JSON.stringify(user)); // Stocker l'utilisateur
    router.push("/"); // Rediriger vers ParametreComponent
  } catch (error) {
    console.error(error);
    alert("Email ou mot de passe incorrect !");
  }
};


    return { email, password, message, submitLogin };
  },
};
</script>


<style scoped>
.connexion-form {
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
  background-color: #2E7D32;
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
</style>
