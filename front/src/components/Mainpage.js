import React, { useState, useEffect } from "react";
import axios from "axios";
import { Container, Card } from "react-bootstrap";
import PostImage from "./PostImage";

const Mainpage = () => {
  // TODO infinite scroll
  // 백엔드 api에서 조금씩만 불러와서 무한 스크롤로 구현
  const [images, setImages] = useState([]);
  const back = "http://localhost:8080";
  const backAPI = back + "/images";

  useEffect(() => {
    // 도커에 올릴때 ip 수정
    getImages(backAPI);
  }, []);

  const getImages = async (response) => {
    let img = [];
    img = img.concat(
      await axios.get(response).then((response) => response.data)
    );
    setImages(img.concat());
  };

  return (
    <Container style={{ width: "50%" }}>
      <PostImage getImages={getImages} backAPI={backAPI} />
      <div className="card-columns">
        {images.map((image) => (
          <Card
            display="inline-block"
            style={{ margin: "20px 0 20px 0" }}
            key={image.id}
          >
            <Card.Img src={back + image.fileURI[0]} />
            <Card.Body>
              <Card.Title>{image.name}</Card.Title>
              <Card.Text>{image.msg}</Card.Text>
            </Card.Body>
          </Card>
        ))}
      </div>
    </Container>
  );
};

export default Mainpage;
