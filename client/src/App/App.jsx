import { Start } from "../pages/StartPage/Start";
import { Login } from "../pages/LoginPage/Login";
import { Registration } from "../pages/RegistrationPage/Registration";
import { Route, Routes } from "react-router-dom";
import { Home } from "../pages/HomePage/Home";
import { Navigate } from "react-router-dom";
import { observer } from "mobx-react-lite";
import { Context } from "..";
import { useContext, useEffect } from "react";
import { AuthLink } from "../pages/AuthLinkPage/AuthLink";

const App = observer(() => {
  const { authStore } = useContext(Context);

  useEffect(() => {
    if (localStorage.getItem("refreshToken")) {
      authStore.checkAuth();
    }
  }, []);

  if (authStore.isLoading) {
    return <div>Загрузка...</div>;
  }

  return (
    <div className="wrapper">
      <div className="App">
        <Routes>
          <Route path="/" element={authStore.token ? <Home /> : <Start />} />
          <Route
            path="/start"
            element={authStore.token ? <Navigate to="/" /> : <Start />}
          />
          <Route
            path="/login"
            element={authStore.token ? <Navigate to="/" /> : <Login />}
          />
          <Route
            path="/registration"
            element={authStore.token ? <Navigate to="/" /> : <Registration />}
          />
          <Route
            path="/auth"
            element={authStore.token ? <Navigate to="/" /> : <AuthLink />}
          />
        </Routes>
      </div>
    </div>
  );
});

export default App;
