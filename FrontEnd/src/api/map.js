import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listSido(success, fail) {
  local.get(`/trip/sido`).then(success).catch(fail);
}

function listGugun(param, success, fail) {
  console.log(`/trip/${param.sido}`)
  // console.log(`/trip/${param}`)
  // console.log(param)
  local.get(`/trip/${param.sido}`).then(success).catch(fail);
}

function listContent(success, fail) {
  local.get(`/trip/content`).then(success).catch(fail);
}

function listAttraction(param, success, fail) {
  console.log(param)
  console.log(`/trip?sido=${param.sido}&gugun=${param.gugun}&contentType=${param.contentType}`)
  local.get(`/trip`, {params: param}).then(success).catch(fail);
  // local.get(`/trip?sido=${param.sido}&gugun=${param.gugun}&contentType=${param.contentType}`).then(success).catch(fail);
}

export { listSido, listGugun, listContent, listAttraction };
