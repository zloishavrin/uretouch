import { Start } from "../pages/StartPage/Start";
import { Login } from "../pages/LoginPage/Login";
import { Registration } from "../pages/RegistrationPage/Registration";
import { Route, Routes } from "react-router-dom";
import { Home } from "../pages/HomePage/Home";
import { Navigate } from "react-router-dom";
import { observer } from "mobx-react-lite";
import { Context } from "..";
import { useContext, useState } from "react";

const App = observer(() => {
  
  const isAuth = localStorage.getItem("isAuth");
  
  return (
    <div className="wrapper">
      <div className="App">
        <Routes>
          <Route path="/" element={isAuth ? <Home /> : <Start />} />
          <Route path="/start" element={isAuth ? <Navigate to="/" /> : <Start />} />
          <Route path="/login" element={isAuth ? <Navigate to="/" /> : <Login />} />
          <Route path="/registration" element={isAuth ? <Navigate to="/" /> : <Registration />} />
        </Routes>
      </div>
    </div>
  );
})

export default App;
