import React from "react";
import { Slider } from "../Slider/Slider";
import "./History.css";

export const History = ({ prompt, listImg }) => {
  return (
    <div className="containerHistory">
      <div className="contentLeft">
        <p className="promptHistory">{prompt}</p>
        <button className="historyBtn btn">В редактор</button>
      </div>
      <div>
        <Slider listImg={listImg} />
      </div>
    </div>
  );
};
