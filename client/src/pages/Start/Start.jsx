import React from "react";
import style from "./Start.module.css";
import { Link } from "react-router-dom";
import { SVGSelector } from "../../components/SVGSelector/SVGSelector";

export const Start = () => {
  return (
    <div className={style.startPage}>
      <div className={style.startContainer}>
        <div className={style.logo}>
          <div className={style.logoImgContainer}>
            <SVGSelector 
              type='logo'
              className={style.logoImg}
            />
          </div>
          <p className={style.logoTitle}>U-Retouch</p>
        </div>
        <p className={style.startText}>
          Визуализация товара на релевантных фонах
        </p>
        <Link className={`${style.startBtn} btn`} to="/login">
          Начать
        </Link>
      </div>
    </div>
  );
};
