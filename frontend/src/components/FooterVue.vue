<template>
  <div class="icon-list">
    <button @click="handleClick('stat')" class="icon-btn">
      <img :src="stat" alt="Stat Icon" class="icon-img" />
    </button>
    <button @click="handleClick('param')" class="icon-btn">
      <img :src="param" alt="Param Icon" class="icon-img" />
    </button>
    <button @click="handleClick('home')" class="icon-btn">
      <img :src="home" alt="Home Icon" class="icon-img" />
    </button>
    <button @click="handleClick('com')" class="icon-btn">
      <img :src="com" alt="Com Icon" class="icon-img" />
    </button>
    <button @click="handleClick('cam')" class="icon-btn">
      <img :src="cam" alt="Cam Icon" class="icon-img" />
    </button>
  </div>
</template>

<script>
export default {
  name: "IconList",
  data() {
    return {
      stat: require("@/assets/icons/stat.png"),
      param: require("@/assets/icons/param.png"),
      home: require("@/assets/icons/home.png"),
      com: require("@/assets/icons/com.png"),
      cam: require("@/assets/icons/cam.png")
    };
  },
  methods: {
    handleClick(icon) {
      console.log(`${icon} clicked`);
      // Ajouter ici ce qui doit se passer lors du clic sur chaque icône
    }
  }
};
</script>

<style scoped>
.icon-list {
  display: flex;
  justify-content: space-around;
  padding: 10px;
}

.icon-btn {
  background: transparent;
  border: none;
  cursor: pointer;
  padding: 10px;
}

.icon-img {
  width: 30px; /* Ajuster la taille des icônes selon tes besoins */
  height: 30px;
}

.icon-btn:hover .icon-img {
  opacity: 0.7; /* Effet au survol */
}
</style>
