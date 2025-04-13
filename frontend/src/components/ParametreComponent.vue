<template>
  <div class="profile-container">
    <header class="profile-header">
      <h1>{{ userName }}</h1>
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
        <img class="icon" src="@/assets/icons/points.png" alt="Point icon" />
        <span class="text">{{ version }}</span>
      </div>
      <div class="version-info">
        <img class="icon-orche" src="@/assets/icons/logo.png" alt="Logo icon" />
        <span class="text">{{ appName }}</span>
      </div>
    </section>

    <section class="support">
      <h2>Soutenez-nous</h2>
      <a href="#evaluation">{{ supportText }}</a>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userName: "",
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
    const user = JSON.parse(localStorage.getItem("user"));
    if (user) {
      this.userName = user.nom;
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
  justify-content: center;
  text-align: center;
}

.profile-header h1 {
  margin: 0;
  font-size: 1.8em;
  font-weight: bold;
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
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.icon {
  width: 7px;
  height: 25px;
  margin-right: 10px;
}
.icon-orche {
  width: 20px;
  height: 20px;
  margin-right: 10px;
}
.text {
  font-size: 1rem;
  font-weight: bold;
  text-align: left;
}

.support a {
  display: inline-block;
  margin-top: 10px;
  color: #33691e;
  text-decoration: underline;
}
</style>
