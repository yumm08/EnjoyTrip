<script setup>
import { ref, onMounted, watch, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import { getPlanArticle } from "@/api/planboard";
import PlanBoardForm from "./item/PlanBoardForm.vue";
import PlanBoardDetailItem from "./item/PlanBoardDetailItem.vue";

const router = useRouter();

const article = ref({});
const planBoardDto = ref([]);
const planSeq = ref([]);
const command = ref("");
const show = ref(false);

onBeforeMount(async () => {
	console.log("Detail history.state : ", history.state);
	await getState();
});

const getState = async () => {
	if (history.state.command == "detail") {
		console.log("Detail !!");

		article.value = JSON.parse(history.state.article);
		command.value = history.state.command;

		console.log("article parsing : ", article.value);

		await getPlanArticle(
			article.value.articleNo,
			({ data }) => {
				console.log("data : ", data);
				planBoardDto.value = data;
				console.log("planBoardDto : ", planBoardDto.value);

				planSeq.value = planBoardDto.reviews;
				// console.log("planSeq.value", planSeq.value);
				// for (var i = 0; i < planSeq.value.length; i++){
				// 	planSeq.value[i].title = planSeq.value[i].contentTitle;
				// }
			},
			(error) => {
				console.error(error);
			}
		);

		show.value = true;
		console.log("show !!!", show.value);
		console.log("show planBoardDto : ", planBoardDto.value);
	} else {
		console.log("False ..");
	}
};
</script>

<template>
	<div v-if="show">
		<!-- <PlanBoardForm :planBoardDto="planBoardDto" :command="command" :planSeq="planBoardDto.reviews" /> -->
		<PlanBoardDetailItem
			:planBoardDto="planBoardDto"
			:command="command"
			:planSeq="planBoardDto.reviews"
		/>
	</div>
</template>

<style scoped></style>
