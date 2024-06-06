import React from "react";
import styles from "./ModeItem.module.css";

export const ModeItem = ({ modeTitle, isActive, handleClick }) => {
  return (
    <button
      className={`${styles.modeBtn} ${isActive ? styles.active : ""} btn`}
      onClick={handleClick}
    >
      {modeTitle}
    </button>
  );
};
