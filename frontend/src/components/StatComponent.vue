<script setup>
import apexchart from "vue3-apexcharts";
// -- les series de données à afficher
const series = [
  {
    name: "Ventes",
    data: [ { x: "Jan", y: 10 }, { x: "Fév", y: 20 },{ x: "Mar", y: 15 },
      { x: "Avr", y: 25 }, { x: "Mai", y: 30 }, { x: "Juin", y: 40 }
    ]
  },
  {
    name: "Achats",
    data: [ { x: "Jan", y: 60 }, { x: "Fév", y: 20 },{ x: "Mar", y: 30 },
      { x: "Avr", y: 10 }, { x: "Mai", y: 50 }, { x: "Juin", y: 10 }
    ]
  }
];
// -- les options du graphique
const options = {
  chart: {
    type : "line", // type de graphique
  },
  stroke: {
    curve: 'smooth', // type de ligne
  },
  markers: {
    size: 5, // taille des marqueurs à chaque point
  },
}
</script>

<template>
 <div class="stat-container">
  <div>
    <apexchart width="500" :options="options" :series="series"></apexchart>
  </div>
 </div>>
</template>
<style>
.stat-container {
  position: relative;
  padding-bottom: 60px;
  background-color: #dcead2;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}
</style>
