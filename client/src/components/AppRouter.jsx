import React from "react";
import { observer } from "mobx-react-lite";
import { Context } from "..";
import { useContext } from "react";
import { Route, Routes } from "react-router-dom";
import { authRoutes, publicRoutes } from "../routes";

export const AppRouter = observer(() => {
  const { authStore } = useContext(Context);

  const isAuth = localStorage.getItem("isAuth");

  return (
      <Routes>
        {authStore.isAuth &&
          authRoutes.map(({ path, element }) => (
            <Route key={path} path={path} element={element} />
          ))}
        {publicRoutes.map(({ path, element }) => (
          <Route key={path} path={path} element={element} />
        ))}
      </Routes>
  );
});
