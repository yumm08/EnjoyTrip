import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function userConfirm(param, success, fail) {
  console.log("param", param);
  await local.post(`/user/login`, param).then(success).catch(fail);
  console.log("userConfirm ok");
}

async function findById(userid, success, fail) {
  console.log("findById : ", userid);
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  console.log("headers : ", local.defaults.headers["Authorization"]);
  await local.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  console.log("tokenRegeneration user : ", user);
  await local.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function updateUserInfo(user, success, fail) {
  await local.put(`/user/update`, user).then(success).catch(fail);
}

async function userRegist(user, success, fail) {
  await local.post(`/user/regist`, user).then(success).catch(fail);
}

async function quit(userId, success, fail) {
  console.log("quit userId : ", userId);
  await local.delete(`/user/delete/${userId}`).then(success).catch(fail);
}

export { userConfirm, findById, tokenRegeneration, logout, updateUserInfo, userRegist, quit };
