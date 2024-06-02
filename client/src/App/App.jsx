import { Start } from "../pages/StartPage/Start";
import { Login } from "../pages/LoginPage/Login";
import { Registration } from "../pages/RegistrationPage/Registration";
import { Route, Routes } from "react-router-dom";
import { Home } from "../pages/HomePage/Home";

function App() {
  return (
    <div className="wrapper">
      <div className="App">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/start" element={<Start />} />
          <Route path="/login" element={<Login  />} />
          <Route path="/registration" element={<Registration />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
