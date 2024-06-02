import axios from "axios";

export const API_URL = `https://uretouch.shaligula.ru/api`;

const $host = axios.create({
  baseURL: API_URL,
});

const $authHost = axios.create({
  baseURL: API_URL,
});

$authHost.interceptors.request.use((config) => {
  config.headers.Authorization = `Bearer ${localStorage.getItem(
    "accessToken"
  )}`;
  return config;
});

$authHost.interceptors.response.use(
  (config) => {
    return config;
  },
  async (error) => {
    const originalRequest = error.config;
    if (
      error.response.status == 401 &&
      error.config &&
      !error.config._isRetry
    ) {
      originalRequest._isRetry = true;
      try {
        const refreshToken = localStorage.getItem("refreshToken");
        const response = await axios.post(`${API_URL}/auth/refresh`, {
          refreshToken,
        });
        localStorage.setItem("token", response.data.accessToken);
        return $authHost.request(originalRequest);
      } catch (e) {
        console.log("НЕ АВТОРИЗОВАН");
      }
    }
    throw error;
  }
);

export { $host, $authHost };
