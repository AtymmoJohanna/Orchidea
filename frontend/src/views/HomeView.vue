<template>
  <div>
    <InfoForm />
    <FooterVue />
  </div>
</template>

<script>
import FooterVue from "@/components/FooterVue.vue";
import homeComponent from '@/components/HomeComponent.vue'

export default {
  components: {
    homeComponent,
    FooterVue,
  },
};
</script>
