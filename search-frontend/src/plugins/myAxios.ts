import axios from "axios";

const instance = axios.create({
  // xxxx为域名或公网ip  本地为localhost
  baseURL: "http://localhost:8202/api",
  //baseURL: "http://localhost:8202/api",
  timeout: 10000,
  headers: {},
});

// 添加响应拦截器
instance.interceptors.response.use(
  function (response) {
    console.log(response);
    const data = response.data;
    if (data.code === 0) {
      return data.data;
    }
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    console.error("request error: " + data);
    return response.data;
  },
  function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error);
  }
);
export default instance;
