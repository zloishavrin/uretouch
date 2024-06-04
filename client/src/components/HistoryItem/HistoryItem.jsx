import React from 'react'
import styles from './HistoryItem.module.css';

export const HistoryItem = ({imgOriginal, listImg, prompt}) => {

  const backgroundImg = {
    background: `url(${imgOriginal})`,
    backgroundRepeat: "no-repeat",
    backgroundSize: "contain",
    backgroundPosition: "center",
  }
  return (
    <li className={styles.historyItem}>
      <div className={styles.historyImgBox} style={backgroundImg}>
      </div>
    </li>
  )
}
