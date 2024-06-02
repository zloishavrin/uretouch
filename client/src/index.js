import { React, createContext } from "react";
import ReactDOM from "react-dom/client";
import "./globalStyles/reset.css";
import "./globalStyles/index.css";
import App from "./App/App";
import reportWebVitals from "./reportWebVitals";
import { BrowserRouter } from "react-router-dom";
import AuthStore from "./store/AuthStore";

export const authStore = new AuthStore();

export const Context = createContext({
  authStore,
});

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <Context.Provider
    value={{
      authStore,
    }}
  >
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Context.Provider>
);

reportWebVitals();
