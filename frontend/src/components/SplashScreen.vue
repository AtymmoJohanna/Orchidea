<!-- src/views/SplashPage.vue -->
<template>
  <div class="splash-screen">
    <img src="@/assets/icons/logo.png" alt="Logo" class="logo" />
    <h1 class="app-name">OrchIdea</h1>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

onMounted(() => {
  setTimeout(() => {
    router.push('/inscrit') // redirection vers /home
  }, 5000)
})
</script>

<style scoped>
.splash-screen {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  z-index: 9999;
}

.logo {
  width: 120px;
  height: 120px;
  margin-bottom: 20px;
}

.app-name {
  font-size: 24px;
  font-weight: bold;
  color: #008000;
}
</style>
