

// main.js ou main.ts
import { createApp } from 'vue';
import App from './App.vue';

// Importer Vuetify
import vuetify from './plugins/vuetify';
import 'vuetify/styles'; // Importer les styles de Vuetify
import router from './router'

createApp(App).use(vuetify).use(router).mount('#app');
