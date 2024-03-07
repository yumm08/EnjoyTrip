import { localAxios } from "@/util/http-commons";

const attraction = localAxios();

function listAttractions(param, success, fail) {
  attraction.get("", { params: param }).then(success).catch(fail);
}

export { listAttractions };
