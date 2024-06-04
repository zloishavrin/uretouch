import React from "react";
import styles from "./Registration.module.css";
import { Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import { observer } from "mobx-react-lite";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { registration } from "../../service/AuthService";
import { SVGSelector } from "../../components/SVGSelector/SVGSelector";

export const Registration = observer(() => {

  const [error, setError] = useState(null);
  const [isLoading, setLoading] = useState(false);

  const {
    register,
    formState: { errors },
    handleSubmit,
    watch,
  } = useForm({
    defaultValues: {
      email: "",
      password: "",
      password2: "",
    },
  });

  const navigate = useNavigate();

  const onSubmit = async (data) => {
    setLoading(true);
    try {
      await registration(data.email, data.password);
      navigate("/auth");
    } catch (e) {
      if (e.response.status == 400) {
        setError("Такой аккаунт уже существут!");
      }
    } finally{
      setLoading(false);
    }
  };

  return (
    <div className={styles.regist}>
      <div className={styles.registBox}>
        <Link to="/login" className={styles.registLink}>
          <SVGSelector 
            type='back'
            className={styles.registLinkImage}
          />
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
            {error && (
              <div className={styles.error}>Такой аккаунт уже существует!</div>
            )}
          </div>
          <button className={`${styles.registBtn} btn`}>
            {
              isLoading ? <span className={styles.loader}></span> : "Зарегистрироваться"
            }
          </button>
        </form>
      </div>
    </div>
  );
});
