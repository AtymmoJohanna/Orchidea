<template>
  <div class="inscrit-view-container">
    <ConnexionComponent class="inscrit-component" />
    <FooterVue />
  </div>
</template>

<script>
import FooterVue from "@/components/FooterVue.vue";
import ConnexionComponent from '@/components/ConnexionComponent.vue'

export default {
  components: {
    ConnexionComponent,
    FooterVue,
  },
};
</script>

<style scoped>
.inscrit-view-container {
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
