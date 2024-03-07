<script setup>
import { useMenuStore } from "@/stores/menu";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";

const menuStore = useMenuStore();
const memberStore = useMemberStore();
const router = useRouter();

// 반응형을 유지하면서 스토어에서 속성을 추출하려면, storeToRefs()를 사용
// https://pinia.vuejs.kr/core-concepts/
const { menuList } = storeToRefs(menuStore);
const { changeMenuState } = menuStore;
const { userLogout } = memberStore;

// const logout = () => {
// 	console.log("로그아웃!!!!");
// 	changeMenuState();
// 	router.push("/");
// };

const logout = async () => {
	console.log("로그아웃!!!!");
	changeMenuState();
	await userLogout(sessionStorage.getItem("accessToken"));

	router.push("/user/login");
};
</script>

<template>
	<q-header reveal elevated class="bg-dark text-white" height-hint="98">
		<q-toolbar>
			<q-toolbar-title @click="$router.push({ name: 'main' })">
				<q-img
					src="@/assets/img/trip.png"
					style="max-height: 30px; max-width: 30px"
					class="q-mr-sm"
				/>
				<q-img src="@/assets/img/logo.png" style="max-height: 30px; max-width: 200px" />
			</q-toolbar-title>

			<q-btn @click="$router.push({ name: 'map' })" label="지역별 여행지" />
			<!-- <q-btn @click="$router.push({ name: 'plan' })" label="나의 여행 계획" /> -->
			<!-- <q-btn @click="$router.push({ name: 'board' })" label="여행지" /> -->
			<q-btn @click="$router.push({ name: 'planboard' })" label="여행 후기 공유" />
			<!-- <q-btn @click="$router.push({ name: 'user-join' })" label="회원가입" />
				<q-btn @click="$router.push({ name: 'user-login' })" label="로그인" /> -->

			<template v-for="menu in menuList" :key="menu.routeName">
				<template v-if="menu.show">
					<template v-if="menu.routeName === 'user-logout'">
						<q-btn @click.prevent="logout" :label="menu.label" />
					</template>
					<template v-else>
						<q-btn
							@click="$router.push({ name: menu.routeName })"
							:label="menu.label"
						/>
					</template>
				</template>
			</template>
		</q-toolbar>
	</q-header>

	<q-page-container>
		<router-view />
	</q-page-container>
</template>

<style scoped>
/* .q-page-container{
	display: flex;
} */
</style>
