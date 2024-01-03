const ROUTER_ENUM = {
  BACK_END: {
    // 上传文件
    UPLOAD_FILE: "/file/upload/image",
    // 用户后端接口
    USER_LOGIN_PASSWORD: "/user/login/password",
    USER_LOGOUT: "/user/logout",
    USER_GET_LOGIN_USER: "/user/query/one",
    USER_GET_USERINFO_LIST: "/user/queryUserInfoList",
    USER_GET_RANKING: "/user/queryRanking",
    USER_GET_USER_ROLE_LIST: "/user/queryUserRoleList",
    USER_UPDATE_USER: "/user/modifyInfoByUser",
    USER_UPDATE_ADMIN: "/user/modifyInfoByAdmin",
    USER_UPDATE_PASSWORD: "/user/modifyUserPassword",
    USER_RESET_PASSWORD: "/user/resetPassword",
    USER_INSERT_ONE: "/user/insertOneUserInfo",
    USER_INSERT_BATCH: "/user/insertUserInfoList",
    USER_DELETE_INFO: "/user/deleteUserInfoList",
    // 题目后端接口
    PROB_GET_PRO_INFO_LIST: "/problem/getProbInfoList",
    PROB_GET_PRO_INFO_VO_LIST: "/problem/getProbInfoVOList",
    PROB_GET_ONE_ID: "/problem/one",
    PROB_INSERT: "/problem/save",
    PROB_UPDATE_ID: "/problem/update",
    PROB_DELETE_ID: "/problem/delete",
    PROB_COMMIT: "/problem/doQuestionSubmit",
    PROB_GET_SUBMIT_VO_LIST: "/problem/getProblemSubmitVO",
    PROB_GET_SAMPLE_LIST_PID: "/problem/getSampleList",
    // 标签后端接口
    TAG_GET_TAG_INFO_LIST: "/tag/getTagInfoList",
    TAG_INSERT: "/tag/insert",
    TAG_DELETE_TAG_INFO_ID: "/tag/deleteTagInfoById",
    TAG_UPDATE_TAG_INFO_ID: "/tag/updateTagInfoById",
    TAG_GET_TAG_INFO_ID: "/tag/getTagInfoById",
    TAG_GET_TAG_INFO_VO_LIST: "/tag/getTagInfoVOList",
  },
};

export default ROUTER_ENUM;
