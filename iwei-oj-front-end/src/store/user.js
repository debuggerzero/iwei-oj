import USER_ROLE_ENUM from "@/constant/accessEnum";
import UserService from "@/service/UserService";

export default {
  namespaced: true,
  state: () => ({
    loginUser: {
      name: "未登录",
      avatar: "default.png",
      role: {
        name: USER_ROLE_ENUM.NO_LOGIN,
      },
    },
  }),
  actions: {
    async getLoginUser({ commit, state }) {
      // 从远程请求获取登录信息
      const res = await UserService.getLoginUser();
      if (res.code === 0) {
        commit("updateUser", res.data);
      } else {
        commit("updateUser", {
          ...state.loginUser,
          role: {
            name: USER_ROLE_ENUM.NO_LOGIN,
          },
        });
      }
    },
  },
  mutations: {
    updateUser(state, payload) {
      state.loginUser = payload;
    },
  },
};
