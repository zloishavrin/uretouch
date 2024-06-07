import React from "react";
import { useState, useRef } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import { SVGSelector } from "../SVGSelector/SVGSelector";

import "swiper/css";
import "swiper/css/free-mode";
import "swiper/css/navigation";
import "swiper/css/thumbs";

import "./History.css";

import { FreeMode, Navigation, Thumbs } from "swiper/modules";

export const History = ({ prompt, listImg }) => {
  const [thumbsSwiper, setThumbsSwiper] = useState(null);
  const [copySuccess, setCopySuccess] = useState('');

  const copyTextToClipboard = async (text) => {
    try {
      await navigator.clipboard.writeText(text);
      alert('Картинка успешно скопирована!');
      setCopySuccess('Картинка успешно скопирована!')
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <div className="containerHistory">
      <div className="contentLeft">
        <p className="promptHistory">{prompt}</p>
        <button className="historyBtn btn">В редактор</button>
      </div>
      <div>
        <Swiper
          style={{
            "--swiper-navigation-color": "#fff",
            "--swiper-pagination-color": "#fff",
          }}
          spaceBetween={10}
          navigation={true}
          thumbs={{ swiper: thumbsSwiper }}
          modules={[FreeMode, Navigation, Thumbs]}
          className="mySwiper2"
        >
          {listImg.map((item, index) => (
            <SwiperSlide key={index}>
              <a
                href={item}
                download="Download.jpg"
                target="_blank"
                className="historyDownload btn"
              >
                <SVGSelector type="arrow-down" className="historyDownloadSVG" />
              </a>
              <button className="btnHistoryCopy btn" onClick={() => copyTextToClipboard(item)}>url</button>
              <img src={item} />
            </SwiperSlide>
          ))}
        </Swiper>
        <Swiper
          onSwiper={setThumbsSwiper}
          spaceBetween={10}
          slidesPerView={4}
          freeMode={true}
          watchSlidesProgress={true}
          modules={[FreeMode, Navigation, Thumbs]}
          className="mySwiper"
        >
          {listImg.map((item, index) => (
            <SwiperSlide key={index}>
              <img src={item} />
            </SwiperSlide>
          ))}
        </Swiper>
      </div>
    </div>
  );
};