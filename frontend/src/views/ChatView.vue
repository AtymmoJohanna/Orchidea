<template>
  <div>
    <h1>Chat</h1>
    <ChatComponent />
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
