<script setup>
import apexchart from "vue3-apexcharts";
// -- les données à afficher
const seriesData = [
  { label: "Pomme", value: 44 },
  { label: "Mangue", value: 55 },
  { label: "Banane", value: 13 },
  { label: "Raisin", value: 43 },
  { label: "Orange", value: 22 },
];
const series = seriesData.map(item => item.value) // Extraction des valeurs
const labels = seriesData.map(item => item.label) // Extraction des labels

// -- les options du graphique
const options = {
  chart: {
    type : "pie", // type de graphique
  },
  labels: labels, // Extraction des labels
}
</script>
<template>
  <div class="pie-container">
  <div>
    <apexchart width="500" :options="options" :series="series"></apexchart>
  </div>
  </div>
</template>
<style>
.pie-container {
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
