<template>
  <div class="inscrit-view-container">
    <InscritComponent class="inscrit-component" />
    <FooterVue />
  </div>
</template>

<script>
import FooterVue from "@/components/FooterVue.vue";
import InscritComponent from '@/components/InscritComponent.vue'

export default {
  components: {
    InscritComponent,
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
