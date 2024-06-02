import { makeAutoObservable } from "mobx";
import { login, logout, registration, checkAuth } from "../service/AuthService";

export default class UserStore {
  user = {};
  isAuth = false;
  isLoading = false;
  errorLogin = "";
  errorRegistration = "";
  constructor() {
    makeAutoObservable(this);
  }

  setAuth(bool) {
    this.isAuth = bool;
  }

  setUser(user) {
    this.user = user;
  }

  setLoading(bool) {
    this.isLoading = bool;
  }

  setErrorLogin(message) {
    this.errorLogin = message;
  }

  setRegistrationError(message)  {
    this.errorRegistration  = message;
  }

  async login(email, password) {
    try {
      const data = await login(email, password);
      localStorage.setItem("accessToken", data.accessToken);
      localStorage.setItem("refreshToken", data.refreshToken);
      this.setAuth(true);
    } catch (e) {
      this.setErrorLogin(e.message);
    }
  }

  async registration(email, password) {
    try {
      const data = await registration(email, password);
      localStorage.setItem("accessToken", data.accessToken);
      localStorage.setItem("refreshToken", data.refreshToken);
      this.setAuth(true);
    } catch (e) {
      console.log(e.message);
      this.setRegistrationError(e.message);
    }
  }

  async logout() {
    const refreshToken = localStorage.getItem("refreshToken");
    try {
      await logout(refreshToken);
      localStorage.removeItem("refreshToken");
      localStorage.removeItem("accessToken");
      this.setAuth(false);
      this.setUser({});
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
      this.setAuth(true);
    } catch (e) {
      console.log(e.response?.data?.message);
    } finally {
      this.setLoading(false);
    }
  }
}
