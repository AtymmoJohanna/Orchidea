<template>
  <div >
    <MapsComponent />
    <StatComponent />
    <PieComponent /><!-- MapsVue fait partie de StatView -->
    <FooterVue />
  </div>
</template>

<script>
import MapsComponent from "@/components/MapsComponent.vue";
import FooterVue from "@/components/FooterVue.vue";
import StatComponent from '@/components/StatComponent.vue'
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
