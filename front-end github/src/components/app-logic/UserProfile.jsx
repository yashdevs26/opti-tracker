import { useEffect, useState } from "react"
import {useNavigate} from 'react-router-dom'
import { retrieveAllUserApi, deleteUserApi } from "../api/UserInfoApiService"
//import { useAuth } from "./security/AuthContext"

function UserProfileComponent() {

    const navigate = useNavigate()

    const [users,setUsers] = useState([])

    const [message,setMessage] = useState(null)
    
    useEffect ( () => refreshUsers(), [])

    function refreshUsers() {
        
        retrieveAllUserApi()
        
        .then(response => {
            setUsers(response.data)
            console.log(users)
        })
        .catch(error => console.log(error))
    }

    function deleteUser(userId) {
        console.log(userId)
        deleteUserApi(userId)
        .then(

            () => {
                setMessage(`Delete of user with id = ${userId} successful`)
                refreshUsers()
            }
            //1: Display message
            //2: Update Todos list
        )
        .catch(error => console.log(error))
    }

    function updateUser(userId) {
        console.log('updateUser ' + userId)
        navigate(`/users/${userId}`, userId)
    }

    function addUser() {
        navigate(`/users/0`)
    }

    return (
        <div className="container">
            <h1>User Profile</h1>
            
            {message && <div className="alert alert-warning">{message}</div>}

            
            <div>
                <table className="table">
                    <thead>
                            <tr>
                                <th>User Name</th>
                                <th>Full Name</th>
                                <th>Email</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                    </thead>
                    <tbody>
                    {
                        users.map(
                            user => (
                                <tr key={user.userId}>
                                    <td>{user.userNameEntity}</td>
                                    <td>{user.fullName}</td>
                                    <td>{user.email}</td>
                                    <td> <button className="btn btn-warning" 
                                                    onClick={() => deleteUser(user.userId)}>Delete</button> </td>
                                    <td> <button className="btn btn-success" 
                                                    onClick={() => updateUser(user.userId)}>Update</button> </td>
                                </tr>
                            )
                        )
                    }
                    </tbody>

                </table>
            </div>
            <div className="btn btn-success m-5" onClick={addUser}>Add New User</div>
        </div>
    )
}

export default UserProfileComponent