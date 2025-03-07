import { createRouter, createWebHistory } from "vue-router";
import StatView from "@/views/StatView.vue";
import CameraView from "@/views/CameraView.vue";
import ParamView from '@/views/ParamView.vue';

const routes = [
  { path: "/", redirect: "/stats" },
  { path: "/stats", component: StatView },
  { path: "/camera", component: CameraView },
  { path: "/param", component: ParamView },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
