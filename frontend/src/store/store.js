import Vue from 'vue'
import Vuex from 'vuex'

import cryptocurrencies from './modules/cryptocurrencies'
import portfolio from './modules/portfolio'
import actions from './modules/actions'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {        
        user: null
    },
    mutations: {
        setUser(state, user){
            state.user = user
            if(user) {
                axios.defaults.headers.common['Authorization'] = `Bearer ${user.accessToken}`
            }
            else
                delete axios.defaults.headers.common['Authorization']
        }
    },
    actions,
    modules: {
        cryptocurrencies,
        portfolio        
    }
})