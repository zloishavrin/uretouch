import { React, createContext } from "react";
import ReactDOM from "react-dom/client";
import "./globalStyles/reset.css";
import "./globalStyles/index.css";
import App from "./App/App";
import reportWebVitals from "./reportWebVitals";
import { BrowserRouter } from "react-router-dom";
import UserStore from "./store/UserStore";

export const userStore = new UserStore();

export const Context = createContext({
  userStore,
});

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <Context.Provider
    value={{
      userStore,
    }}
  >
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Context.Provider>
);

reportWebVitals();
