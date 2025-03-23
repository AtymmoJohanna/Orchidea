<template>
  <div class="stat-view-container">
    <MapsComponent class="stat-component"/>
    <StatComponent class="stat-component"/>
    <PieComponent class="stat-component"/><!-- MapsVue fait partie de StatView -->
    <FooterVue />
  </div>
</template>

<script>
import MapsComponent from "@/components/MapsComponent.vue";
import FooterVue from "@/components/FooterVue.vue";
import StatComponent from '@/components/StatComponent.vue';
import PieComponent from '@/components/PieComponent.vue'

export default {
  components: {

    MapsComponent,
    StatComponent,
    PieComponent,
    FooterVue,
  },
};
</script>

<style scoped>
.stat-view-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background-color: #dcead2; /* Set background color */
  position: relative;
}

.stat-component {
  flex: 1; /* Make CameraComponent take all available space */
}


</style>
