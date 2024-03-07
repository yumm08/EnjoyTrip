import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "../views/TheMainView.vue";

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: "/",
			name: "main",
			component: TheMainView,
		},
		{
			path: "/map",
			name: "map",
			component: () => import("@/views/TheAttractionView.vue"),
		},
		{
			path: "/plan",
			name: "plan",
			component: () => import("../views/ThePlanView.vue"),
			redirect: { name: "plan-list" },
			children: [
				{
					path: "list",
					name: "plan-list",
					component: () => import("@/components/plan/PlanList.vue"),
					meta: { requiresAuth: true },
				},
				{
					path: "write",
					name: "plan-write",
					component: () => import("@/components/plan/PlanWrite.vue"),
					meta: { requiresAuth: true },
				},
				{
					path: "modify/:articleno",
					name: "plan-modify",
					component: () => import("@/components/plan/PlanModify.vue"),
					meta: { requiresAuth: true },
				},
			],
		},
		{
			path: "/board",
			name: "board",
			component: () => import("../views/TheBoardView.vue"),
			redirect: { name: "board-list" },
			children: [
				{
					path: "list",
					name: "board-list",
					component: () => import("@/components/board/BoardList.vue"),
				},
				{
					path: "detail/:listVal",
					name: "board-detail",
					component: () => import("@/components/board/BoardDetail.vue"),
				},
			],
		},
		{
			path: "/planboard",
			name: "planboard",
			component: () => import("../views/ThePlanBoardView.vue"),
			redirect: { name: "planboard-list" },
			children: [
				{
					path: "list",
					name: "planboard-list",
					component: () => import("@/components/planboard/PlanBoardList.vue"),
				},
				{
					path: "detail",
					name: "planboard-detail",
					component: () => import("@/components/planboard/PlanBoardDetail.vue"),
				},
				{
					path: "write",
					name: "planboard-write",
					component: () => import("@/components/planboard/PlanBoardWrite.vue"),
				},
				{
					path: "modify",
					name: "planboard-modify",
					component: () => import("@/components/planboard/PlanBoardModify.vue"),
				},
			],
		},
		{
			path: "/user",
			name: "user",
			component: () => import("../views/TheUserView.vue"),
			// redirect: { name: "user-mypage" },
			children: [
				{
					path: "mypage",
					name: "user-mypage",
					component: () => import("@/components/user/UserMypage.vue"),
				},
				{
					path: "join",
					name: "user-join",
					component: () => import("@/components/user/UserJoin.vue"),
					// props: true,
				},
				{
					path: "login",
					name: "user-login",
					component: () => import("@/components/user/UserLogin.vue"),
				},
				{
					path: "detail",
					name: "user-detail",
					component: () => import("@/components/user/UserDetail.vue"),
					// props: true,
				},
				{
					path: "modify",
					name: "user-modify",
					component: () => import("@/components/user/UserModify.vue"),
					// props: true,
				},
				{
					path: "logout",
					name: "user-logout",
					component: () => import("@/components/user/UserLogout.vue"),
				},
			],
		},
	],
});

router.beforeEach((to, from, next) => {
	const access_token = sessionStorage.getItem("accessToken");
	console.log(access_token);
	if (to.meta.requiresAuth && access_token === null) {
		next({
			name: "user-login",
		});
	} else {
		next();
	}
});

export default router;
