import { createContext, useContext, useState } from "react";
import { apiClient } from "../api/ApiClient";
import { executeJwtAuthenticationService } from "../api/AuthApiService";

//1: Create a Context
export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

//2: Share the created context with other components
export default function AuthProvider({ children }) {

    //3: Put some state in the context
    const [isAuthenticated, setAuthenticated] = useState(false)
    const [userId, setUserId] = useState(0)
    const [token, setToken] = useState(null)

    async function login(email, password) {

        try {
            const response = await executeJwtAuthenticationService(email, password)

            if (response.status == 200) {
                const jwtToken = 'Bearer ' + response.data.jwtToken
                setAuthenticated(true)
                setUserId(response.data.userId)
                setToken(jwtToken)
                localStorage.setItem('userId', userId)

                apiClient.interceptors.request.use(
                    (config) => {
                        console.log('intercepting and adding a token')
                        config.headers.Authorization = jwtToken
                        return config
                    }
                )
                return true;
            } else {
                logout()
                return false
            }
        } catch (error) {
            logout()
            return false
        }
    }


    function logout() {
        setAuthenticated(false)
        setToken(null)
        setUserId(0)
    }

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout, token, userId }}>
            {children}
        </AuthContext.Provider>
    )
} 