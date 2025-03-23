<template>
  <div class="chat-view-container">
    <ChatComponent class="chat-component"/>
    <FooterVue />
  </div>
</template>

<script>
import FooterVue from "@/components/FooterVue.vue";
import ChatComponent from '@/components/ChatComponent.vue'

export default {
  components: {
    ChatComponent,
    FooterVue,
  },
};
</script>

<style scoped>
.chat-view-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background-color: #dcead2; /* Set background color */
  position: relative;
}

.chat-component {
  flex: 1; /* Make CameraComponent take all available space */
}


</style>
