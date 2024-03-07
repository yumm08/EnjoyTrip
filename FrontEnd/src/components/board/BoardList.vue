<script setup>
import { ref } from "vue";
// import { useRouter } from "vue-router";

const list = ref([
    { key: "1", value: "aa" },
    { key: "2", value: "bb" },
    { key: "3", value: "cc" },
    { key: "4", value: "dd" },
]);
</script>

<template>
    <div>
        <table>
            <tr v-for="val in list" :key="val.key">
                <td>
                    <router-link :to="{name: 'board-detail', params: { listVal: val.value}}">key : {{ val.key }}</router-link>
                </td>
                <td>value : {{ val.value }}</td>
            </tr>
        </table>
    </div>
</template>

<style scoped></style>