import React from "react";
import { Link } from "react-router-dom";
import styles from "./HeaderButton.module.css";

export const HeaderButton = ({ currentPath, path, title }) => {
  const HeaderButtonStyles = [styles.button];

  if (currentPath === path) {
    HeaderButtonStyles.push(styles.active);
  }

  return (
    <div className={HeaderButtonStyles.join(' ')}>
      <Link to={path}>{title}</Link>
      <div></div>
    </div>
  );
};
