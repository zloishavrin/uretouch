import React from "react";
import { useState, useEffect } from "react";
import { getHistoryList } from "../../service/UserService";
import styles from "./HistoryList.module.css";
import { HistoryItem } from "../HistoryItem/HistoryItem";

export const HistoryList = () => {
  const [history, setHistory] = useState([]);
  const [error, setError] = useState(null);
  const [isLoading, setLoading] = useState(true);

  useEffect(() => {
    const fetchHistoryList = async () => {
      try {
        const data = await getHistoryList();
        console.log(data);
        setHistory(data);
      } catch (e) {
        setError(e.message);
      } finally {
        setLoading(false);
      }
    };

    fetchHistoryList();
  }, []);

  if (isLoading) {
    return <span className={styles.loader}></span>;
  }

  return (
    <ul className={styles.historyList}>
      {history.map((item) => (
        <HistoryItem
          key={item._id}
          imgOriginal={item.original}
          listImg={item.url}
          prompt={item.prompt}
        />
      ))}
    </ul>
  );
};
