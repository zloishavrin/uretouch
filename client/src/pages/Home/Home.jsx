import React from "react";
import { Context } from "../..";
import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { observer } from "mobx-react-lite";

export const Home = observer(() => {
  const { authStore } = useContext(Context);

  const navigate = useNavigate();

  const buttonHandler = () => {
    authStore.logout();
    navigate("/start");
  };

  return (
    <div>
      Home
      <button onClick={buttonHandler}>Выход</button>
    </div>
  );
});
