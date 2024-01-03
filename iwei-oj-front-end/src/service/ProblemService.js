import axios from "@/plugins/axios";
import RouterEnum from "@/constant/RouterEnum";

const getProbInfoList = (baseQuery) => {
  return axios.post(RouterEnum.BACK_END.PROB_GET_PRO_INFO_LIST, baseQuery);
};

const getProbInfoVOList = (baseQuery) => {
  return axios.post(RouterEnum.BACK_END.PROB_GET_PRO_INFO_VO_LIST, baseQuery);
};

const getOneProbInfo = (pid) => {
  return axios.get(RouterEnum.BACK_END.PROB_GET_ONE_ID + "/" + pid);
};

const getSampleList = (pid) => {
  return axios.get(RouterEnum.BACK_END.PROB_GET_SAMPLE_LIST_PID + "/" + pid);
};

const insertProbInfo = (probInfo) => {
  return axios.post(RouterEnum.BACK_END.PROB_INSERT, probInfo);
};

const updateProbInfo = (probInfo) => {
  return axios.put(RouterEnum.BACK_END.PROB_UPDATE_ID, probInfo);
};

const deleteProbInfo = (pid) => {
  return axios.delete(RouterEnum.BACK_END.PROB_DELETE_ID + "/" + pid);
};

const doProbSubmit = (query) => {
  return axios.post(RouterEnum.BACK_END.PROB_COMMIT, query);
};

const getProbSubmit = (query) => {
  return axios.post(RouterEnum.BACK_END.PROB_GET_SUBMIT_VO_LIST, query);
};

export default {
  getProbInfoList: getProbInfoList,
  getProbInfoVOList: getProbInfoVOList,
  getOneProbInfo: getOneProbInfo,
  getSampleList: getSampleList,
  insertProbInfo: insertProbInfo,
  updateProbInfo: updateProbInfo,
  deleteProbInfo: deleteProbInfo,
  doProbSubmit: doProbSubmit,
  getProbSubmit: getProbSubmit,
};
