import React from "react";
import styles from "./HistoryItem.module.css";
import { Popup } from "../Popup/Popup";
import { useState } from "react";
import { History } from "../AboutHistory/History";

export const HistoryItem = ({ imgOriginal, listImg, prompt, status }) => {
  const [isOpenPopup, setIsOpenPopup] = useState(false);

  const backgroundImg = {
    background: `url(${imgOriginal})`,
    backgroundRepeat: "no-repeat",
    backgroundSize: "contain",
    backgroundPosition: "center",
  };

  console.log(status);

  if (status === "inProgress") {
    return (
      <li className={styles.historyItemLoad}>
        <div className={styles.loader}></div>
        <div className={styles.historyImgBox} style={backgroundImg}></div>
      </li>
    );
  }

  const handleOpenPopup = () => {
    setIsOpenPopup(true);
  };

  const handleClosePopup = () => {
    setIsOpenPopup(false);
  };

  return (
    <>
      <Popup open={isOpenPopup} close={handleClosePopup}>
        <History prompt={prompt} listImg={listImg} />
      </Popup>
      <li className={styles.historyItem} onClick={handleOpenPopup}>
        <div className={styles.historyImgBox} style={backgroundImg}></div>
      </li>
    </>
  );
};
