<script setup>
import { defineProps, ref, computed } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();

const props = defineProps({
	plan: Object,
});

const originalText = ref(props.plan.planTitle);
const maxLength = 12; // 최대 길이

const truncatedText = computed(() => {
	if (originalText.value.length <= maxLength) {
		return originalText.value; // 원본 문자열이 최대 길이보다 작을 경우 그대로 반환
	} else {
		console.log("문자열 길이 초과 !!! : ", originalText.value);
		return originalText.value.substring(0, maxLength) + "..."; // 일정 길이 이상이면 자르고 ...을 붙임
	}
});

const modifyPlan = () => {
	console.log("stringify : ", JSON.stringify(props.plan));
	router.push({
		name: "plan-write",
		state: {
			modify: true,
			plan: JSON.stringify(props.plan),
		},
	});
};
</script>

<template>
	<div>
		<q-card class="bg-dark text-white" @click="modifyPlan">
			<q-img :src="plan.image" style="height: 250px;" />
			<q-card-section>
				<div class="text-h6">{{ truncatedText }}</div>
				<div class="text-subtitle2">{{ plan.userNickname }}</div>
			</q-card-section>

			<q-card-section class="q-pt-none">
				<!-- {{ lorem }} -->
			</q-card-section>
		</q-card>
	</div>
</template>

<style scoped></style>
