import axios from "@/plugins/axios";
import RouterEnum from "@/constant/RouterEnum";

const getTagInfoList = (baseQuery) => {
  return axios.post(RouterEnum.BACK_END.TAG_GET_TAG_INFO_LIST, baseQuery);
};

const insertTagInfo = (tagInfo) => {
  return axios.post(RouterEnum.BACK_END.TAG_INSERT, tagInfo);
};

const deleteTagInfoById = (tid) => {
  return axios.delete(RouterEnum.BACK_END.TAG_DELETE_TAG_INFO_ID + "/" + tid);
};

const updateTagInfoById = (tagInfo) => {
  return axios.put(RouterEnum.BACK_END.TAG_UPDATE_TAG_INFO_ID, tagInfo);
};

const getTagInfoById = (tid) => {
  return axios.get(RouterEnum.BACK_END.TAG_GET_TAG_INFO_ID + "/" + tid);
};

const getTagInfoVOList = () => {
  return axios.post(RouterEnum.BACK_END.TAG_GET_TAG_INFO_VO_LIST);
};

export default {
  getTagInfoList: getTagInfoList,
  insertTagInfo: insertTagInfo,
  deleteTagInfoById: deleteTagInfoById,
  updateTagInfoById: updateTagInfoById,
  getTagInfoById: getTagInfoById,
  getTagInfoVOList: getTagInfoVOList,
};
