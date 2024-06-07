import { Start } from "../pages/Start/Start";
import { Login } from "../pages/Login/Login";
import { Registration } from "../pages/Registration/Registration";
import { Route, Routes } from "react-router-dom";
import { Navigate } from "react-router-dom";
import { observer } from "mobx-react-lite";
import { Context } from "..";
import { useContext, useEffect } from "react";
import { AuthLink } from "../pages/AuthLink/AuthLink";
import { Profile } from "../pages/Profile/Profile";
import { Generation } from "../pages/Generation/Generation";
import { Hisrory } from "../pages/History/History";
import { Header } from "../components/Header/Header";
import { CSSTransition, TransitionGroup } from "react-transition-group";
import { useLocation, useNavigate } from "react-router-dom";
import "./App.css";

const App = observer(() => {
  const { authStore } = useContext(Context);
  const location = useLocation();
  const navigate = useNavigate();
  useEffect(() => {
    if (localStorage.getItem("refreshToken")) {
      authStore.checkAuth();
    } else {
      navigate("/start");
    }
  }, []);

  if (authStore.isLoading) {
    return <div className="loader"></div>;
  }

  return (
    <div className="wrapper">
      <div className="App">
        {authStore.token && <Header />}
        <TransitionGroup>
          <CSSTransition key={location.key} classNames="fade" timeout={1000}>
            <Routes>
              <Route
                path="/user"
                element={authStore.token ? <Profile /> : <Start />}
              />
              <Route
                path="/generation"
                element={authStore.token ? <Generation /> : <Start />}
              />
              <Route
                path="/history"
                element={authStore.token ? <Hisrory /> : <Start />}
              />
              <Route
                path="/start"
                element={authStore.token ? <Navigate to="/user" /> : <Start />}
              />
              <Route
                path="/login"
                element={authStore.token ? <Navigate to="/user" /> : <Login />}
              />
              <Route
                path="/registration"
                element={
                  authStore.token ? <Navigate to="/user" /> : <Registration />
                }
              />
              <Route
                path="/auth"
                element={
                  authStore.token ? <Navigate to="/user" /> : <AuthLink />
                }
              />
            </Routes>
          </CSSTransition>
        </TransitionGroup>
      </div>
    </div>
  );
});

export default App;
