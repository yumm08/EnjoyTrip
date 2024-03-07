<script setup>
import { ref, onMounted, watch } from "vue";
import { useRouter } from "vue-router";
import draggable from "vuedraggable";
import { useMemberStore } from "@/stores/member";
import { listSido, listGugun, listContent, listAttraction } from "@/api/map";
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import VSelect from "@/components/common/VSelect.vue";
import { registNewPlan, getPlanSeq, updatePlan, deletePlan } from "@/api/plan";
import { getConstantType } from "@vue/compiler-core";

const memberStore = useMemberStore();
const { userInfo } = memberStore;
const router = useRouter();

const sidoList = ref([]);
const gugunList = ref([{ text: "구군선택", value: "" }]);
const contentList = ref([]);
const attractions = ref([]);
const selectAttraction = ref({});
const modify = ref(false);
const plan = ref({});
const seq = ref([]);

const param = ref({
	sido: 0,
	gugun: 0,
	contentTypeId: 0,
});

onMounted(() => {
	console.log("history.state : ", history.state);
	getState();
	getSidoList();
	getContentList();
});

const getState = () => {
	if (history.state.modify == true) {
		console.log("Modify !!");

		modify.value = history.state.modify;
		plan.value = JSON.parse(history.state.plan);
		planTitle.value = plan.value.planTitle;

		console.log("plan parsing : ", plan.value);
		console.log("modify : ", modify.value);

		getPlanSeq(
			plan.value.planNo,
			({ data }) => {
				console.log("seq data : ", data);
				planList.value = data;
				// console.log("seq : ", seq);
			},
			(error) => {
				console.error(error);
			}
		);
	} else {
		console.log("Create !!");
	}
};

const getSidoList = () => {
	listSido(
		({ data }) => {
			console.log(data);
			let options = [];
			options.push({ text: "시도선택", value: "" });
			data.forEach((sido) => {
				options.push({ text: sido.name, value: sido.code });
			});
			sidoList.value = options;
		},
		(err) => {
			console.log(err);
		}
	);
};

const getContentList = () => {
	listContent(
		({ data }) => {
			console.log(data);
			let options = [];
			options.push({ text: "유형 선택", value: "" });
			data.forEach((content) => {
				options.push({ text: content.contentName, value: content.contentTypeId });
			});
			contentList.value = options;
		},
		(err) => {
			console.log(err);
		}
	);
};

const getAttractionList = () => {
	console.log("param.value : ", param.value);
	listAttraction(
		param.value,
		({ data }) => {
			console.log("Attraction list : ");
			console.log(data);
			attractions.value = data;
		},
		(err) => {
			console.log(err);
		}
	);
};

const onChangeSido = (val) => {
	console.log(val);
	param.value.sido = val;
	listGugun(
		{ sido: val },
		({ data }) => {
			console.log(data);
			let options = [];
			options.push({ text: "구군선택", value: "" });
			data.forEach((gugun) => {
				options.push({ text: gugun.name, value: gugun.code });
			});
			gugunList.value = options;
		},
		(err) => {
			console.log(err);
		}
	);
};

const onChangeGugun = (val) => {
	param.value.gugun = val;
};

const onChangeContent = (val) => {
	param.value.contentTypeId = val;
};

const viewAttraction = (attraction) => {
	selectAttraction.value = attraction;
};

const getAttraction = () => {
	getAttractionList();
};

const planDto = ref({
	userId: userInfo.userId,
	userNickname: userInfo.userNickname,
	planTitle: "",
	planSeqs: [],
	img: "",
});
const planList = ref([]);

const addPlan = (attraction) => {
	// console.log("이건 돼");
	// attraction.title = "도산";
	planList.value.push(attraction);
	console.log("planList : ", planList);
};

const printPlan = () => {
	console.log("changed Plan List : ", planList);
};

const removeAt = (idx) => {
	planList.value.splice(idx, 1);
};

const planTitle = ref("");

watch(planTitle, (newValue) => {
	planDto.value.planTitle = newValue;

	console.log("new Title : ", planDto.value.planTitle);
});

const setPlanDto = () => {
	console.log("planDto first : ", planDto);
	if (planDto.value.planTitle == "") {
		planDto.value.planTitle = "제목 없음";
		console.log("제목 없음 !!!");
	}
	console.log("planList before : ", planList.value);
	for (var i = 1; i <= planList.value.length; i++) {
		planList.value[i - 1].planSeq = i;
		console.log(i, "번째 : ", planList.value[i - 1]);
	}
	console.log("planList after : ", planList.value);
	planDto.value.planSeqs = planList.value;

	console.log("Set PlanDto Complete !!!", planDto.value);
};

const registPlan = async () => {
	// console.log("planDto first : ", planDto);
	// if (planDto.value.planTitle == "") {
	//     planDto.value.planTitle = "제목 없음";
	//     console.log("제목 없음 !!!");
	// }
	// for (var i = 1; i <= planList.value.length; i++){
	//     planList.value[i-1].planSeq = i;
	// }
	// planDto.value.planSeqs = planList.value;

	setPlanDto();

	console.log("registPlan ing!!!! !!!------------------------------------------");
	console.log("planDto : ", planDto);
	await registNewPlan(
		planDto.value,
		(response) => {
			console.log("registPlan Success !!!");
		},
		(error) => {
			console.log("registPlan Failed ...", error);
		}
	);
	router.push("/plan/list");
};

const modifyPlan = () => {
	planDto.value.planNo = plan.value.planNo;
	setPlanDto();

	console.log("modifyPlan PlanDto : ", planDto.value);

	updatePlan(
		planDto.value,
		(response) => {
			console.log("Modify Success !!!");
		},
		(error) => {
			console.error("Modify Failed ...", error);
		}
	);

	router.push({ name: "plan-list" });
};

const delPlan = async () => {
	console.log("del plan : ", plan.value);
	await deletePlan(
		plan.value.planNo,
		(response) => {
			console.log("Delete Success !!!");
		},
		(error) => {
			console.error(error);
		}
	);
	router.push({ name: "plan-list" });
};

const sharePlan = () => {
	setPlanDto();

	router.push({
		name: "planboard-write",
		state: {
			command: "regist",
			planNo: plan.value.planNo,
			planDto: JSON.stringify(planDto.value),
		},
	});
};

const goMyList = () => {
	router.push({ name: "plan-list" });
};
</script>

<template>
	<q-page>
		<div class="row main-row">
			<div class="col-3">
				<div class="sel-control-main">
					<!-- 첫 번째 줄 -->
					<div class="row q-gutter-y-md att-control" style="padding: 5px 8px">
						<div class="col sel-control">
							<VSelect :selectOption="sidoList" @onKeySelect="onChangeSido" />
						</div>
						<div class="col sel-control">
							<VSelect :selectOption="gugunList" @onKeySelect="onChangeGugun" />
						</div>
					</div>

					<!-- 두 번째 줄 -->
					<div class="row q-gutter-y-md att-control" style="padding: 10px 8px">
						<div class="col-9 sel-control">
							<VSelect :selectOption="contentList" @onKeySelect="onChangeContent" />
						</div>
						<div class="col btn-control">
							<q-btn
								round
								color="dark"
								size="md"
								@click="getAttraction"
								icon="search"
							></q-btn>
						</div>
					</div>
				</div>
				<q-scroll-area style="height: calc(90vh - 120px); max-width: 100%">
					<div
						class="text-center"
						v-for="attraction in attractions"
						:key="attraction.contentId"
					>
						<q-card class="my-card">
							<q-img
								:src="attraction.image"
								width="50"
								height="50"
								@click="viewAttraction(attraction)"
							>
								<div class="absolute-bottom">
									<div class="text-h6">{{ attraction.title }}</div>
								</div>
							</q-img>

							<q-card-actions style="justify-content: center">
								<q-btn
									outline
									rounded
									color="dark"
									class="regist-btn"
									label="등록"
									@click="addPlan(attraction)"
								></q-btn>
							</q-card-actions>
						</q-card>
					</div>
				</q-scroll-area>
			</div>
			<div class="col-6" style="height: calc(95vh - 10px)">
				<VKakaoMap :attractions="attractions" :selectAttraction="selectAttraction" />
			</div>
			<div class="col-3">
				<div class="q-mx-sm q-mb-lg">
					<q-input
						v-model="planTitle"
						type="text"
						placeholder="여행 제목을 입력하세요"
						class="q- custom-input-style"
					/>
				</div>

				<q-scroll-area class="q-ml-sm" style="height: calc(90vh - 100px); max-width: 100%">
					<draggable v-model="planList" class="list-group" :options="{ animation: 150 }">
						<template #item="{ element, index }">
							<q-item clickable v-ripple>
								<q-item-section thumbnail>
									<img :src="element.image" width="auto" height="auto" />
								</q-item-section>
								<q-item-section>{{ element.title }}</q-item-section>
								<q-item-section side>
									<q-btn flat icon="close" @click="removeAt(index)" />
								</q-item-section>
							</q-item>
						</template>
					</draggable>
				</q-scroll-area>
				<div class="row justify-end q-px-sm q-gutter-sm">
					<!-- <q-btn outline color="dark" label="리스트 출력" @click="printPlan()" /> -->
					<q-btn
						v-show="!modify"
						outline
						color="dark"
						label="등록"
						@click="registPlan()"
					/>
					<q-btn
						v-show="modify"
						outline
						color="dark"
						label="수정"
						@click="modifyPlan()"
					/>
					<q-btn outline color="dark" label="취소" @click="goMyList()" />
					<q-btn
						v-show="modify"
						outline
						color="primary"
						label="공유"
						@click="sharePlan()"
					/>
					<q-btn
						v-show="modify"
						outline
						color="negative"
						label="삭제"
						@click="delPlan()"
					/>
				</div>
			</div>
		</div>
	</q-page>
</template>

<style>
.q-page {
	height: calc(90vh - 150px);
}

mark.purple {
	background: linear-gradient(to top, #c354ff 20%, transparent 30%);
}

.ghost {
	opacity: 0.5;
	background: #c8ebfb;
}

.text {
	box-sizing: border-box;
}

.close {
	float: right;
	padding-top: 8px;
	padding-bottom: 8px;
}

.close::after {
	content: "\00d7";
}

.main-row {
	height: calc(90vh - 100px);
}

.my-card {
	width: 100%;
	margin: 5px 0;
}

.regist-btn {
	float: right;
	margin: 5px 8px;
}

/* #item-control {
    margin: 5px 5px;
    border-radius: 15px;
    border: 2px rgba(0, 0, 0, 0.2) solid;


    box-shadow: 3px 3px 3px gray;
} */

.sel-control-main {
	/* display: flex; */
	/* justify-content: space-between; */
	height: 150px;
}

.sel-control {
	display: flex;
	justify-content: center;
	width: 100%;
	padding: 3px;
}

.btn-control {
	display: flex;
	justify-content: center;
	height: 40px;
	width: 100%; /* 부모 컨테이너에 맞게 확장 */
	padding: 10px;
}

.plan-btn-control {
	margin-left: 5px;
}

.att-control {
	/* margin: 0 auto; */
	width: 100%;
}

.del-btn-control {
	border: none;
}
</style>
