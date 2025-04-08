import { defineStore } from 'pinia';

export const usePhotoStore = defineStore('photoStore', {
  state: () => ({
    photo: null
  }),
  actions: {
    setPhoto(photo) {
      this.photo = photo;
    },
    clearPhoto() {
      this.photo = null;
    }
  }
});
