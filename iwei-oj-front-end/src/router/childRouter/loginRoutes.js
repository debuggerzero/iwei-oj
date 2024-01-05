import LoginForm from "@/components/LoginForm.vue";
import { USER_ROLE_ENUM } from "@/constant/accessEnum";

export const loginRoutes = [
  {
    path: "/login",
    name: "登录",
    icon: '<i class="iconfont icon-denglu"></i>',
    component: LoginForm,
    meta: {
      access: USER_ROLE_ENUM.NO_LOGIN,
    },
  },
];
