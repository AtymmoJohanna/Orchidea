<template>
  <v-app>
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import MapsVue from "@/components/HomeComponent.vue";


export default {
  components: {
    MapsVue,
  },
};
</script>



