import { lazy, Suspense } from "react";
import { Routes, Route } from "react-router-dom";
import Footer from "../components/Footer";
import Home from "../pages/Home";
import Header from "../components/Header";
import { Styles } from "../../../styles";

const LandingComponent = () => {
  return (
    <>
      <Suspense fallback={null}>
        <Styles />
        <Header />
        <Routes>
          <Route path='/' element={<Home />} />
        </Routes>
        <Footer />
      </Suspense>
    </>
  );
};

export default LandingComponent;
