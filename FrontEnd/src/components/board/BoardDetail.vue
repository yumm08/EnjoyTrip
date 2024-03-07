<script setup>
import { useRoute } from "vue-router";

const route = useRoute();

const { listVal } = route.params;
</script>

<template>
    <div>
        {{ listVal }}
    </div>
</template>

<style scoped></style>