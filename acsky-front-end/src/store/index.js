import { createStore } from 'vuex'

export default createStore({
  state: {
    user: {
      isLoggedIn : false,
      id: 0,
      name: "YUE",
      account: "null",
      email: "null",
      phone: "null",
      profile: "null",
      avatar: "null",
      createDate: "null",
      priv_level: 0,
      submit_cnt: 1,
      pass_cnt: 1
    }
  },
  getters: {
    getUser: state => state.user
  },
  mutations: {
    updateUser(state, newUser) {
      state.user = { ...state.user, ...newUser };
    }
  },
  actions: {
  },
  modules: {
  }
})
