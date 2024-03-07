<script setup>
import { ref, onMounted, watch } from "vue";
import { useRouter } from "vue-router";
import draggable from "vuedraggable";
import { useMemberStore } from "@/stores/member";
import { listSido, listGugun, listContent, listAttraction } from "@/api/map";
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import VSelect from "@/components/common/VSelect.vue";
import { registNewPlan, getPlanSeq, deletePlan } from "@/api/plan";
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
const defaultImage = "../assets/img/logo(1).png";

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
		console.log("True !!");
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
		console.log("False ..");
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

	if (!attraction.image) {
		console.log("이미지 없음");
		attraction.image = defaultImage;
	} else {
		console.log("이미지 있음");
	}
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

const truncateText = (text, maxLength) => {
	if (text.length > maxLength) {
		return text.substring(0, maxLength) + "...";
	} else {
		return text;
	}
};
</script>

<template>
	<q-page>
		<div class="row main-row">
			<div class="col-6">
				<div class="sel-control-main">
					<!-- 첫 번째 줄 -->
					<div
						class="row justify-end q-px-sm q-gutter-sm att-control"
						style="padding: 5px 8px"
					>
						<div class="col sel-control">
							<VSelect :selectOption="sidoList" @onKeySelect="onChangeSido" />
						</div>
						<div class="col sel-control">
							<VSelect :selectOption="gugunList" @onKeySelect="onChangeGugun" />
						</div>
						<div class="col-4 sel-control">
							<VSelect :selectOption="contentList" @onKeySelect="onChangeContent" />
						</div>
						<div class="col-1 btn-control">
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
				<q-scroll-area style="height: calc(90vh - 40px); max-width: 100%">
					<div
						class="text-center"
						v-for="attraction in attractions"
						:key="attraction.contentId"
					>
						<q-card class="my-card">
							<q-card-section horizontal>
								<q-img
									:src="attraction.image || defaultImage"
									class="col-5"
									@click="viewAttraction(attraction)"
								/>

								<q-card-section>
									<div class="text-h6">{{ attraction.title }}</div>
									{{ truncateText(attraction.overview, 300) }}
								</q-card-section>
							</q-card-section>
						</q-card>
					</div>
				</q-scroll-area>
			</div>
			<div class="col-6">
				<VKakaoMap :attractions="attractions" :selectAttraction="selectAttraction" />
			</div>
		</div>
	</q-page>
</template>

<style>
.q-page {
	width: 100%;
	height: 800px;
	margin: 0 auto;
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
	height: 100%;
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
	display: flex;
	justify-content: space-between;
	max-height: 60px;
}

.sel-control {
	display: flex;
	justify-content: center;
	width: 100%;
}

.btn-control {
	display: flex;
	margin: auto;
	justify-content: center;
	align-items: center;
	padding-top: 20px;
}

.att-control {
	/* margin: 0 auto; */
	width: 100%;
}

.del-btn-control {
	border: none;
}

.custom-input-style {
	width: 80%; /* 원하는 폭으로 조절 */
	height: 40px; /* 원하는 높이로 조절 */
	/* 추가적인 사용자 정의 스타일 속성들 */
}
</style>
