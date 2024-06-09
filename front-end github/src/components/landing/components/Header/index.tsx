import { Row } from "antd";
import Container from "../../common/Container";
import LoginComponent from "../../../entry-actions/LoginComponent";
import {
  HeaderSection,
  LogoContainer,
  Burger,
  NotHidden,
  Menu,
  CustomNavLinkSmall,
  Label,
  Outline,
  Span,
} from "./styles";

const Header = () => {

  const MenuItem = () => {
    const scrollTo = (id: string) => {
      const element = document.getElementById(id) as HTMLDivElement;
      element.scrollIntoView({
        behavior: "smooth",
      });
    };
    return (
      <>
        {/* <CustomNavLinkSmall onClick={() => scrollTo("features")}>
          <Span>{("Features")}</Span>
        </CustomNavLinkSmall>
        <CustomNavLinkSmall onClick={() => scrollTo("technical")}>
          <Span>{("Technical")}</Span>
        </CustomNavLinkSmall> */}
        <CustomNavLinkSmall>
          <Span>
            <LoginComponent />
          </Span>
        </CustomNavLinkSmall>
      </>
    );
  };

  return (
    <HeaderSection>
      <Container>
        <Row justify="space-between">
          <LogoContainer to="http://yashdevs.com/" aria-label="homepage">
            <h4>yashdevs.com</h4>
          </LogoContainer>
          <NotHidden>
            <MenuItem />
          </NotHidden>
        </Row>
      </Container>
    </HeaderSection>
  );
};

export default (Header);
