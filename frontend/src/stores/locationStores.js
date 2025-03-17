import { defineStore } from "pinia";
import { ref } from "vue";

export const useLocationStore = defineStore("locationStore", () => {
  const location = ref(null);

  const setLocation = (lat, lng) => {
    location.value = { latitude: lat, longitude: lng };
  };

  return { location, setLocation };
});
