import React, { useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import Modal from "react-modal";
import axios from "axios";

const PostImage = ({ backAPI, getImages }) => {
  const [openModal, setOpenModal] = useState(false);
  const [imageFile, setImageFile] = useState([]);
  const [title, setTitle] = useState([]);
  const [message, setMessage] = useState([]);

  const handleModal = (e) => {
    e.preventDefault();
    setOpenModal(!openModal);
  };

  const handleInput = (e) => {
    const id = e.target.id;
    if (id === "title") {
      setTitle(e.target.value);
    } else {
      setMessage(e.target.value);
    }
  };

  const handleImageFile = (e) => {
    setImageFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formdata = new FormData();

    formdata.append("files", imageFile);

    const requestDto = {
      name: title,
      msg: message,
    };

    formdata.append("requestDto", JSON.stringify(requestDto));

    await axios.post(backAPI, formdata).catch((err) => {
      alert(err);
    });
    setOpenModal(false);
    getImages(backAPI);
  };

  const customStyles = {
    content: {
      top: "50%",
      left: "50%",
      right: "auto",
      bottom: "auto",
      marginRight: "-50%",
      transform: "translate(-50%, -50%)",
    },
  };

  return (
    <Container style={{ textAlign: "center" }}>
      <Button
        style={{
          color: "white",
          fontWeight: "bold",
          fontSize: "32px",
          padding: "0px 12px 8px 12px",
        }}
        variant="info"
        onClick={handleModal}
      >
        +
      </Button>
      {openModal && (
        <Modal
          isOpen={openModal}
          onRequestClose={handleModal}
          contentLabel="from"
          ariaHideApp={false}
          style={customStyles}
        >
          <Form validated={true} onSubmit={handleSubmit}>
            <Form.Group id="file" className="mb-3" onChange={handleImageFile}>
              <Form.Label>Upload your picture</Form.Label>
              <Form.Control type="file" />
            </Form.Group>
            <Form.Control
              id="title"
              size="sm"
              type="text"
              placeholder="Title of picture"
              onChange={handleInput}
            />
            <Form.Control
              id="message"
              size="sm"
              type="text"
              placeholder="Leave a Message"
              onChange={handleInput}
            />
            <Button
              variant="primary"
              type="submit"
              style={{ marginTop: "10px" }}
            >
              Submit
            </Button>
          </Form>
        </Modal>
      )}
    </Container>
  );
};

export default PostImage;
