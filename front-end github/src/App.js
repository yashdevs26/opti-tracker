import './App.css';
import OptiTracker from './components/OptiTracker'
import LandingComponent from './components/landing/main'
import { Route, Routes, BrowserRouter } from 'react-router-dom'
import AuthProvider from './components/security/AuthContext';

function App() {
  //const authContext = useAuth()
  return (
    <div className="App">
      <AuthProvider>
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<LandingComponent />} />
            {/* <Route path='*' element={<OptiTracker />} /> */}
          </Routes>
        </BrowserRouter>
        <OptiTracker />
      </AuthProvider>
    </div>

  );
}

export default App;
