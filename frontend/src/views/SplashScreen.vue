<template>
  <div class="splash-view-container">
    <SplashScreen class="splash-component" />

  </div>

</template>

<script>
import SplashScreen from '@/components/SplashScreen.vue'

export default {
  components: {
    SplashScreen,
  },
};
</script>

<style scoped>
.spalsh-view-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background-color: #dcead2; /* Set background color */
  position: relative;
}

.spalsh-component {
  flex: 1; /* Make CameraComponent take all available space */
  height: 80vh;
}


</style>
