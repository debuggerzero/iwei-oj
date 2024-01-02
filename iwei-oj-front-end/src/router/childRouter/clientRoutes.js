import USER_ROLE_ENUM from "@/constant/accessEnum";

export const clientRoutes = [
  {
    path: "/",
    name: "首页",
    icon: '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M512 128 128 447.936V896h255.936V640H640v256h255.936V447.936z"></path></svg>',
  },
  {
    path: "/problem",
    name: "题目",
    icon: '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M640 384v256H384V384zm64 0h192v256H704zm-64 512H384V704h256zm64 0V704h192v192zm-64-768v192H384V128zm64 0h192v192H704zM320 384v256H128V384zm0 512H128V704h192zm0-768v192H128V128z"></path></svg>',
    meta: {
      access: USER_ROLE_ENUM.NO_LOGIN,
    },
  },
  {
    path: "/ranking",
    name: "排行榜",
    icon: '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M416 896V128h192v768zm-288 0V448h192v448zm576 0V320h192v576z"></path></svg>',
    meta: {
      access: USER_ROLE_ENUM.NO_LOGIN,
    },
  },
  {
    path: "/console",
    name: "控制台",
    icon: '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M512 896a384 384 0 1 0 0-768 384 384 0 0 0 0 768m0 64a448 448 0 1 1 0-896 448 448 0 0 1 0 896"></path><path fill="currentColor" d="M192 512a320 320 0 1 1 640 0 32 32 0 1 1-64 0 256 256 0 1 0-512 0 32 32 0 0 1-64 0"></path><path fill="currentColor" d="M570.432 627.84A96 96 0 1 1 509.568 608l60.992-187.776A32 32 0 1 1 631.424 440l-60.992 187.776zM502.08 734.464a32 32 0 1 0 19.84-60.928 32 32 0 0 0-19.84 60.928"></path></svg>',
    meta: {
      access: USER_ROLE_ENUM.ADMIN,
    },
  },
];
