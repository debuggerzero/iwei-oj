import axios from "@/plugins/axios";
import RouterEnum from "@/constant/RouterEnum";
import { md5 } from "js-md5";

const login = (account, password) => {
  return axios.post(RouterEnum.BACK_END.USER_LOGIN_PASSWORD, {
    account: account,
    password: md5(password),
  });
};

const logout = () => {
  return axios.get(RouterEnum.BACK_END.USER_LOGOUT);
};

const getLoginUser = () => {
  return axios.get(RouterEnum.BACK_END.USER_GET_LOGIN_USER);
};

const queryUserInfoList = (baseQuery) => {
  return axios.post(RouterEnum.BACK_END.USER_GET_USERINFO_LIST, baseQuery);
};

const queryRanking = (baseQuery) => {
  return axios.post(RouterEnum.BACK_END.USER_GET_RANKING, baseQuery);
};

const queryUserRoleList = () => {
  return axios.get(RouterEnum.BACK_END.USER_GET_USER_ROLE_LIST);
};

const modifyInfoByUser = (userInfo) => {
  return axios.put(RouterEnum.BACK_END.USER_UPDATE_USER, userInfo);
};

const modifyInfoByAdmin = (userInfo) => {
  return axios.put(RouterEnum.BACK_END.USER_UPDATE_ADMIN, userInfo);
};

const modifyUserPassword = (modifyRequest) => {
  return axios.put(RouterEnum.BACK_END.USER_UPDATE_PASSWORD, modifyRequest);
};

const resetUserPassword = (uid) => {
  return axios.put(RouterEnum.BACK_END.USER_RESET_PASSWORD + "/" + uid);
};

const insertOneUserInfo = (userInfo) => {
  return axios.post(RouterEnum.BACK_END.USER_INSERT_ONE, userInfo);
};

const insertUserInfoList = (file) => {
  return axios.post(RouterEnum.BACK_END.USER_INSERT_BATCH, file, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
};

const deleteUserInfo = (uid) => {
  return axios.delete(RouterEnum.BACK_END.USER_DELETE_INFO + "/" + uid);
};

export default {
  login: login,
  logout: logout,
  getLoginUser: getLoginUser,
  queryUserInfoList: queryUserInfoList,
  queryRanking: queryRanking,
  queryUserRoleList: queryUserRoleList,
  modifyInfoByUser: modifyInfoByUser,
  modifyInfoByAdmin: modifyInfoByAdmin,
  modifyUserPassword: modifyUserPassword,
  resetUserPassword: resetUserPassword,
  insertOneUserInfo: insertOneUserInfo,
  insertUserInfoList: insertUserInfoList,
  deleteUserInfo: deleteUserInfo,
};
