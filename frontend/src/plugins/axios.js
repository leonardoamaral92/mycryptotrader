import Vue from 'vue'
import axios from 'axios'

Vue.use({
    install(Vue){
        // Vue.prototype.$http = axios.create({
        //     baseURL: 'https://stock-trader-4bfff-default-rtdb.firebaseio.com/'
        // })
        Vue.prototype.$http = axios.create({
            baseURL: 'http://localhost:8080/api/'
        })
    }
})