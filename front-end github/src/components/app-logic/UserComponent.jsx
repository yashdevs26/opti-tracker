import { useEffect, useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { createUserApi, retrieveUserApi, updateUserApi } from '../api/UserInfoApiService'
import { useAuth } from '../security/AuthContext'
import { Formik, Form, Field, ErrorMessage } from 'formik'

import '../css/OptiTracker.css'

export default function UserComponent() {

    const { userId } = useParams()
    //const userId = localStorage.getItem('userId')
    const authContext = useAuth()
    const navigate = useNavigate()

    const [fullName, setFullName] = useState('')
    const [userName, setUserName] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [showAlert, setShowAlert] = useState('');

    useEffect(
        () => retrieveUserById(),
        [userId]
    )

    function retrieveUserById() {
        if (userId > 0) {
            retrieveUserApi(userId)
                .then(response => {
                    setFullName(response.data.fullName)
                    setUserName(response.data.userName)
                    setEmail(response.data.email)
                    setPassword(response.data.password)
                })
                .catch(error => console.log(error))
        }
    }

    function onSubmit(userInfo) {
        console.log(userInfo)
        console.log(userId)

        const user = {
            fullName: userInfo.fullName,
            userName: userInfo.userName,
            email: userInfo.email,
            password: userInfo.password,
            userId: userId
        }

        if (userId == 0  ||  userId == undefined) {
            createUserApi(userInfo)
                .then(response => {
                    //navigate(`/users/${userId}`, response.data.userId)
                    //navigate(`/`)
                    if (response.status === 201) {
                        setShowAlert('SUCCESS')
                        if (authContext.isAuthenticated) {
                            navigate(`/users`)
                        }
                    }
                    else {
                        setShowAlert('FAIL')
                    }

                })
                .catch(error => {
                    console.log(error)
                    setShowAlert('FAIL')
                })

        } else {
            updateUserApi(userId, user)
                .then(response => {
                    //navigate(`/users/${userId}`, response.data.userId)
                    if (response.status === 200) {
                        setShowAlert('SUCCESS')
                        //navigate(`/users`)
                    }
                    else {
                        setShowAlert('FAIL')
                    }
                })
                .catch(error => {
                    console.log(error)
                    setShowAlert('FAIL')
                })
        }
    }

    function validate(values) {
        let errors = {
            // description: 'Enter a valid description',
            // targetDate: 'Enter a valid target date'
        }

        if (values.fullName.length < 1 || values.fullName.length > 200) {
            errors.fullName = 'Full Name Length should be between 1 and 200 characters'
        }

        if (values.userName.length < 5 || values.userName.length > 10) {
            errors.userName = 'User Name Length should be between 5 and 10'
        }

        console.log(values)
        return errors
    }

    return (<>
        {
            showAlert == "SUCCESS" &&
            <div className="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Success</strong> You should check in on some of those fields below.
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        }
        {
            showAlert == "FAIL" &&
            <div className="alert alert-danger alert-dismissible fade show" role="alert">
                <strong>Error Occured</strong> Please retry your action
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">  &times;</span>
                </button>
            </div>
        }
        <div className="container">
            <div>
                <Formik initialValues={{ fullName, userName, email, password }}
                    enableReinitialize={true}
                    onSubmit={onSubmit}
                    validate={validate}
                    validateOnChange={false}
                    validateOnBlur={false}
                >
                    {
                        (props) => (
                            <Form>
                                <ErrorMessage
                                    name="fullName"
                                    component="div"
                                    className="alert alert-warning"
                                />

                                <ErrorMessage
                                    name="userName"
                                    component="div"
                                    className="alert alert-warning"
                                />

                                <ErrorMessage
                                    name="email"
                                    component="div"
                                    className="alert alert-warning"
                                />

                                {!authContext.isAuthenticated  && <ErrorMessage
                                    name="password"
                                    component="div"
                                    className="alert alert-warning"
                                />}

                                <fieldset className="form-group">
                                    <label>Your Name</label>
                                    <Field type="text" className="form-control" name="fullName" />
                                </fieldset>

                                <fieldset className="form-group">
                                    <label>User Name</label>
                                    <Field type="text" className="form-control" name="userName" />
                                </fieldset>

                                <fieldset className="form-group">
                                    <label>Email Id</label>
                                    <Field type="email" className="form-control" name="email" />
                                </fieldset>

                                {!authContext.isAuthenticated && <fieldset className="form-group">
                                    <label>Password</label>
                                    <Field type="password" className="form-control" name="password" />
                                </fieldset>}
                                <div>
                                    <button className="btn btn-success m-5" type="submit">Submit Details</button>
                                </div>
                            </Form>
                        )
                    }
                </Formik>
            </div>

        </div>
    </>
    )
}