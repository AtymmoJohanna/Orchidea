import { createRouter, createWebHistory } from "vue-router";
import StatView from "@/views/StatView.vue";
import CameraView from "@/views/CameraView.vue";

const routes = [
  { path: "/", redirect: "/stats" },
  { path: "/stats", component: StatView },
  { path: "/camera", component: CameraView },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
