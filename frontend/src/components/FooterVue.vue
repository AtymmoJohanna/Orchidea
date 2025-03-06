<script setup>
import statIcon from '@/assets/stat.png';
import paramIcon from '@/assets/param.png';
import homeIcon from '@/assets/home.png';
import comIcon from '@/assets/com.png';
import camIcon from '@/assets/cam.png';

const handleClick = (icon) => {
  console.log(`${icon} clicked`);
};
</script>

<template>
  <div class="icon-list">
    <button @click="handleClick('stat')" class="icon-btn">
      <img :src="statIcon" alt="Stat Icon" class="icon-img" />
    </button>
    <button @click="handleClick('param')" class="icon-btn">
      <img :src="paramIcon" alt="Param Icon" class="icon-img" />
    </button>
    <button @click="handleClick('home')" class="icon-btn">
      <img :src="homeIcon" alt="Home Icon" class="icon-img" />
    </button>
    <button @click="handleClick('com')" class="icon-btn">
      <img :src="comIcon" alt="Com Icon" class="icon-img" />
    </button>
    <button @click="handleClick('cam')" class="icon-btn">
      <img src="@/assets/cam.png" @click="$router.push('/camera')" />
    </button>
  </div>
</template>

<style scoped>
.icon-list {
  display: flex;
  justify-content: space-around;
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
}

.icon-btn:hover .icon-img {
  opacity: 0.7;
}
</style>
