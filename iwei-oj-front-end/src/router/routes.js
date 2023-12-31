import LoginView from "@/views/LoginView.vue";
import LoginForm from "@/components/LoginForm.vue";

export const routes = [
  {
    path: "/user",
    name: "user",
    component: LoginView,
    children: [
      {
        path: "/user/login",
        name: "userLogin",
        component: LoginForm,
      },
    ],
  },
];
