import { createRouter, createWebHistory } from "vue-router";
import StatView from "@/views/StatView.vue";
import CameraView from "@/views/CameraView.vue";
import ParamView from '@/views/ParamView.vue';
import FormView from '@/views/FormView.vue'
import ChatView from '@/views/ChatView.vue'

const routes = [
  { path: "/", redirect: "/stats" },
  { path: "/stats", component: StatView },
  { path: "/camera", component: CameraView },
  { path: "/param", component: ParamView },
  { path: "/formulaire", component: FormView },
  { path: "/com", component: ChatView },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
