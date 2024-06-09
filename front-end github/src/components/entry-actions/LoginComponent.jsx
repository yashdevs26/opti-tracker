import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import './Entry-Actions.css'
import { StyledButton } from "../landing/common/Button/styles";
import UserComponent from '../app-logic/UserComponent'
import { useAuth } from '../security/AuthContext'


function LoginComponent() {

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [showErrorMessage, setShowErrorMessage] = useState(false)
    const navigate = useNavigate()

    const authContext = useAuth()

    function handleEmailChange(event) {
        setEmail(event.target.value)
    }

    function handlePasswordChange(event) {
        setPassword(event.target.value)
    }

    async function handleSubmit() {
        if (await authContext.login(email, password)) {
            navigate(`/home`)
        } else {
            setShowErrorMessage(true)
        }
        console.log('Form submitted');
    }
    const [isLoginFormVisible, setIsLoginFormVisible] = useState(false);
    const [showSignUp, setShowSignUp] = useState(false)

    const handleLoginClick = () => {
        setIsLoginFormVisible(!isLoginFormVisible);
        setShowErrorMessage(false)
    };

    const handleSignUpClick = () => {
        setShowSignUp(!showSignUp);
        setShowErrorMessage(false)
    };

    return (
        <>
            <StyledButton onClick={handleLoginClick}>Login/Sign Up</StyledButton>
            {isLoginFormVisible && (
                <div className="popup">
                    <div className="popup-inner">
                        <div className="Login">
                            {!showSignUp && <h1>Login</h1>} {showSignUp && <h1>Sign Up</h1>} <span className='closeButton'><button type="button" name="showSignUp" className="close-button" onClick={handleLoginClick}>&times;</button></span>
                            {showErrorMessage && <div className="errorMessage">Authentication Failed. Please check your credentials.</div>}
                            {!showSignUp &&

                                <div className="LoginForm">
                                    <div>
                                        <label>Email Id </label>
                                        <input type="email" name="email" value={email} onChange={handleEmailChange} />
                                    </div>

                                    <div>
                                        <label>Password </label>
                                        <input type="password" name="password" value={password} onChange={handlePasswordChange} />
                                    </div>
                                    <div>
                                        <button type="button" name="login" onClick={handleSubmit}>Submit</button>
                                    </div>
                                </div>
                            }

                            {showSignUp &&
                                <div>
                                    <UserComponent />
                                </div>
                            }

                            {!showSignUp && <>
                                <br />
                                <p> new user?  <button type="button" name="showSignUp" onClick={handleSignUpClick}>Sign Up here</button> </p>
                            </>
                            }
                            {showSignUp && <>
                                <br />
                                <p> returning user?  <button type="button" name="showSignUp" onClick={handleSignUpClick}>Login here</button> </p>
                            </>
                            }
                        </div>
                    </div>
                </div>
            )}
        </>
    )
}

export default LoginComponent