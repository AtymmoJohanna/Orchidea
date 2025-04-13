<template>
  <div class="stat-view-container">
    <MapsComponent class=" maps-component"/>

    <PieComponent class="stat-component"/>
    <EtatStat class="stat-component"/>
    <TaxonStat class="stat-component"/>
    <MotifStat class="stat-component"/>
    <IndividuStat class="stat-component"/>
    <FormStat class="stat-component"/>
    <FooterVue />
  </div>
</template>

<script>
import MapsComponent from "@/components/MapsComponent.vue";
import FooterVue from "@/components/FooterVue.vue";
import PieComponent from '@/components/PieComponent.vue';
import EtatStat from "@/components/EtatStat.vue";
import TaxonStat from "@/components/TaxonStat.vue";
import MotifStat from "@/components/MotifStat.vue";
import IndividuStat from "@/components/IndividuStat.vue";
import FormStat from "@/components/FormStat.vue";

export default {
  components: {
    FormStat,
    IndividuStat,
    MotifStat,
    TaxonStat,
    EtatStat,
    MapsComponent,
    PieComponent,
    FooterVue,
  },
};
</script>

<style scoped>
.stat-view-container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  min-height: 100vh;
  background-color: #dcead2;
  position: relative;
  justify-content: space-between;
  padding: 10px;
}

.stat-component {

  margin: 10px;
  min-height: 200px;
  box-sizing: border-box;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* Règle spécifique pour MapsComponent */
.maps-component {
  flex: 0 0 calc(100% - 20px);
  margin: 10px;
  height: 60vh;
  box-sizing: border-box;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* S'assurer que le footer prend toute la largeur et reste en bas */
.stat-view-container > :last-child {
  flex: 0 0 100%;
  margin: 0;
}

/* Responsive pour petits écrans */
@media (max-width: 768px) {
  .stat-component {
    flex: 0 0 calc(100% - 20px); /* 1 par ligne sur petits écrans */
  }
}
</style>
