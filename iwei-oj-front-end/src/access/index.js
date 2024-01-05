import router from "@/router";
import store from "@/store";
import checkAccess from "@/access/checkAccess";
import { USER_ROLE_ENUM } from "@/constant/accessEnum";

router.beforeEach(async (to, from, next) => {
  let loginUser = store.state.user.loginUser;
  const needAccess = to.meta?.access ?? USER_ROLE_ENUM.NO_LOGIN;
  // 如果之前没登陆过，自动登录
  if (loginUser.name === "未登录") {
    // 加 await 是为了等用户登录成功之后，再执行后续的代码
    await store.dispatch("user/getLoginUser");
    loginUser = store.state.user.loginUser;
  }
  // 要跳转的页面必须要登陆
  if (needAccess !== USER_ROLE_ENUM.NO_LOGIN) {
    // 如果没登陆，跳转到登录页面
    if (
      !loginUser ||
      !loginUser.role ||
      loginUser.role.name === USER_ROLE_ENUM.NO_LOGIN
    ) {
      next(`/login?redirect=${to.fullPath}`);
      return;
    }
    // 如果已经登陆了，但是权限不足，那么跳转到无权限页面
    if (!checkAccess(loginUser, needAccess)) {
      next("/noAuth");
      return;
    }
  }
  next();
});
