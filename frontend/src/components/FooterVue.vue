<script setup>
import { useRouter } from 'vue-router';

import statIcon from '@/assets/stat.png';
import paramIcon from '@/assets/param.png';
import homeIcon from '@/assets/home.png';
import comIcon from '@/assets/com.png';
import camIcon from '@/assets/cam.png';

const router = useRouter();

const navigateTo = (path) => {
  router.push(path);
};
</script>

<template>
  <div class="icon-list">
    <button @click="navigateTo('/stats')" class="icon-btn">
      <img :src="statIcon" alt="Stat Icon" class="icon-img" />
    </button>
    <button @click="navigateTo('/param')" class="icon-btn">
      <img :src="paramIcon" alt="Param Icon" class="icon-img" />
    </button>
    <button @click="navigateTo('/')" class="icon-btn">
      <img :src="homeIcon" alt="Home Icon" class="icon-img" />
    </button>
    <button @click="navigateTo('/com')" class="icon-btn">
      <img :src="comIcon" alt="Com Icon" class="icon-img" />
    </button>
    <button @click="navigateTo('/camera')" class="icon-btn">
      <img :src="camIcon" alt="Cam Icon" class="icon-img" />
    </button>
  </div>
</template>

<style scoped>
.icon-list {
  display: flex;
  justify-content: space-around;
  background: #008000;
  padding: 10px;
}

.icon-btn {
  background: transparent;
  border: none;
  cursor: pointer;
  padding: 10px;
}

.icon-img {
  width: 30px;
  height: 30px;
  filter: brightness(0) invert(1); /* Rend les icônes blanches sur fond vert */
  transition: transform 0.2s ease-in-out;
}

.icon-btn:hover .icon-img {
  transform: scale(1.1);
  opacity: 0.8;
}
</style>
