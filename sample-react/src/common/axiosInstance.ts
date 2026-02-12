import axios from "axios";
import messages from "./messages";

const axiosInstance = axios.create({
  baseURL: import.meta.env.VITE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

axiosInstance.interceptors.request.use((config) => {
  const rawJwt = localStorage.getItem("jwt");

  if (rawJwt) {
    const jwt = JSON.parse(rawJwt);

    if (jwt && jwt.accessToken) {
      config.headers.Authorization = `Bearer ${jwt.accessToken}`;
    }
  }

  return config;
});

axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    console.error(error);
    const msg = error.response?.data?.message || messages.contactAdmin;
    alert("[서버 오류] : " + msg);

    return Promise.reject(error);
  }
);

export default axiosInstance;