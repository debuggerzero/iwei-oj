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

export default {
  login: login,
  logout: logout,
  getLoginUser: getLoginUser,
};
