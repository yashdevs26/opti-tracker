import { Row, Col } from "antd";
import { SvgIcon } from "../../common/SvgIcon";
import Container from "../../common/Container";

import {
  FooterSection,
  Title,
  NavLink,
  Extra,
  LogoContainer,
  Para,
  Large,
  Chat,
  Empty,
  FooterContainer,
  Language,
  Label,
  LanguageSwitch,
  LanguageSwitchContainer,
} from "./styles";

interface SocialLinkProps {
  href: string;
  src: string;
}

const Footer = () => {

  const SocialLink = ({ href, src }: SocialLinkProps) => {
    return (
      <a
        href={href}
        target="_blank"
        rel="noopener noreferrer"
        key={src}
        aria-label={src}
      >
        <SvgIcon src={src} width="25px" height="25px" />
      </a>
    );
  };

  return (
    <>
      <Extra>
        <Container border={true}>
          <Row
            justify="space-between"
            align="middle"
            style={{ paddingTop: "3rem" }}
          >
            <NavLink to="https://yashdevs.com/">
              <LogoContainer>
                <h2>yashdevs.com</h2>
              </LogoContainer>
            </NavLink>
            <FooterContainer>
              <SocialLink
                href="https://github.com/yashdevs26"
                src="github.svg"
              />
              {/* <SocialLink
                href="https://twitter.com/Adrinlolx"
                src="twitter.svg"
              /> */}
              <SocialLink
                href="https://linkedin.com/in/yashvardhan-dwivedi"
                src="linkedin.svg"
              />
             {/*  <SocialLink
                href="https://github.com/sirius-y"
                src="github.svg"
              /> */}
              {/* <SocialLink
                href="https://medium.com/@lashakakabadze/"
                src="medium.svg"
              /> */}
              {/* <a
                href="https://ko-fi.com/Y8Y7H8BNJ"
                target="_blank"
                rel="noopener noreferrer"
              >
                <img
                  height="36"
                  style={{ border: 0, height: 36 }}
                  src="https://storage.ko-fi.com/cdn/kofi3.png?v=3"
                  alt="Buy Me a Coffee at ko-fi.com"
                />
              </a> */}
            </FooterContainer>
          </Row>
        </Container>
      </Extra>
    </>
  );
};

export default Footer;
