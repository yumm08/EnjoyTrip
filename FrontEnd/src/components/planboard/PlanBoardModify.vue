<script setup>
import { ref, onMounted, watch, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import { getPlanArticle } from "@/api/planboard";
import PlanBoardForm from './item/PlanBoardForm.vue';

const router = useRouter();

const article = ref({});
const planBoardDto = ref([]);
const command = ref("");

onBeforeMount(() => {
    console.log("history.state : ", history.state);
    getState();
});

const getState = () => {
    if (history.state.command == 'modify') {
        console.log("Modify !!");

        planBoardDto.value = JSON.parse(history.state.planBoardDto);
        command.value = history.state.command;

        // article.value = JSON.parse(history.state.article);
        // command.value = history.state.command;

        console.log("Modify planBoardDto parsing : ", planBoardDto.value);

        // getPlanArticle(
        //     article.value.articleNo,
        //     ({ data }) => {
        //         console.log("data : ", data);
        //         planBoardDto.value = data;
        //         console.log("planBoardDto : ", planBoardDto.value);
        //     },
        //     (error) => {
        //         console.error(error);
        //     }
        // );
    } else {
        console.log("False ..");
    }
}
</script>

<template>
    <PlanBoardForm :planBoardDto="planBoardDto" :command="command" :planSeq="planBoardDto.reviews" />
</template>

<style scoped></style>
