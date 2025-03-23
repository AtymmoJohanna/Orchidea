<template>
  <div class="param-view-container">
    <ParametreComponent class="param-component" />
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

<style scoped>
.param-view-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background-color: #dcead2; /* Set background color */
  position: relative;
}

.param-component {
  flex: 1; /* Make CameraComponent take all available space */
  height: 80vh;
}


</style>
