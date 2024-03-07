<script setup>
// import { RouterView } from "vue-router";
import TheHeadingNavbar from "@/components/layout/TheHeadingNavbar.vue";
</script>

<template>
	<q-layout view="hHh lpr fFf">
		<TheHeadingNavbar></TheHeadingNavbar>
		<!-- <router-view></router-view> -->
	</q-layout>
</template>

<style scoped>
body {
	margin: 0;
	width: 100%;
}

#app {
	margin: 0;
	width: 100%;
}
</style>
