import axios from 'axios'

const http = axios.create({
    baseURL: '/api',
    timeout: 5000
})

http.interceptors.request.use(
    config => {
        return config;
    },
    error => {
        Promise.reject(error)
    }
)

http.interceptors.response.use(
    response =>{
        return Promise.resolve(response);
    },
    error => {
        return Promise.reject(error)
    }
)

export default http;
