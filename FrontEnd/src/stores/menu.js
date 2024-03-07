import { ref } from "vue";
import { defineStore } from "pinia";

export const useMenuStore = defineStore("menuStore", () => {
  const menuList = ref([
    { label: "나의 여행 계획", show: false, routeName: "plan" },
    { label: "회원가입", show: true, routeName: "user-join" },
    { label: "로그인", show: true, routeName: "user-login" },
    { label: "내정보", show: false, routeName: "user-detail" },
    { label: "로그아웃", show: false, routeName: "user-logout" },

    // { label: "회원가입", show: true, routeName: "user-join", title: "회원 가입" },
    // { label: "로그인", show: true, routeName: "user-login", title: "" },
    // { label: "내정보", show: false, routeName: "user-detail", title: "내 정보" },
    // { label: "로그아웃", show: false, routeName: "user-logout", title: "" },
  ]);

  const changeMenuState = () => {
    menuList.value = menuList.value.map((item) => ({ ...item, show: !item.show }));
  };
  return {
    menuList,
    changeMenuState,
  };
}, {persist: true});
