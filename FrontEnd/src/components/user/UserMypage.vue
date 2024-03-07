<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

const moveDetail = () => {
    router.push({ name: "user-detail" });
};
</script>

<template>
    <div>
        <button @click="moveDetail">마이페이지로 이동</button>
    </div>
</template>

<style scoped>

</style>