<script setup>
import { ref, onMounted, watch, defineProps, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
// import { getPlanArticle } from "@/api/planboard";
import { useMemberStore } from "@/stores/member";
import { writeArticle, modifyPlanArticle, deletePlanArticle } from "@/api/planboard";

const router = useRouter();
const memberStore = useMemberStore();

const { userInfo } = memberStore;

const props = defineProps({
	command: {
		type: String,
		required: true,
	},
	// article: {
	//     type: Object,
	//     required: false
	// },
	// articleNo: {
	//     type: Number,
	//     required: false
	// },
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

// const planDto = ref({});
// const planBoardDto = ref({});
const seqs = ref([]);
// const share = ref(false);
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
	if (props.command != "regist") {
		console.log("onMounted");
		console.log("props planBoardDto : ", props.planBoardDto);
		console.log("props command : ", props.command);
		console.log("props seqs : ", props.planBoardDto.reviews);

		seqs.value = props.planBoardDto.reviews;
		console.log("seqs.value : ", seqs.value);
	}

	if (props.command == "modify") {
		// article.value = props.article;
		content.value = props.planBoardDto.articleContent;
		title.value = props.planBoardDto.articleTitle;
	}

	if (props.command == "detail") {
		// article.value = props.article;
		content.value = props.planBoardDto.articleContent;
		title.value = props.planBoardDto.articleTitle;
	}

	if (props.command == "regist") {
		article.value.planNo = props.planNo;
	}
});

// onMounted(() => {
//     console.log("history.state : ", history.state);
//     getState();
// });

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

const modifyArticle = async () => {
	article.value.articleNo = props.planBoardDto.articleNo;
	setArticle();

	console.log("modify article !!! ", article.value);

	await modifyPlanArticle(
		article.value,
		(response) => {
			console.log("Modify Success !!!");
		},
		(error) => {
			console.log("Modify Failed ...", error);
		}
	);

	router.push({ name: "planboard-list" });
};

const deleteArticle = async () => {
	console.log("Delete ing ----------> ", article.value);
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
</script>

<template>
	<q-page-container class="q-mx-sm">
		<div class="row justify-around">
			<div class="col-4 q-gutter-sm" style="align-items: center; padding-top: 5%">
				<q-card round class="col">
					<q-item>
						<q-item-section class="q-pa-md q-gutter-sm">
							<q-item-label v-if="command == 'detail'" class="text-h3">{{
								props.planBoardDto.articleTitle
							}}</q-item-label>
							<q-input
								rounded
								standout
								type="text"
								v-model="title"
								placeholder="Insert Title"
							/>
							<q-editor
								v-model="content"
								placeholder="Insert Content"
								min-height="14rem"
							/>
						</q-item-section>
					</q-item>
				</q-card>
				<div style="display: flex; justify-content: right">
					<q-btn
						v-if="command == 'regist'"
						outline
						color="black"
						label="등록"
						@click="registArticle"
					/>
					<q-btn
						v-if="command == 'modify'"
						outline
						color="black"
						label="저장"
						@click="modifyArticle"
					/>
				</div>
			</div>
			<div class="col-6">
				<q-scroll-area style="width: 45%; height: 90%; position: absolute">
					<q-timeline color="black">
						<div v-for="seq in planSeq" :key="seq.contentId" class="q-py-xs">
							<q-timeline-entry :body="body" :icon="circle">
								<template v-slot:title>{{ seq.contentTitle }}</template>
								<div class="row justify-between">
									<div class="col q-px-xs" style="max-width: 450px">
										<q-img :src="seq.image" :ratio="4 / 3" />
									</div>
									<div class="col q-px-sm" style="max-width: 450px">
										<q-input
											square
											v-if="command != 'detail'"
											v-model="seq.content"
											type="textarea"
											placeholder="리뷰를 적어주세요."
										/>
									</div>
								</div>
							</q-timeline-entry>
						</div>
					</q-timeline>
				</q-scroll-area>
			</div>
		</div>
	</q-page-container>
</template>

<style scoped></style>
