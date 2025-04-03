<template>
  <div>
    <InfoForm />
    <FooterVue />
    <router-view :key="$route.fullPath"></router-view>

  </div>
</template>

<script>
import InfoForm from "@/components/InfoForm.vue";
import FooterVue from "@/components/FooterVue.vue";

export default {
  components: {
    InfoForm,
    FooterVue,
  },
};
</script>
