import { Row, Col } from "antd";
import { Slide } from "react-awesome-reveal";
import { MiddleBlockSection, CardContentWrapper } from "./styles";

interface ContentCardBlockProps {
  title: string;
  id: string;
}

const ContentCardBlock = ({ title, id }: ContentCardBlockProps) => {
/*   const scrollTo = (id: string) => {
    const element = document.getElementById(id) as HTMLDivElement;
    element.scrollIntoView({
      behavior: "smooth",
    });
  }; */
  return (
    <MiddleBlockSection>
      <Slide direction="up" triggerOnce>
        <Row justify="center" align="middle">
          <CardContentWrapper>
            <Col key={id} lg={34} md={24} sm={24} xs={24}>
              <h6>{(title)}</h6>
              <br></br>
                <div className="row">
                  <div className="col-sm-6">
                    <div className="card">
                      <div className="card-body">
                        <h4>Spring Boot 3.2+, Java 17 and Java Mail API</h4>
                        <p className="card-text">With power of Spring Boot, the application has enhanced capabilities including sending verification links to valid email ids of the user at the time of registeration </p>
                      </div>
                    </div>
                  </div>
                  
                  <div className="col-sm-6">
                    <div className="card">
                      <div className="card-body">
                        <h4>Spring Security and Spring Data JPA and OAuth2 Resource Server</h4>
                        <p className="card-text">Uses JWT based authentication to encapsulate API and JPA to communicate with the MySQL Database</p>
                      </div>
                    </div>
                  </div>
                </div>
                <br></br>

                <div className="row">
                  <div className="col-sm-6">
                    <div className="card">
                      <div className="card-body">
                        <h4>React and Axios</h4>
                        <p className="card-text">UI Rendering made possible with React and API configuration made by Axios</p>
                      </div>
                    </div>
                  </div>
                  
                  <div className="col-sm-6">
                    <div className="card">
                      <div className="card-body">
                        <h4>Amazon Web Services (AWS)</h4>
                        <p className="card-text">With backend server running on Elastic Beanstalk, front end on S3 instance and with a seperate instance of RDB, the frontend and backend are seperated inside the cloud environment and communicate with each other</p>
                      </div>
                    </div>
                  </div>
                </div>
            </Col>
          </CardContentWrapper>
        </Row>
      </Slide>
    </MiddleBlockSection>
  );
};

export default ContentCardBlock;
