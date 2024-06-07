import React from "react";
import { useState, useEffect } from "react";
import { getHistoryList } from "../../service/UserService";
import styles from "./HistoryList.module.css";
import { HistoryItem } from "../HistoryItem/HistoryItem";
import { Link } from  "react-router-dom";

export const HistoryList = () => {
  const [history, setHistory] = useState([]);
  const [error, setError] = useState(null);
  const [isLoading, setLoading] = useState(true);

  useEffect(() => {
    const fetchHistoryList = async () => {
      try {
        const data = await getHistoryList();
        setHistory(data);
      } catch (e) {
        setError(e.message);
      } finally {
        setLoading(false);
      }
    };
    fetchHistoryList();
    setInterval(fetchHistoryList, 5000)
    return () => {
      clearInterval(fetchHistoryList);
    }
  }, []);

  if (isLoading) {
    return <span className={styles.loader}></span>;
  }

  if (history.length === 0) {
    return (
      <div className={styles.historyEmpty}>
        Пока что у Вас нет истории генераций. <Link className={styles.historyEmptyLink} to="/generation">Перейдите к генерации.</Link>
      </div>
    );
  }

  return (
    <ul className={styles.historyList}>
      {history.map((item) => (
        <HistoryItem
          key={item._id}
          imgOriginal={item.original}
          listImg={item.url}
          prompt={item.prompt}
          status={item.status}
        />
      ))}
    </ul>
  );
};
