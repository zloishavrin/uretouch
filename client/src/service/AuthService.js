import { $host, $authHost } from "./api";

export const registration = async (email, password) => {
  const { data } = await $host.post("/auth/registration", { email, password });
  return data;
};

export const login = async (email, password) => {
  const { data } = await $host.post("/auth/login", { email, password });
  return data;
};

export const logout = async (refreshToken) => {
  await $host.post("/auth/logout", { refreshToken });
};

export const checkAuth = async (refreshToken) => {
  const { data } = await $authHost.post("/auth/refresh", { refreshToken });
  return data;
};
