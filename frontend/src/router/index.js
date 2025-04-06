import { createRouter, createWebHistory } from "vue-router";
import StatView from "@/views/StatView.vue";
import CameraView from "@/views/CameraView.vue";
import ParamView from '@/views/ParamView.vue';
import FormView from '@/views/FormView.vue'
import ChatView from '@/views/ChatView.vue'
import HomeView from '@/views/HomeView.vue'
import InscritView from '@/views/InscritView.vue'
import ConexView from '@/views/ConexView.vue'
import SplashScreen from '@/views/SplashScreen.vue'

const routes = [
  { path: "/", component: SplashScreen },
  { path: "/inscrit", component: InscritView },
  { path: "/stats", component: StatView },
  { path: "/camera", component: CameraView },
  { path: "/param", component: ParamView },
  { path: "/formulaire/:photo?", name: "formulaire", component: FormView, props: true },
  { path: "/com", component: ChatView },
  {path: "/home", component: HomeView },
  { path: "/connexion", component: ConexView },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
