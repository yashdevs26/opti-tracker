import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import ErrorComponent from './generic/ErrorComponent'
import Footer from "./landing/components/Footer";
import HeaderComponent from './generic/HeaderComponent'
import HomeComponent from './generic/HomeComponent'
import UserComponent from './app-logic/UserComponent'
import UserProfileComponent from './app-logic/UserProfile'
import LogoutComponent from './entry-actions/LogoutComponent'
import TaskInfoComponent from './app-logic/TaskInfo';
import TaskComponent from './app-logic/TaskComponent';
import { useAuth } from './security/AuthContext'

function AuthenticatedRoute({ children }) {
    const authContext = useAuth()
    if (authContext.isAuthenticated)
        return children

    return <Navigate to="/" />
}
export default function OptiTracker() {

    return (
        <div className="OptiTracker">
            <BrowserRouter>
                {/* <HeaderComponent /> */}
                <AuthenticatedRoute>
                    <HeaderComponent />
                </AuthenticatedRoute>
                <Routes>

                    {/* <Route path='/' element={<LandingComponent />} /> */}
                    <Route path='/' element={
                        <AuthenticatedRoute>
                            {/* <LandingComponent /> */}
                        </AuthenticatedRoute>
                    } />

                    <Route path='/home' element={
                        <AuthenticatedRoute>
                            <HomeComponent />
                        </AuthenticatedRoute>
                    } />

                    <Route path='/users' element={
                        <AuthenticatedRoute>
                            <UserProfileComponent />
                        </AuthenticatedRoute>
                    } />

                    <Route path='/users/tasks' element={
                        <AuthenticatedRoute>
                            <TaskInfoComponent />
                        </AuthenticatedRoute>
                    } />

                    <Route path='/users/tasks/:taskId' element={
                        <AuthenticatedRoute>
                            <TaskComponent />
                        </AuthenticatedRoute>
                    } />

                    <Route path='/users/:userId' element={
                        <AuthenticatedRoute>
                            <UserComponent />
                        </AuthenticatedRoute>
                    } />

                    <Route path='/logout' element={
                        <AuthenticatedRoute>
                            <LogoutComponent />
                        </AuthenticatedRoute>
                    } />
                    <Route path='*' element={<ErrorComponent />} />
                </Routes>
                {/* <FooterComponent /> */}
                {/* <Footer /> */}
                <AuthenticatedRoute>
                    <Footer />
                </AuthenticatedRoute>
            </BrowserRouter>
        </div>
    )
}