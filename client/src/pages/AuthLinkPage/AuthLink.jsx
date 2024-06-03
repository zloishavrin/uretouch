import React from "react";
import styles from "./AuthLinkPage.module.css";

export const AuthLink = () => {
  return (
    <div className={styles.auth}>
      <div className={styles.authBox}>
        <p>Вам на почту выслана <span>ссылка активации аккаунта</span></p>
      </div>
    </div>
  );
};
