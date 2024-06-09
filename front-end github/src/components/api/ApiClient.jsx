import axios from 'axios'

export const apiClient = axios.create(
    {
        baseURL: 'http://localhost:5000',
        //baseURL: 'http://yashdevs.ap-south-1.elasticbeanstalk.com/',
        /* headers: {
            post: {
              "Access-Control-Allow-Origin": "http://yashdevs.ap-south-1.elasticbeanstalk.com/"
            }
          } */
    }
);