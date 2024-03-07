<script setup>
import { ref, onMounted, watch, defineProps, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
// import { getPlanArticle } from "@/api/planboard";
import { useMemberStore } from "@/stores/member";
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import { writeArticle, modifyPlanArticle, deletePlanArticle } from "@/api/planboard";

const router = useRouter();
const memberStore = useMemberStore();
const selectAttraction = ref({});

const { userInfo } = memberStore;

const props = defineProps({
	command: {
		type: String,
		required: true,
	},
	planDto: {
		type: Object,
		required: false,
	},
	planNo: {
		type: Number,
		required: false,
	},
	planBoardDto: {
		type: Object,
		required: false,
	},
	planSeq: {
		type: Array,
		required: false,
	},
});

const seqs = ref([]);
const title = ref("");
const content = ref("");

watch(
	() => {
		props.command;
	},
	() => {
		console.log("command 감지");
		console.log("command : ", props.command);
	},
	{
		deep: true,
	}
);

watch(
	() => {
		props.planBoardDto;
	},
	() => {
		console.log("planBoardDto 감지");
		console.log("planBoardDto : ", props.planBoardDto);
	},
	{
		deep: true,
	}
);

watch(
	() => {
		// (props.planSeq)
		seqs;
	},
	() => {
		console.log("planSeq 감지");
		console.log("planSeq : ", props.planSeq);
	},
	{
		deep: true,
	}
);

const article = ref({
	articleNo: 0,
	userId: "",
	userNickname: "",
	articleTitle: "",
	articleContent: "",
	planNo: 0,
	reviews: [],
});

onMounted(() => {
	console.log("onMounted");
	if (props.command != "regist") {
		console.log("onMounted");
		console.log("props planBoardDto : ", props.planBoardDto);
		console.log("props command : ", props.command);
		console.log("props seqs : ", props.planBoardDto.reviews);

		seqs.value = props.planBoardDto.reviews;
		console.log("seqs.value : ", seqs.value);
	}

	console.log(userInfo.userId);
	// content.value = props.planBoardDto.articleContent;
	// title.value = props.planBoardDto.articleTitle;
});

const setArticleUserinfo = () => {
	article.value.userId = props.planDto.userId;
	article.value.userNickname = props.planDto.userNickname;
};

const setArticle = () => {
	if (title.value == "") {
		article.value.articleTitle = "제목 없음";
		console.log("제목 없음 !!!");
	} else {
		article.value.articleTitle = title.value;
	}

	article.value.reviews = props.planSeq;
	article.value.articleContent = content.value;
};

const registArticle = async () => {
	setArticle();
	setArticleUserinfo();

	for (var i = 0; i < article.value.reviews.length; i++) {
		if (!article.value.reviews[i].content) {
			article.value.reviews[i].content = "";
		}
	}

	console.log("print !!! ", article.value);

	await writeArticle(
		article.value,
		(response) => {
			console.log("Regist Success !!!");
		},
		(error) => {
			console.log("Regist Failed ...", error);
		}
	);

	router.push({ name: "planboard-list" });
};

const deleteArticle = async () => {
	console.log("Delete ing : ", article.value);
	console.log("articleNo : ", props.planBoardDto.articleNo);

	await deletePlanArticle(
		props.planBoardDto.articleNo,
		(response) => {
			console.log("Delete Success !!!");
		},
		(error) => {
			console.log("Delete Failed ...", error);
		}
	);

	router.push({ name: "planboard-list" });
};

const modify = () => {
	console.log("modify 전에 가지고 있니 ?? planBoardDto : ", props.planBoardDto);
	console.log("modify 전에 가지고 있니 ?? seqs : ", seqs.value);
	router.push({
		name: "planboard-modify",
		state: {
			command: "modify",
			// planNo: plan.value.planNo,
			planBoardDto: JSON.stringify(props.planBoardDto),
		},
	});
};

const viewAttraction = (attraction) => {
	selectAttraction.value = attraction;
	console.log("viewAttraction", selectAttraction.value);
};

const getAttractionList = () => {
	console.log("articleNo : ", props.planBoardDto.articleNo);
	getPlanArticle(
		articleNo.value,
		({ data }) => {
			console.log(data);
			attractions.value = data;
		},
		(err) => {
			console.log(err);
		}
	);
};
</script>

<template>
	<!-- <q-page-container class="q-ma-sm" style="padding: 0"> -->
	<q-page style="padding: 0 5%">
		<div class="row q-mx-xl">
			<div class="col-6">
				<div class="column">
					<div style="background-color: whitesmoke">
						<div class="text-h4 q-pa-sm">{{ planBoardDto.articleTitle }}</div>
						<div class="q-px-sm">{{ planBoardDto.articleContent }}</div>
					</div>
					<div>
						<q-scroll-area
							class="q-pt-lg"
							style="width: 85vh; height: calc(90vh - 40px); position: absolute"
						>
							<q-timeline :layout="comfortable" :side="left" color="dark">
								<div v-for="seq in planSeq" :key="seq.contentId" class="q-py-xs">
									<q-timeline-entry side="right">
										<template v-slot:title>{{ seq.contentTitle }}</template>
										<div class="row justify-between">
											<div class="col q-px-xs" style="max-width: 450px">
												<q-img
													:src="seq.image"
													:ratio="4 / 3"
													@click="viewAttraction(seq)"
												/>
											</div>
											<div class="col q-px-xs" style="max-width: 600px">
												<template v-if="command == 'detail'">
													{{ seq.content }}
												</template>
											</div>
										</div>
									</q-timeline-entry>
								</div>
							</q-timeline>
						</q-scroll-area>
					</div>
				</div>
			</div>
			<div class="col-6 q-gutter-sm">
				<div>
					<div style="height: 100vh">
						<VKakaoMap :attractions="planSeq" :selectAttraction="selectAttraction" :detail="true" />
					</div>
				</div>
				<div style="height: 10vh" v-if="props.planBoardDto.userId == userInfo.userId">
					<q-btn
						v-if="command == 'detail'"
						outline
						color="primary"
						label="수정"
						@click="modify"
					/>
					<q-btn
						v-if="command == 'detail'"
						outline
						color="primary"
						label="삭제"
						@click="deleteArticle"
					/>
				</div>
			</div>
		</div>
	</q-page>
	<!-- </q-page-container> -->
</template>

<style scoped>
.q-page {
	height: calc(90vh - 10px);
}
.VKakaoMap {
	width: 100%; /* 또는 필요한 크기로 설정하세요 */
	height: 100%; /* 또는 필요한 크기로 설정하세요 */
}
</style>
