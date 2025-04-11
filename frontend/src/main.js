

// main.js ou main.ts
import { createApp } from 'vue';
import App from './App.vue';

// Importer Vuetify
import vuetify from './plugins/vuetify';
import 'vuetify/styles'; // Importer les styles de Vuetify
import router from './router'
import axios from "axios";
// Configurer l'URL de base pour Axios
axios.defaults.baseURL = 'http://localhost:8989';

createApp(App).use(vuetify).use(router).mount('#app');
