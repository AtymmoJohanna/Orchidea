<template>
  <div class="home-view-container">
    <HomeComponent />
    <FooterVue />
  </div>
</template>

<script>
import FooterVue from "@/components/FooterVue.vue";
import HomeComponent from '@/components/HomeComponent.vue'

export default {
  components: {
    HomeComponent,
    FooterVue,
  },
};
</script>
<style scoped>
.home-view-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background-color: #dcead2; /* Set background color */
  position: relative;
}

.inscrit-component {
  flex: 1; /* Make CameraComponent take all available space */
  height: 80vh;
}
</style>
