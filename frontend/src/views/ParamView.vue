<template>
  <div>
    <h1>Parametre</h1>
    <ParametreComponent />
    <FooterVue />
  </div>
</template>

<script>
import ParametreComponent from "@/components/ParametreComponent.vue";
import FooterVue from "@/components/FooterVue.vue";

export default {
  components: {
    ParametreComponent,
    FooterVue,
  },
};
</script>
