<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

const router = useRouter();

const memberStore = useMemberStore();

const { isLogin } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;
const { changeMenuState } = useMenuStore();

const loginUser = ref({
    userId: "",
    userPass: "",
});

const login = async () => {
    console.log("login ing!!!! !!!------------------------------------------");
    console.log(loginUser.value);
    await userLogin(loginUser.value);
    let token = sessionStorage.getItem("accessToken");
    console.log("111. ", token);
    console.log("isLogin: ", isLogin);
    if (isLogin) {
        console.log("로그인 성공아닌가???");
        getUserInfo(token);
        changeMenuState();
    }
    router.push("/");
};

const regist = () => {
    router.push("/user/join");
}
</script>

<template>
    <div>
        <div class="container">
            <div class="title" align="center">로그인</div>
            <div class="form-controller">
                <q-input class="input-control" outlined v-model="loginUser.userId" label="아이디" />
                <q-input class="input-control" type="password"  outlined v-model="loginUser.userPass" label="비밀번호" @keyup.enter.native="login" />
            </div>
            <div class="btn-control">
                <q-btn outline color="primary" @click="login">
                    로그인
                </q-btn>
                <q-btn outline color="green-13" @click="regist">
                    회원가입
                </q-btn>
            </div>
            
        </div>
    </div>

</template>

<style scoped>
.container {
    /* position: absolute;
    top: 20%;
    margin: 0 auto; */

    display: flex;
    flex-direction: column;
    margin-top: 10%;
}

.btn-control {
    display: flex;
    /* justify-content: flex-between; */
    margin: 0 auto;
}

.form-controller {
    display: flex;
    flex-direction: column;
    width: 420px;
    margin: 0 auto;
}

.q-btn {
    margin: 5px 5px;
}

.input-control {
    padding: 5px 0px;
}

.title {
    /* display: block; */
    width: 420px;
    padding: 20px 15px 0px;
    margin: 0 auto 20px;
    font-size: 21px;
    line-height: inherit;
    color: #333;
    border: 0;
    border-bottom: 1px solid #e5e5e5;
}
</style>
