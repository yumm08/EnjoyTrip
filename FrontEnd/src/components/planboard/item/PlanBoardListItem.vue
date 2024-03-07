<script setup>
import { defineProps, ref, computed } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();

const props = defineProps({
	article: Object,
});

const originalText = ref(props.article.articleTitle);
const maxLength = 12; // 최대 길이

const truncatedText = computed(() => {
	if (originalText.value.length <= maxLength) {
		return originalText.value; // 원본 문자열이 최대 길이보다 작을 경우 그대로 반환
	} else {
		console.log("문자열 길이 초과 !!! : ", originalText.value);
		return originalText.value.substring(0, maxLength) + "..."; // 일정 길이 이상이면 자르고 ...을 붙임
	}
});

const getDetail = () => {
	console.log("stringify : ", JSON.stringify(props.article));
	router.push({
		name: "planboard-detail",
		state: {
			command: "detail",
			article: JSON.stringify(props.article),
		},
	});
};
</script>

<template>
	<div>
		<q-card class="bg-dark text-white">
			<q-img :src="article.image" style="height: 250px;" />

			<q-card-section>
				<div class="text-h6">{{ truncatedText }}</div>
				<div class="text-subtitle2">{{ article.userNickname }}</div>
			</q-card-section>

			<q-card-actions class="row justify-between" style="padding-top: 0">
				<div class="col-4">
					<q-btn flat round color="white" icon="visibility" />
					<span class="text-caption text-grey">{{ article.articleHit }}</span>
				</div>
				<div class="col-4">
					<q-btn flat color="white" label="More Detail" @click="getDetail" />
				</div>
			</q-card-actions>
		</q-card>
	</div>
</template>

<style scoped></style>
