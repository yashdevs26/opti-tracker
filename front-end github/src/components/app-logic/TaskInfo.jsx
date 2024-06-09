import { useEffect, useState } from "react"
import {useNavigate} from 'react-router-dom'
import { retrieveAllUserTasksApi, deleteTaskApi } from "../api/TaskInfoApiService"
import { useAuth } from '../security/AuthContext'

function TaskInfoComponent() {

    const navigate = useNavigate()
    const [tasks,setTasks] = useState([])
    const [message,setMessage] = useState(null)
    const authContext = useAuth()
    const userId = authContext.userId


    useEffect ( () => refreshTasks(), [])

    function refreshTasks() {
        
        retrieveAllUserTasksApi(userId)
        
        .then(response => {
            setTasks(response.data)
            console.log(tasks)
        })
        .catch(error => console.log(error))
    }

    function deleteTask(taskId) {
        console.log(taskId)
        deleteTaskApi(taskId)
        .then(

            () => {
                setMessage(`Delete of task with id = ${taskId} successful`)
                refreshTasks()
            }
            //1: Display message
            //2: Update Todos list
        )
        .catch(error => console.log(error))
    }

    function updateTask(taskId) {
        console.log('updatetask ' + taskId)
        navigate(`/users/tasks/${taskId}`, taskId)
    }

    function addTask() {
        navigate(`/users/tasks/0`)
    }

    return (
        <div className="container">
            <h1>Task Info</h1>
            
            {message && <div className="alert alert-warning">{message}</div>}

            
            <div>
                <table className="table">
                    <thead>
                            <tr>
                                <th>Description</th>
                                <th>Category</th>
                                <th>Status</th>
                            </tr>
                    </thead>
                    <tbody>
                    {
                        tasks.map(
                            task => (
                                <tr key={task.taskId}>
                                    <td>{task.taskDescription}</td>
                                    <td>{task.taskCategory}</td>
                                    <td>{task.taskStatus}</td>
                                    <td> <button className="btn btn-warning" 
                                                    onClick={() => deleteTask(task.taskId)}>Delete</button> </td>
                                    <td> <button className="btn btn-success" 
                                                    onClick={() => updateTask(task.taskId)}>Update</button> </td>
                                </tr>
                            )
                        )
                    }
                    </tbody>

                </table>
            </div>
            <div className="btn btn-success m-5" onClick={addTask}>Add New Task</div>
        </div>
    )
}

export default TaskInfoComponent