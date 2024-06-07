import { $authHost } from "./api";

export const getInfoUser = async () => {
  const { data } = await $authHost.get("/user/info");
  return data;
};

export const getApiKeyUser = async () => {
  const { data } = await $authHost.get("/user/api-key");
  return data;
};

export const getHistoryList = async () => {
  const { data } = await $authHost.get("/user/history");
  return data;
};

export const getGenerationMode = async () => {
  const { data } = await $authHost.get("/generation/private/mods");
  return data;
};

export const setGeneration = async (formData) => {
  const { data } = await $authHost.post("/generation/private", { formData });
  return data;
};

export const getGeneration = async (generationId) => {
  const { data } = await $authHost.get("/user/generations/", {
    params: { id: generationId },
  });
  return data;
};
