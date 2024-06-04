import { makeAutoObservable } from "mobx";
import { logout, checkAuth } from "../service/AuthService";

export default class AuthStore {
  token = localStorage.getItem("refreshToken");
  isLoading = false;
  errorLogin = "";
  errorRegistration = "";
  constructor() {
    makeAutoObservable(this);
  }

  setAuth(token) {
    this.token = token;
  }

  setLoading(bool) {
    this.isLoading = bool;
  }

  setErrorLogin(message) {
    this.errorLogin = message;
  }

  setRegistrationError(message) {
    this.errorRegistration = message;
  }

  login(data) {
    localStorage.setItem("accessToken", data.accessToken);
    localStorage.setItem("refreshToken", data.refreshToken);
    this.setAuth(data.refreshToken);
  }

  async logout() {
    const refreshToken = localStorage.getItem("refreshToken");
    try {
      await logout(refreshToken);
      localStorage.removeItem("refreshToken");
      localStorage.removeItem("accessToken");
      this.setAuth(null);
    } catch (e) {
      console.log(e.message);
    }
  }

  async checkAuth() {
    const refreshToken = localStorage.getItem("refreshToken");
    this.setLoading(true);
    try {
      const data = await checkAuth(refreshToken);
      localStorage.setItem("accessToken", data.accessToken);
      localStorage.setItem("refreshToken", data.refreshToken);
      this.setAuth(data.refreshToken);
    } catch (e) {
      console.log(e.response?.data?.message);
    } finally {
      this.setLoading(false);
    }
  }
}
