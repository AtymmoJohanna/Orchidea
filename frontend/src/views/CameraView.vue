<template>
  <div class="camera-view-container">
    <CameraComponent class="camera-component"/>
    <FooterVue />
  </div>
</template>

<script>
import FooterVue from "@/components/FooterVue.vue";
import CameraComponent from '@/components/CameraComponent.vue'

export default {
  components: { CameraComponent, FooterVue },
};
</script>

<style scoped>
.camera-view-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background-color: #dcead2; /* Set background color */
  position: relative;
}

.camera-component {
  flex: 1; /* Make CameraComponent take all available space */
}


</style>
