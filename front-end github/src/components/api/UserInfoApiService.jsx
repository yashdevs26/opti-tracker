import { apiClient } from './ApiClient';

export const deleteUserApi
    = (userId) => apiClient.delete(`/users/${userId}`);

export const retrieveUserApi
    = (userId) => apiClient.get(`/users/${userId}`);

export const retrieveAllUserApi
    = () => apiClient.get(`/users/`);

export const updateUserApi
    = (userId, user) => apiClient.put(`/users/${userId}`, user);

export const createUserApi
    = (user) => apiClient.post(`/auth/signup`, user);