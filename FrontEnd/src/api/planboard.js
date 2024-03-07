import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listPlanArticle(success, fail) {
	local.get(`/planboard/list`).then(success).catch(fail);
}

function searchPlanArticle(word, success, fail) {
	local.get(`/planboard/list/${word}`).then(success).catch(fail);
}

function detailPlanArticle(articleno, success, fail) {
	local.get(`/planboard/${articleno}`).then(success).catch(fail);
}

function registPlanArticle(article, success, fail) {
	console.log("planboardjs article", article);
	local.post(`/planboard/regist`, JSON.stringify(article)).then(success).catch(fail);
}

function getModifyPlanArticle(articleno, success, fail) {
	local.get(`/planboard/modify/${articleno}`).then(success).catch(fail);
}

function modifyPlanArticle(article, success, fail) {
	console.log("modifyPlanArticle article : ", article);
	local.put(`/planboard/modify`, article).then(success).catch(fail);
}

function deletePlanArticle(articleno, success, fail) {
	console.log("deletePlanArticle articleno : ", articleno);
	local.delete(`/planboard/${articleno}`).then(success).catch(fail);
}

function getPlanArticle(articleno, success, fail) {
	console.log("getPlanArticle articleno : ", articleno);
	local.get(`/planboard/view/${articleno}`).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
	console.log("writeArticle article : ", article);
	local.post(`/planboard/regist`, article).then(success).catch(fail);
}

export {
	listPlanArticle,
	searchPlanArticle,
	detailPlanArticle,
	registPlanArticle,
	getModifyPlanArticle,
	modifyPlanArticle,
	deletePlanArticle,
	getPlanArticle,
	writeArticle,
};
