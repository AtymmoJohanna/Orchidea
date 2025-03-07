<template>
  <div class="profile-container">
    <header class="profile-header">
      <h1>{{ userName }}</h1>
      <div class="profile-image">
        <img :src="profileImage" alt="Profile Picture" />
      </div>
    </header>

    <section class="permissions" >
      <h2>Autorisations</h2>
      <div v-for="permission in permissions" :key="permission.name" class="permission-item">
        <input type="checkbox" v-model="permission.granted" />
        <label>{{ permission.label }}</label>
      </div>
    </section>

    <section class="app-info">
      <h2>Version</h2>
      <div>{{ version }}</div>
      <div class="point-icon">
        <img src="@/assets/icons/points.png" alt="Point icon" />
      </div>
      <div>{{ appName }}</div>
      <div class="logo-icon">
        <img src="@/assets/icons/logo.png" alt="logo icon" sizes="10" />
      </div>
    </section>

    <section class="support">
      <h2>Soutenez nous</h2>
      <a href="#evaluation">{{ supportText }}</a>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userName: 'Antonio Fauci',
      profileImage: '', // Changez le chemin vers votre image
      permissions: [
        { name: 'localisation', label: 'Localisation', granted: false },
        { name: 'camera', label: 'Caméra', granted: false },
        { name: 'photos', label: 'Photos', granted: false },
      ],
      version: '1.0.0',
      appName: 'Les orchidées 2025',
      supportText: 'Évaluation',
    };
  },
};
</script>

<style scoped>
.profile-container {
  background-color: #e8f5e9;
  padding: 20px;
  border-radius: 5px;
}

.profile-header {
  display: flex;
  align-items: center;
}
.section {
  background-color: white;  /* Définit la couleur de fond à blanc */
  border: 1px solid #ccc;   /* Optionnel : ajoute une bordure grise */
}

.profile-header h1 {
  margin-right: 20px;
}

.profile-image img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.permission-item {
  display: flex;
  align-items: center;
}

.app-info, .support {
  margin-top: 20px;
  background-color: white;  /* Définit la couleur de fond à blanc */
  border: 1px solid #ccc;   /* Optionnel : ajoute une bordure grise */
}
.permissions{
  background-color: white;
}
</style>
