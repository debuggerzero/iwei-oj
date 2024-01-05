import { USER_ROLE_ENUM } from "@/constant/accessEnum";
import UserManagerView from "@/views/console/UserManagerView.vue";
import ProblemManagerView from "@/views/console/ProblemManagerView.vue";
import TagManagerView from "@/views/console/TagManagerView.vue";

export const consoleRoutes = [
  {
    path: "/console/index",
    name: "仪表盘",
    icon: '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="m665.216 768 110.848 192h-73.856L591.36 768H433.024L322.176 960H248.32l110.848-192H160a32 32 0 0 1-32-32V192H64a32 32 0 0 1 0-64h896a32 32 0 1 1 0 64h-64v544a32 32 0 0 1-32 32zM832 192H192v512h640zM352 448a32 32 0 0 1 32 32v64a32 32 0 0 1-64 0v-64a32 32 0 0 1 32-32m160-64a32 32 0 0 1 32 32v128a32 32 0 0 1-64 0V416a32 32 0 0 1 32-32m160-64a32 32 0 0 1 32 32v192a32 32 0 1 1-64 0V352a32 32 0 0 1 32-32"></path></svg>',
    // component: ProblemEditLayout,
    meta: {
      access: USER_ROLE_ENUM.ADMIN,
      hideInMenu: false,
    },
  },
  {
    path: "/console/user",
    name: "用户管理",
    icon: '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M512 512a192 192 0 1 0 0-384 192 192 0 0 0 0 384m0 64a256 256 0 1 1 0-512 256 256 0 0 1 0 512m320 320v-96a96 96 0 0 0-96-96H288a96 96 0 0 0-96 96v96a32 32 0 1 1-64 0v-96a160 160 0 0 1 160-160h448a160 160 0 0 1 160 160v96a32 32 0 1 1-64 0"></path></svg>',
    component: UserManagerView,
    meta: {
      access: USER_ROLE_ENUM.ADMIN,
    },
  },
  {
    path: "/console/problem",
    name: "题目管理",
    icon: '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M832 384H576V128H192v768h640zm-26.496-64L640 154.496V320zM160 64h480l256 256v608a32 32 0 0 1-32 32H160a32 32 0 0 1-32-32V96a32 32 0 0 1 32-32m160 448h384v64H320zm0-192h160v64H320zm0 384h384v64H320z"></path></svg>',
    component: ProblemManagerView,
    meta: {
      access: USER_ROLE_ENUM.ADMIN,
    },
  },
  {
    path: "/console/tag",
    name: "标签管理",
    icon: '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M224 318.336V896h576V318.336L552.512 115.84a64 64 0 0 0-81.024 0zM593.024 66.304l259.2 212.096A32 32 0 0 1 864 303.168V928a32 32 0 0 1-32 32H192a32 32 0 0 1-32-32V303.168a32 32 0 0 1 11.712-24.768l259.2-212.096a128 128 0 0 1 162.112 0z"></path><path fill="currentColor" d="M512 448a64 64 0 1 0 0-128 64 64 0 0 0 0 128m0 64a128 128 0 1 1 0-256 128 128 0 0 1 0 256"></path></svg>',
    component: TagManagerView,
    meta: {
      access: USER_ROLE_ENUM.ADMIN,
    },
  },
];
