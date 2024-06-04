import { $authHost } from "./api";

export const getInfoUser = async () => {
  const { data } = await $authHost.get("/user/info");
  return data;
};

export const getApiKeyUser = async () => {
  const { data } = await $authHost.get("/user/api-key");
  return data;
};