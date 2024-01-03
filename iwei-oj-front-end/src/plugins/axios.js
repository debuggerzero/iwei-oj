import axios from "axios";
import { ElMessage } from "element-plus";

axios.defaults.withCredentials = true;
axios.defaults.baseURL = "/api";
axios.defaults.timeout = 5000;

axios.interceptors.request.use(
  function (config) {
    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

axios.interceptors.response.use(
  function (response) {
    if (response.data.code !== 0) {
      ElMessage.error(response.data.message);
    }
    return response.data;
  },
  function (error) {
    return Promise.reject(error);
  }
);

export default axios;