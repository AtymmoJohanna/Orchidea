<template>
  <div class="profile-container">
    <header class="profile-header">
      <h1>{{ userName }}</h1>
      <div class="profile-image">
        <img :src="profileImage" alt="Profile Picture" />
      </div>
    </header>

    <section class="permissions">
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
        <img src="@/assets/icons/logo.png" alt="Logo icon" />
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
  max-width: 100%;
  box-sizing: border-box;
}

.profile-header {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.profile-header h1 {
  margin-bottom: 10px;
}

.profile-image img {
  width: 80px;
  height: 80px;
  border-radius: 50%;
}

.permissions,
.app-info,
.support {
  margin-top: 20px;
  background-color: white;
  padding: 15px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.permission-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.permission-item label {
  margin-left: 10px;
}

.app-info .point-icon,
.app-info .logo-icon {
  margin-top: 10px;
  text-align: center;
}

.app-info img,
.support a {
  display: block;
  margin: 10px auto;
}

/* Responsive Design for mobile devices */
@media (max-width: 768px) {
  .profile-header {
    flex-direction: column;
    align-items: center;
    text-align: center;
  }

  .profile-header h1 {
    font-size: 1.5em;
    margin-bottom: 10px;
  }

  .profile-image img {
    width: 100px;
    height: 100px;
  }

  .permission-item {
    font-size: 1.1em;
  }

  .permissions,
  .app-info,
  .support {
    padding: 15px;
    margin: 10px 0;
  }

  .app-info img {
    width: 50px;
    height: 50px;
  }
}

@media (max-width: 480px) {
  .profile-container {
    padding: 10px;
  }

  .permissions,
  .app-info,
  .support {
    padding: 10px;
  }

  .profile-image img {
    width: 80px;
    height: 80px;
  }

  .app-info img,
  .support a {
    width: 50px;
    height: 50px;
  }
}
</style>
