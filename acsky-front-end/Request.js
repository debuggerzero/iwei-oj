import axios from "axios";
import {ElLoading, ElMessage} from "element-plus";

const contentTypeForm = "application/x-www-form-urlencoded;charset=UTF-8";
const contentTypeJson = "application/json";
const contentTypeFile = "multipart/form-data";

const request = (config) => {
    let {url, params, methods, dataType = "json", showLoading = true} = config;

    let contentType = contentTypeJson;
    if (dataType === "form") {
        contentType = contentTypeForm;
    } else if (dataType === "file") {
        contentType = contentTypeFile;
        const param = new FormData();
        for (const key in params) {
            param.append(key, params[key]);
        }
        params = param;
    }

    const instantce = axios.create({
        baseURL: "/path",
        timeout: 10 * 1000,
        headers: {
            "Content-Type": contentType,
            "X-Requested-With": "XMLHttpRequest",
        },
    });

    let loading = null;
    instantce.interceptors.request.use(
        (config) => {
            if (showLoading) {
                loading = ElLoading.service({
                    lock: true,
                    text: "Loading",
                    background: "rgba(0, 0, 0, 0.7)",
                });
            }
            return config;
        },
        (error) => {
            if (showLoading && loading) {
                loading.close;
            }
            return Promise.reject("发送请求失败");
        }
    );

    //请求后拦截
    instantce.interceptors.response.use(
        (response) => {
            if (showLoading && loading) {
                loading.close();
            }
            return response.data;
        },
        (error) => {
            console.log(error);
            if (showLoading && loading) {
                loading.close();
            }
            return Promise.reject(error.response.data.message);
        }
    );

    let returnMessage = null;
    const throwError = (error) => {
        ElMessage({
            message: error,
            type: "error",
        });
    };
    if (methods === "GET") {
        returnMessage = instantce.get(url).catch((error) => {
            throwError(error);
            return null;
        });
    } else if (methods === "POST") {
        returnMessage = instantce.post(url, params).catch((error) => {
            throwError(error);
            return null;
        });
    } else if (methods === "DELETE") {
        returnMessage = instantce.delete(url).catch((error) => {
            throwError(error);
            return null;
        });
    } else if (methods === "PUT") {
        returnMessage = instantce.put(url, params).catch((error) => {
            throwError(error);
            return null;
        });
    }
    return returnMessage;
};

export default request;
