import { USER_ROLE_ENUM } from "@/constant/accessEnum";

const checkAccess = (loginUser, needAccess = USER_ROLE_ENUM.NO_LOGIN) => {
  const loginUserAccess = loginUser?.role?.name ?? USER_ROLE_ENUM.NO_LOGIN;
  if (needAccess === USER_ROLE_ENUM.NO_LOGIN) {
    return true;
  }
  if (needAccess === USER_ROLE_ENUM.USER) {
    if (loginUserAccess === USER_ROLE_ENUM.NO_LOGIN) {
      return false;
    }
  }
  if (needAccess === USER_ROLE_ENUM.ADMIN) {
    if (loginUserAccess !== USER_ROLE_ENUM.ADMIN) {
      return false;
    }
  }
  return true;
};

export default checkAccess;
