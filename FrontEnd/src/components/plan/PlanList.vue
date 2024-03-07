<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { getMyPlanList, getMylistByTitle } from "@/api/plan";
import { useMemberStore } from "@/stores/member";
import PlanListItem from "./item/PlanListItem.vue";

const router = useRouter();
const memberStore = useMemberStore();

const { userInfo } = memberStore;
const planList = ref([]);
const word = ref("");

onMounted(() => {
	getPlanList();
});

const getPlanList = async () => {
	console.log("getPlanList userId", userInfo.userId);
	await getMyPlanList(
		userInfo.userId,
		({ data }) => {
			console.log("getPlanList Success !!!", data);
			planList.value = data;
			console.log("planList.value", planList.value);
		},
		(error) => {
			console.log("getPlanList Failed ...", error);
		}
	);
};

const searchByTitle = () => {
	console.log("내 계획 검색 userId : ", userInfo.userId);
	console.log("내 계획 검색 word : ", word.value);
	let param = {
		userId: userInfo.userId,
		word: word.value,
	};
	console.log("내 계획 검색 param : ", param);
	getMylistByTitle(
		param,
		({ data }) => {
			console.log("내 계획 검색 List : ", data);
			planList.value = data;
		},
		(error) => {
			console.error(error);
		}
	);
};

const addPlan = () => {
	router.push({
		name: "plan-write",
		state: {
			modify: false,
		},
	});
};
</script>
<template>
	<div>
		<q-page-container style="padding: 0">
			<q-page>
				<div>
					<q-img
						src="@/assets/img/planboardlistbgimg.jpg"
						style="width: 100%; height: 400px"
					/>
				</div>
				<div class="row justify-end items-start q-gutter-md">
					<div class="col-4 q-mb-xl">
						<q-input
							class="custom-input-style"
							v-model="word"
							type="search"
							placeholder="여행 후기를 검색해보세요"
							@keyup.enter.native="searchByTitle"
						>
							<template v-slot:append>
								<q-icon name="search" />
							</template>
						</q-input>
					</div>
				</div>

				<div class="row justify-center items-start q-gutter-md">
					<q-scroll-area
						style="
							width: 80%;
							height: calc(70vh - 100px);
							max-height: 600px;
							position: absolute;
						"
					>
						<div class="row justify-center items-start q-gutter-md">
							<PlanListItem
								v-for="plan in planList"
								:key="plan.planNo"
								:plan="plan"
								class="my-card"
							></PlanListItem>
						</div>
					</q-scroll-area>
				</div>
				<q-page-sticky position="bottom-right" :offset="[18, 18]" style="padding-right: 5%">
					<q-btn fab icon="add" color="grey-9" @click="addPlan" />
				</q-page-sticky>
			</q-page>
		</q-page-container>
	</div>
</template>
<style scoped>
.custom-input-style {
	width: 70%;
	height: 40px;
}

.my-card {
	width: calc(20% - 20px);
	/* 3 cards per row with some margin in between */
	margin: 15px;
	/* Adjust as needed */
	min-width: 200px;
}

.menu-control {
	display: flex;
	align-items: flex;
}
</style>
