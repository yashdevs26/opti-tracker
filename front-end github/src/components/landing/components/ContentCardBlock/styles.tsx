import styled from "styled-components";

export const MiddleBlockSection = styled("section")`
  position: relative;
  padding: 1.5rem 0 3rem;
  text-align: center;
  display: flex;
  justify-content: center;
  color: #18216d;

  @media screen and (max-width: 1024px) {
    padding: 5.5rem 0 3rem;
  }
`;

export const Content = styled("p")`
  padding: 0.75rem 0 0.75rem;
  color: #18216d;
`;

export const ContentWrapper = styled("div")`
  max-width: 570px;
  color: #18216d;

  @media only screen and (max-width: 768px) {
    max-width: 100%;
  }
`;

export const CardContentWrapper = styled("div")`
  max-width: 100%;
  color: #18216d;
`;
