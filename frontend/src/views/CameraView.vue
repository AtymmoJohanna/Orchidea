<template>
  <div>
    <CameraComponent />
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
