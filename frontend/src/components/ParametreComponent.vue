<template>
  <div class="profile-container">
    <header class="profile-header">
      <h1> {{ userName }}</h1>

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
      <div class="version-info">
        <div class="point-icon">
          <img src="@/assets/icons/points.png" alt="Point icon" />
        </div>
        <div class="text">{{ version }}</div>
      </div>

      <div class="version-info">
        <div class="logo-icon">
          <img src="@/assets/icons/logo.png" alt="Logo icon" />
        </div>
        <div class="text">{{ appName }}</div>
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
      userName: "",  // Initialisation vide
      profileImage: "",
      permissions: [
        { name: "localisation", label: "Localisation", granted: false },
        { name: "camera", label: "Caméra", granted: false },
        { name: "photos", label: "Photos", granted: false },
      ],
      version: "1.0.0",
      appName: "Les orchidées 2025",
      supportText: "Évaluation",
    };
  },
  created() {
    const user = JSON.parse(localStorage.getItem("user")); // Récupérer l'utilisateur
    if (user) {
      this.userName = user.nom; // Assure-toi que `nom` est la bonne propriété de ton objet utilisateur
    }
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

.version-info {
  display: grid;
  grid-template-columns: 20% 1px 80%; /* First column takes 20%, 1px gap, second column takes 80% */
  align-items: center;
  gap: 1px;
  justify-content: center; /* Align items to the left */
}

.point-icon,
.logo-icon {
  display: flex;
  align-items: center;
  justify-content: center;

}

.point-icon img {
  width: 2vh; /* Set width to 2vh */
  height: 4vh; /* Set height to 4vh */
}

.text {
  font-size: 2vh;
  margin: 2vh 0;
  text-align: left; /* Align text to the left */
  font-weight: bold;
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
    width: 1vh;
    height: 3vh;
  }
}
</style>
