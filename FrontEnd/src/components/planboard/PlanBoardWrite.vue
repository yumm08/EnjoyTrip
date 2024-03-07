<script setup>
import { ref, onMounted, watch, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import { getPlanArticle } from "@/api/planboard";
import PlanBoardForm from "./item/PlanBoardForm.vue";

const router = useRouter();

const article = ref({});
const planDto = ref([]);
const command = ref("");
const planNo = ref(0);

onBeforeMount(() => {
	console.log("PlanBoardWrite history.state : ", history.state);
	getState();
});

const getState = () => {
	if (history.state.command == "regist") {
		console.log("Regist !!");

		planDto.value = JSON.parse(history.state.planDto);
		command.value = history.state.command;
		planNo.value = history.state.planNo;

		// article.value = JSON.parse(history.state.article);
		// command.value = history.state.command;

		console.log("Modify planDto parsing : ", planDto.value);

		for (var i = 0; i < planDto.value.planSeqs.length; i++) {
			planDto.value.planSeqs[i].contentTitle = planDto.value.planSeqs[i].title;
		}

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
};
</script>

<template>
	<PlanBoardForm
		:planDto="planDto"
		:command="command"
		:planNo="planNo"
		:planSeq="planDto.planSeqs"
	/>
</template>

<style scoped></style>
