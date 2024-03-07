import { localAxios } from "@/util/http-commons";

const local = localAxios();

function registNewPlan(plan, success, fail) {
  console.log("registNewPlan", plan);
  local.post(`/plan/regist`, plan).then(success).catch(fail);
}

function getMyPlanList(userId, success, fail) {
  console.log("getMyPlanList userId : ", userId);
  local.get(`/plan/list/${userId}`).then(success).catch(fail);
}

function getMylistByTitle(param, success, fail) {
  console.log("getMyPlanList userId : ", param.userId);
  console.log("getMyPlanList userId : ", param.word);
  local.get(`/plan/list/${param.userId}/${param.word}`).then(success).catch(fail);
}

function getPlanSeq(planNo, success, fail) {
  console.log("getPlanSeq planNo : ", planNo);
  local.get(`/plan/seq/${planNo}`).then(success).catch(fail);
}

function updatePlan(planDto, success, fail) {
  console.log("updatePlan planDto : ", planDto);
  local.put(`/plan/0`, planDto).then(success).catch(fail);
}

function deletePlan(planNo, success, fail) {
  console.log("deletePlan planNo : ", planNo);
  local.delete(`/plan/${planNo}`).then(success).catch(fail);
}

export { registNewPlan, getMyPlanList, getMylistByTitle, getPlanSeq, updatePlan, deletePlan };
