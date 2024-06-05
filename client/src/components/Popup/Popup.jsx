import { createPortal } from "react-dom";
import styles from "./Popup.module.css";
import { useEffect, useRef } from "react";

export const Popup = ({ children, open, close }) => {
  const containerRef = useRef(null);

  useEffect(() => {
    if(open){
      document.body.style.overflowY = "hidden";
    }
    return () => {
      document.body.style.overflowY = "visible";
    };
  }, [open]);

  // eslint-disable-next-line react-hooks/exhaustive-deps
  function handleClick (event) {
    if (event.target === containerRef.current) {
      close();
    }
  };

  return (
    <>
      {open
        ? createPortal(
            <div
              ref={containerRef}
              className={styles.container}
              onClick={handleClick}
            >
              <div className={styles.popup}>
                <button className={styles.buttonClose} onClick={close}>
                  X
                </button>
                <div className={styles.content}>{children}</div>
              </div>
            </div>,
            document.getElementById("popup-root")
          )
        : null}
    </>
  );
};
