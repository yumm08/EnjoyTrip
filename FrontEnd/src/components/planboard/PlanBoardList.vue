<script setup>
import { ref, onMounted } from "vue";
import PlanBoardListItem from "./item/PlanBoardListItem.vue";
import { listPlanArticle, searchPlanArticle } from "@/api/planboard.js";

const articles = ref([]);
const word = ref("");

onMounted(() => {
	getArticleList();
});

const getArticleList = async () => {
	console.log("서버에서 글목록 얻어오자!!!");
	await listPlanArticle(
		({ data }) => {
			console.log("글목록", data);
			articles.value = data;
		},
		(error) => {
			console.log(error);
		}
	);
};

const searchByTitle = () => {
	console.log("word", word);
	searchPlanArticle(
		word.value,
		({ data }) => {
			console.log("searchByTitle List : ", data);
			articles.value = data;
		},
		(error) => {
			console.error(error);
		}
	);
};
</script>
<template>
	<div>
		<q-page-container style="padding: 0%">
			<q-page>
				<div>
					<q-img
						src="@/assets/img/planboardlistbgimg.jpg"
						style="width: 100%; height: 400px"
					/>
				</div>
				<div class="q-mb-xl row justify-center">
					<q-input
						v-model="word"
						type="search"
						placeholder="여행 후기를 검색해보세요"
						class="custom-input-style"
						@keyup.enter.native="searchByTitle"
					>
						<template v-slot:append>
							<q-icon name="search" />
						</template>
					</q-input>
				</div>

				<div class="row justify-center items-start q-gutter-md">
					<q-scroll-area
						style="width: 80%; height: 70%; max-height: 600px; position: absolute"
					>
						<div class="row justify-center items-start q-gutter-md">
							<PlanBoardListItem
								v-for="article in articles"
								:key="article.articleNo"
								:article="article"
								class="caption my-card"
							/>
						</div>
						<template v-slot:loading>
							<div class="row justify-center q-my-md">
								<q-spinner-dots color="primary" size="40px" />
							</div>
						</template>
					</q-scroll-area>
				</div>
			</q-page>
		</q-page-container>
	</div>
</template>
<style scoped>
.custom-input-style {
	width: 40%; /* 원하는 폭으로 조절 */
	height: 40px; /* 원하는 높이로 조절 */
	/* 추가적인 사용자 정의 스타일 속성들 */
}
.card-container {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
	margin: 20px; /* Adjust as needed */
}
.my-card {
	width: calc(20% - 20px);
	margin: 15px;
	min-width: 200px;
}
</style>
