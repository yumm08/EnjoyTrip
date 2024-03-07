<script setup>
// import { ref } from "vue";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";

const memberStore = useMemberStore();
const menuStore = useMenuStore();
const router = useRouter();

const { userInfo } = storeToRefs(memberStore);
const { userUpdate, userJoin, userDelete } = memberStore;
const { changeMenuState } = menuStore;

if (userInfo.value === null) {
    userInfo.value = {
        "userId": "",
        "userPass": "",
        "userName": "",
        "userNickname": "",
        "userEmail": "",
        // "userDomain": "",
    }
}
console.log("userInfo : ", userInfo.value);

defineProps({
    // userInfo: Object,
    title: String
});

// const printInfo = () => {
//     console.log(userInfo.value);
// };

const modify = () => {
    router.push({ name: "user-modify" });
};

const quit = async () => {
    console.log("quit ing!!!! ------------------------------------------");
    await userDelete(userInfo.value);
    changeMenuState();
    router.push({ name: "user-login" });
}

const update = async () => {
    console.log("update ing!!!! ------------------------------------------");
    console.log(userInfo.value);
    await userUpdate(userInfo.value);
    router.push("/user/detail");
};

const join = async () => {
    console.log("join ing!!!! ------------------------------------------");
    console.log(userInfo.value);
    await userJoin(userInfo.value);
    router.push("/user/login");
};
</script>

<template>
    <div>
        <div class="container">
            <div class="title" align="center">{{ title }}</div>
            <div class="form-controller">
                <q-input class="input-control" outlined v-model="userInfo.userId" label="아이디" :readonly="title === '정보 수정'" />
                <q-input class="input-control" type="password" outlined v-model="userInfo.userPass" label="비밀번호" />
                <q-input class="input-control" outlined v-model="userInfo.userName" label="이름" :readonly="title === '정보 수정'" />
                <q-input class="input-control" outlined v-model="userInfo.userNickname" label="닉네임" />
                <q-input class="input-control" type="email" outlined v-model="userInfo.userEmail" label="이메일" :readonly="title === '정보 수정'" />
                <!-- <q-input class="input-control" outlined v-model="userInfo.userDomain" label="도메인" :readonly="title === '정보 수정'" /> -->
            </div>
            <div class="btn-control">
                <q-btn outline color="primary" v-show="title === '내 정보'" @click="modify">수정</q-btn>
                <q-btn outline color="primary" v-show="title === '내 정보'" @click="quit">탈퇴</q-btn>
                <q-btn outline color="primary" v-show="title === '정보 수정'" @click="update">정보 수정</q-btn>
                <q-btn outline color="primary" v-show="title === '회원 가입'" @click="join">회원 가입</q-btn>
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
    margin-top: 5%;
}

.btn-control {
    display: flex;
    margin: 5px auto;
}

.form-controller {
    display: flex;
    flex-direction: column;
    width: 420px;
    margin: 0 auto;
}

.input-control {
    padding: 5px 0px;
}

/* button {
    float: right;
    margin-right: 30px;
} */

.q-btn {
    float: right;
    /* margin: 5px 5px; */
}

.title {
    display: block;
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
