import React from "react";
import styles from "./User.module.css";
import { getInfoUser } from "../../service/UserService";
import { useState, useEffect } from "react";
import { getApiKeyUser } from "../../service/UserService";
import { useContext } from "react";
import { Context } from '../../index';
import { observer } from "mobx-react-lite";

export const User = observer(() => {
  const [error, setError] = useState(null);
  const [user, setUser] = useState({});
  const [apiKeyUser, setApiKeyUser] = useState(null);

  const { authStore } = useContext(Context);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const data = await getInfoUser();
        setUser(data);
      } catch (e) {
        setError(e.message);
      }
    };

    const fetchApiKey = async () => {
      try {
        const {apiKey} = await getApiKeyUser();
        setApiKeyUser(apiKey);
      } catch (e) {
        setError(e.message);
      }
    };

    fetchUser();
    fetchApiKey();
  }, []);

  if (error) {
    return <div>{error}</div>;
  }
  return (
    <div className="container">
      <div className={styles.userContainer}>
        <div className={styles.userInfo}>
          <p>Почта</p>
          <p>{user.email}</p>
        </div>
        <div className={styles.userInfo}>
          <p>API-ключ</p>
          <p>{apiKeyUser}</p>
        </div>
      </div>
      <button className={`${styles.userBtn} btn`} onClick={() => authStore.logout()}>Выйти</button>
    </div>
  );
});
