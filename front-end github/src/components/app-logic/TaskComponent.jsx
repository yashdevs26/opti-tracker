import { useEffect, useState } from 'react'
import {useParams, useNavigate} from 'react-router-dom'
import { createUserTaskApi, retrieveAllTasksApi, retrieveTaskApi, updateUserTaskApi, deleteTaskApi} from '../api/TaskInfoApiService'
import { useAuth } from '../security/AuthContext'
import {Formik, Form, Field, ErrorMessage} from 'formik'

import '../css/OptiTracker.css'

export default function TaskComponent() {
    
    const {taskId} = useParams()
    const authContext = useAuth()
    const navigate = useNavigate()
    
    const[taskDescription, setTaskDescription] = useState('')
    const[taskStatus, setTaskStatus] = useState('')
    const[taskCategory, setTaskCategory] = useState('')
    const userId = authContext.userId

    useEffect(
        () => retrieveTaskById(),
        [taskId]
        )

    function retrieveTaskById(){
        if(taskId > 0) {
            retrieveTaskApi(taskId)
            .then(response => {
                setTaskDescription(response.data.taskDescription)
                setTaskStatus(response.data.taskStatus)
                setTaskCategory(response.data.taskCategory)
            })
            .catch(error => console.log(error))
        }
    }

    function onSubmit(taskInfo) {
        console.log(taskId)
        
        const task = {
            taskDescription: taskInfo.taskDescription,
            taskStatus: taskInfo.taskStatus,
            taskCategory: taskInfo.taskCategory,
            taskId: taskId
        }

        if(taskId == 0) {
            createUserTaskApi(userId, taskInfo)
            .then(response => {
               //navigate(`/users/${userId}`, response.data.userId)
                navigate(`/users/tasks`)
            })
            .catch(error => console.log(error))
    
        } else {
            updateUserTaskApi(userId, taskId, task)
            .then(response => {
                //navigate(`/users/${userId}`, response.data.userId)
                navigate(`/users/tasks/`)
            })
            .catch(error => console.log(error))
        }
    }

    function validate(values) {
        let errors = {
            // description: 'Enter a valid description',
            // targetDate: 'Enter a valid target date'
        }

        if(values.taskDescription.length < 1 || values.taskDescription.length > 200) {
            errors.taskDescription = 'Task Description Length should be between 1 and 200 characters'
        }

        console.log(values)
        return errors
    }

    return (
        <div className="container">
            <h1>Please Fill Task Details </h1>
            <div>
                <Formik initialValues={ { taskDescription, taskStatus, taskCategory} } 
                    enableReinitialize = {true}
                    onSubmit = {onSubmit}
                    validate = {validate}
                    validateOnChange = {false}
                    validateOnBlur = {false}
                >
                {
                    (props) => (
                        <Form>
                            <ErrorMessage 
                                name="taskDescription"
                                component="div"
                                className = "alert alert-warning"
                            />
                            
                            {/* <ErrorMessage 
                                name="password"
                                component="div"
                                className = "alert alert-warning"
                            /> */}

                            <fieldset className="form-group">
                                <label>Task Description</label>
                                <Field type="text" className="form-control" name="taskDescription" />
                            </fieldset>

                            <fieldset className="form-group">
                                <label>User Name</label>
                                <Field type="disabled" className="form-control" name="taskStatus" />
                            </fieldset>

                            <fieldset className="form-group">
                                <label>Email Id</label>
                                <Field type="disabled" className="form-control" name="taskCategory" />
                            </fieldset>

                            {/* <fieldset className="form-group">
                                <label>Password</label>
                                <Field type="password" className="form-control" name="password"/>
                            </fieldset> */}
                            <div>
                                <button className="btn btn-success m-5" type="submit">Submit Details</button>
                            </div>
                        </Form>
                    )
                }
                </Formik>
            </div>

        </div>
    )
}