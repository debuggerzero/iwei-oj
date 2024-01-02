import LoginView from "@/views/LoginView.vue";
import BasicLayout from "@/layouts/BasicLayout.vue";
import { clientRoutes } from "@/router/childRouter/clientRoutes";
import { userRoutes } from "@/router/childRouter/userRoutes";

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
    path: "/user",
    name: "用户",
    component: LoginView,
    children: userRoutes,
  },
];
