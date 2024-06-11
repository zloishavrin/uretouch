import React, { useState, useEffect, useCallback } from "react";
import { getGenerationMode } from "../../service/UserService";
import { ModeItem } from "../../components/ModeItem/ModeItem";
import styles from "./Generation.module.css";
import { useDropzone } from "react-dropzone";
import { SVGSelector } from "../../components/SVGSelector/SVGSelector";
import { $authHost } from "../../service/api";
import { getGeneration } from "../../service/UserService";
import { Slider } from "../../components/Slider/Slider";

export const Generation = () => {
  const [modeList, setModeList] = useState([]);
  const [activeBtn, setActiveBtn] = useState(null);
  const [isModeHand, setModeHand] = useState(false);
  const [file, setFile] = useState(null);
  const [customPrompt, setCustomPrompt] = useState("");
  const [typeMode, setTypeMode] = useState("");
  const [generationIds, setGenerationIdsList] = useState([]);
  const [generationList, setGenerationList] = useState([]);

  const { getRootProps, getInputProps } = useDropzone({
    accept: "image/*",
    onDrop: (acceptedFiles) => {
      const file = acceptedFiles[0];
      setFile(
        Object.assign(file, {
          preview: URL.createObjectURL(file),
        })
      );
    },
  });

  const backgroundImg = {
    backgroundImage: `url(${file ? file.preview : ""})`,
  };

  useEffect(() => {
    return () => {
      if (file) {
        URL.revokeObjectURL(file.preview);
      }
    };
  }, [file]);

  const handleItemClick = (index, mode) => {
    setActiveBtn(index);
    setTypeMode(mode);
    setCustomPrompt("");
  };

  const handleModeHand = () => {
    setModeHand(true);
    setActiveBtn(null);
    setTypeMode("");
  };

  useEffect(() => {
    if (activeBtn !== null) {
      setModeHand(false);
    }
  }, [activeBtn]);

  useEffect(() => {
    const fetchMode = async () => {
      try {
        const data = await getGenerationMode();
        setModeList(data);
      } catch (e) {
        console.log(e.message);
      }
    };

    fetchMode();
  }, []);

  const submitGeneration = async () => {
    if (!file) {
      alert("Вы не выбрали файл");
      return;
    }

    if (customPrompt === "" && typeMode === "") {
      alert("Вы не выбрали мод");
      return;
    }

    const formData = new FormData();
    formData.append("image", file);
    formData.append("mode", typeMode);
    formData.append("prompt", customPrompt);

    const tempId = Date.now();

    const newItem = { id: tempId, url: [], status: "inProgress" };
    setGenerationList((prev) => [...prev, newItem]);

    try {
      const response = await $authHost.post("/generation/private", formData);
      const newGenerationId = response.data.generation_id;
      setGenerationIdsList((prev) => [...prev, newGenerationId]);
      setGenerationList((prev) =>
        prev.map((item) =>
          item.id === tempId ? { ...item, id: newGenerationId } : item
        )
      );
      setCustomPrompt("");
      setTypeMode("");
      setActiveBtn(null);
      setFile(null);
    } catch (e) {
      console.log(e.message);
    }
  };

  const fetchGeneration = useCallback(async () => {
    try {
      if (generationIds.length > 0) {
        const data = await getGeneration(generationIds.join(","));
        setGenerationList((prev) =>
          prev.map((item) => {
            const newItem = data.find((d) => d._id === item.id);
            return newItem ? { ...item, ...newItem } : item;
          })
        );
      }
    } catch (e) {
      console.log(e.message);
    }
  }, [generationIds]);

  useEffect(() => {
    const intervalId = setInterval(fetchGeneration, 5000);
    return () => clearInterval(intervalId);
  }, [fetchGeneration]);

  return (
    <div className="container">
      <div className={styles.generationContainer}>
        <div className={styles.generationDragonDrop}>
          <div {...getRootProps({ className: styles.dropzone })}>
            <input {...getInputProps()} />
            {!file ? (
              <div className={styles.generationLoadContainer}>
                <SVGSelector
                  type="arrow-down"
                  className={styles.generationLoadContainerSvg}
                />
              </div>
            ) : (
              <div className={styles.generationImgBox}>
                <div
                  className={styles.generationImgLoad}
                  style={backgroundImg}
                ></div>
              </div>
            )}
          </div>
        </div>
        <div className={styles.generationModeContainer}>
          <div className={styles.generationModeList}>
            {modeList.map((mode, index) => (
              <ModeItem
                key={mode.name}
                modeTitle={mode.name}
                isActive={activeBtn === index}
                handleClick={() => handleItemClick(index, mode._id)}
              />
            ))}
            <button
              className={`${styles.generationBtn} ${
                isModeHand ? styles.active : ""
              } btn`}
              onClick={handleModeHand}
            >
              Ручной
            </button>
          </div>
          {isModeHand && (
            <textarea
              className={styles.generationTextArea}
              placeholder="Введите текст"
              value={customPrompt}
              onChange={(e) => setCustomPrompt(e.target.value)}
            ></textarea>
          )}
          <button
            className={`${styles.generationBtn} btn`}
            onClick={submitGeneration}
          >
            Запустить
          </button>
        </div>
        <div>
          {generationList.map((item) => (
            <div className={styles.generationListContainer} key={item.id}>
              <Slider listImg={item.url} status={item.status} />
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};
