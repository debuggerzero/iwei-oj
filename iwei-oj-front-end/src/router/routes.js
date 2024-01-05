import LoginView from "@/views/LoginView.vue";
import BasicLayout from "@/layouts/BasicLayout.vue";
import { clientRoutes } from "@/router/childRouter/clientRoutes";
import { loginRoutes } from "@/router/childRouter/loginRoutes";

export const routes = [
  {
    path: "/index",
    redirect: "/",
  },
  {
    path: "/",
    name: "客户端路由",
    component: BasicLayout,
    children: clientRoutes,
  },
  {
    path: "/login",
    name: "登录",
    component: LoginView,
    children: loginRoutes,
  },
];
