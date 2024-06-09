import { apiClient } from './ApiClient';

export const deleteTaskApi
    = (taskId) => apiClient.delete(`/users/tasks/${taskId}`);

export const retrieveAllUserTasksApi
    = (userId) => apiClient.get(`/users/${userId}/tasks`);

export const retrieveTaskApi
    = (taskId) => apiClient.get(`/users/tasks/${taskId}`);

export const retrieveAllTasksApi
    = () => apiClient.get(`/users/tasks`);

export const updateUserTaskApi
    = (userId, taskId, task) => apiClient.put(`/users/${userId}/tasks/${taskId}`, task);

export const createUserTaskApi
    = (userId, task) => apiClient.post(`/users/${userId}/tasks`, task);