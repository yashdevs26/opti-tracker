import { Row, Col } from "antd";
import { Slide } from "react-awesome-reveal";
import { Button } from "../../common/Button";
import { MiddleBlockSection, Content, ContentWrapper } from "./styles";

interface MiddleBlockProps {
  title: string;
  content: string;
  button: string;
}

const MiddleBlock = ({ title, content, button }: MiddleBlockProps) => {
  const scrollTo = (id: string) => {
    const element = document.getElementById(id) as HTMLDivElement;
    element.scrollIntoView({
      behavior: "smooth",
    });
  };
  return (
    <MiddleBlockSection>
      <Slide direction="up" triggerOnce>
        <Row justify="center" align="middle">
          <ContentWrapper>
            <Col lg={24} md={24} sm={24} xs={24}>
              <h6>{(title)}</h6>
              <br />
              <Content>1. Unique user registrations handled by backed api</Content>
              <Content>2. Email verification service with failsafe which confirms the authenticity of email and user</Content>
              <Content>3. Secure wrapping of backend REST API using JWT with RSA Algorithm</Content>
              <Content>4. Admin dashboard for user profile management</Content>
              <Content>5. Task tracking with description, status and (start, target and end) periods</Content>
              {/* <Content>{(content)}</Content>
              <Content>{(content)}</Content> */}
              {/* {button && (
                <Button name="submit" onClick={() => scrollTo("mission")}>
                  {(button)}
                </Button>
              )} */}
            </Col>
          </ContentWrapper>
        </Row>
      </Slide>
    </MiddleBlockSection>
  );
};

export default (MiddleBlock);
