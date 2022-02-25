import React from "react";
import { Container } from "react-bootstrap";

const Header = () => {
  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=Lobster&display=swap"
        rel="stylesheet"
      />
      <Container fluid style={{ margin: "30px 0 70px 0" }}>
        <h1 className="text-center" style={{ fontFamily: "Lobster" }}>
          My Instagram
        </h1>
      </Container>
    </>
  );
};

export default Header;
