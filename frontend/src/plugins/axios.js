import axios from 'axios'
import { baseApiUrl, userKey } from '@/global'

const success = res => res
const error = async err => {
    if (401 === err.response.status){
        const response = await refreshToken(err);
        return response;               
        
    } else {
        return Promise.reject(err)
    }
}

async function refreshToken(error){
    return new Promise((resolve, reject) => {
        try{
            const json = localStorage.getItem(userKey)
            const userData = JSON.parse(json)            
    
            if(!userData){            
                //O return é apenas porque quero sair do método                
                return window.location = '/auth'
            }    
            
            delete axios.defaults.headers.common['Authorization']
            axios.post(`${baseApiUrl}/auth/refresh/${userData.username}`, userData.refreshToken)
            .then( async (res) => {       
                console.log("REFRESH TOKEN")
                console.log(res.data)
                axios.defaults.headers.common['Authorization'] = `Bearer ${res.data.accessToken}`
                localStorage.setItem(userKey, JSON.stringify(res.data))
                return resolve(res)
            })
            .catch(() => {                
                localStorage.removeItem(userKey)
                window.location = '/auth'
                return reject(error);
            }) 
        }
        catch(err){
            return reject(err);
        }
    })
}

axios.interceptors.response.use(success, error)
