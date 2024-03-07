<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const slide = ref(1);

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
	<q-page>
		<q-carousel animated v-model="slide" arrows navigation infinite>
			<q-carousel-slide
				:name="1"
				img-src="@/assets/img/annie-spratt-qyAka7W5uMY-unsplash.jpg"
			>
				<div class="q-carousel-overlay q-overlay-left q-container">
					<div>
						<h1 class="text-h3 text-white">
							<q-icon name="flight" class="text-white" />
							어디로 여행을 떠나고 싶나요?
						</h1>
						<h2 class="text-h5 text-teal-2 text-nowrap">
							ENJOY TRIP과 함께 간편하게 여행 계획을 세워보세요!
						</h2>
						<q-btn
							color="teal-6"
							label="계획 세우기"
							@click="addPlan"
						></q-btn>
					</div>
				</div>
			</q-carousel-slide>
			<q-carousel-slide
				:name="2"
				img-src="@/assets/img/arches-national-park-1846759_1920.jpg"
			>
				<div class="q-carousel-overlay q-overlay-right q-container">
					<div>
						<h1 class="text-h3 text-indigo-1">
							<q-icon name="map" class="text-indigo-1" />
							나만의 여행 계획,
						</h1>
						<h2 class="text-h5 text-indigo-4 text-nowrap">
							ENJOY TRIP으로 쉽게 관리해보세요
						</h2>
						<q-btn
							color="indigo-6"
							label="내 계획"
							@click="$router.push({ name: 'plan' })"
						></q-btn>
					</div>
				</div>
			</q-carousel-slide>
			<q-carousel-slide
				:name="3"
				img-src="@/assets/img/johannes-plenio-OwMTchwUTNw-unsplash.jpg"
			>
				<div class="q-carousel-overlay q-overlay-center q-container">
					<div>
						<h1 class="text-h3 text-blue-grey-2">어디를 가야할지 고민중이세요?</h1>
						<h1 class="text-h3 text-blue-grey-2">
							요즘 인기있는 여행지가 궁금하신가요?
						</h1>
						<h2 class="text-h5 text-blue-grey-1 text-nowrap">
							여행지에 다녀온 후기를 보고 계획을 세워보세요!
						</h2>
						<q-btn
							color="blue-grey-6"
							label="여행 후기 찾기"
							@click="$router.push({ name: 'plan' })"
						></q-btn>
					</div>
				</div>
			</q-carousel-slide>
		</q-carousel>
	</q-page>
	<router-view></router-view>
</template>

<style scoped>
.q-carousel-overlay {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	display: flex;
	align-items: center;
	background-color: rgba(0, 0, 0, 0.2);
}

.q-overlay-left {
	justify-content: left;
	text-align: left;
	padding-left: 10%;
}

.q-overlay-center {
	justify-content: center;
	text-align: center;
}

.q-overlay-right {
	justify-content: right;
	text-align: right;
	padding-right: 10%;
}

/* 슬라이드 내부 텍스트 스타일 설정 */
.text-h3,
.text-h5 {
	margin: 0;
	font-weight: bold;
	padding-bottom: 10px;
}
.q-page {
	height: 100vh; /* 100% viewport height */
	overflow: hidden; /* 페이지 넘김 스크롤 제거 */
}

/* q-carousel 스타일 설정 */
.q-carousel {
	height: 100%; /* 100% 부모 요소 높이로 설정 */
}

/* q-carousel-slide 스타일 설정 (필요한 경우) */
.q-carousel-slide {
	height: 100vh; /* q-carousel-slide의 높이를 100% viewport height로 설정 */
}

.q-btn {
	margin-top: 10px; /* 버튼과의 간격 추가 */
}
</style>
