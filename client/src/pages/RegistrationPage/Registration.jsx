import React from "react";
import styles from "./Registration.module.css";
import { Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import { Context } from "../..";
import { useContext } from "react";
import { observer } from "mobx-react-lite";

export const Registration = observer(() => {
  const {
    register,
    formState: { errors },
    reset,
    handleSubmit,
    watch,
  } = useForm({
    defaultValues: {
      email: "",
      password: "",
      password2: "",
    },
  });

  const { userStore } = useContext(Context);

  const onSubmit = (data) => {
    userStore.registration({ email: data.email, password: data.password });
    reset();
  };

  return (
    <div className={styles.regist}>
      <div className={styles.registBox}>
        <Link to="/login" className={styles.registLink}>
          <svg
            version="1.1"
            id="Capa_1"
            xmlns="http://www.w3.org/2000/svg"
            x="0px"
            y="0px"
            viewBox="0 0 240.823 240.823"
            className={styles.registLinkImg}
          >
            <g>
              <path
                id="Chevron_Right"
                d="M57.633,129.007L165.93,237.268c4.752,4.74,12.451,4.74,17.215,0c4.752-4.74,4.752-12.439,0-17.179 l-99.707-99.671l99.695-99.671c4.752-4.74,4.752-12.439,0-17.191c-4.752-4.74-12.463-4.74-17.215,0L57.621,111.816 C52.942,116.507,52.942,124.327,57.633,129.007z"
                fill="#ffffff"
              ></path>
            </g>
          </svg>
        </Link>
        <p className={styles.registTitle}>Регистрация</p>
        <form className={styles.registForm} onSubmit={handleSubmit(onSubmit)}>
          <div className={styles.inputContainer}>
            <input
              {...register("email", { required: "Введите почту" })}
              type="email"
              className={styles.registInput}
              placeholder="Почта"
            />
            <p className={styles.inputError}>{errors.email?.message}</p>
          </div>
          <div className={styles.inputContainer}>
            <input
              {...register("password", { required: "Введите пароль" })}
              className={styles.registInput}
              type="password"
              placeholder="Пароль"
            />
            <p className={styles.inputError}>{errors.password?.message}</p>
          </div>
          <div className={styles.inputContainer}>
            <input
              {...register("password2", {
                required: "Повторите пароль",
                validate: (value) =>
                  value === watch("password") || "Пароли не совпадают",
              })}
              className={styles.registInput}
              type="password"
              placeholder="Повторите пароль"
            />
            <p className={styles.inputError}>{errors.password2?.message}</p>
          </div>
          <div className={styles.inputContainer}>
            {userStore.errorRegistration && (
              <div className={styles.error}>Такой аккаунт уже существует!</div>
            )}
          </div>
          <button className={`${styles.registBtn} btn`}>
            Зарегистрироваться
          </button>
        </form>
      </div>
    </div>
  );
});
