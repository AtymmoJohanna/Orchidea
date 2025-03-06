import { createRouter, createWebHistory } from 'vue-router';
import MapsVue from '@/components/MapsVue.vue';
import CameraVue from '@/components/CameraVue.vue';

const routes = [
  { path: '/', component: MapsVue },
  { path: '/camera', component: CameraVue },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
