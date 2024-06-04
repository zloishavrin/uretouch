import React from "react";
import styles from "./Login.module.css";
import { Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import { Context } from "../..";
import { useContext } from "react";
import { observer } from "mobx-react-lite";
import { useNavigate } from "react-router-dom";
import { login } from "../../service/AuthService";
import { useState } from "react";

export const Login = observer(() => {
  const [error, setError] = useState(null);
  const [isLoading, setLoading] = useState(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    defaultValues: {
      email: "",
      password: "",
    },
  });

  const { authStore } = useContext(Context);

  const navigate = useNavigate();

  const onSubmit = async (data) => {
    setLoading(true);
    try {
      const resp = await login(data.email, data.password);
      authStore.login(resp);
      navigate("/history");
    } catch (e) {
      if (e.response.status == 400) {
        setError("Такой аккаунт уже существут!");
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className={styles.login}>
      <div className={styles.loginBox}>
        <p className={styles.loginTitle}>вход</p>
        <form className={styles.loginForm} onSubmit={handleSubmit(onSubmit)}>
          <div className={styles.inputContainer}>
            <input
              {...register("email", { required: "Введите почту" })}
              className={styles.loginInput}
              type="email"
              placeholder="Почта"
            />
            <p className={styles.inputError}>{errors.email?.message}</p>
          </div>
          <div className={styles.inputContainer}>
            <input
              {...register("password", { required: "Введите пароль" })}
              className={styles.loginInput}
              type="password"
              placeholder="Пароль"
            />
            <p className={styles.inputError}>{errors.password?.message}</p>
          </div>
          <button className={`${styles.loginBtn} btn`}>
            {isLoading ? <span className={styles.loader}></span> : "Войти"}
          </button>
        </form>
        <p className={styles.loginText}>
          У вас еще нет аккаунта?
          <Link to="/registration" className={styles.loginLink}>
            Зарегистрироваться
          </Link>
        </p>
        <div className={styles.inputContainer}>
          {error && (
            <p className={styles.error}>Неправильный логин или пароль</p>
          )}
        </div>
      </div>
    </div>
  );
});
