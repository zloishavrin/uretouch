import { makeAutoObservable } from "mobx";

export default class UserStore {
  mode = "";

  constructor() {
    makeAutoObservable(this);
  }

  setMode(title) {
    this.mode = title;
  }
}
