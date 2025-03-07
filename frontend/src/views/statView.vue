<template>
  <div>
    <h1>Statistiques</h1>
    <MapsComponent /> <!-- MapsVue fait partie de StatView -->
    <FooterVue />
  </div>
</template>

<script>
import MapsComponent from "@/components/MapsComponent.vue";
import FooterVue from "@/components/FooterVue.vue";

export default {
  components: {
    MapsComponent,
    FooterVue,
  },
};
</script>
