import Vue from 'vue'
import Vuex from 'vuex'

import cryptocurrencies from './modules/cryptocurrencies'
import portfolio from './modules/portfolio'
import actions from './modules/actions'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        cryptocurrencies,
        portfolio        
    },
    state: {        
        user: null
    },
    mutations: {
        setUser(state, user){
            if(user) {
                state.user = { username: '', investorId: 0, funds: 0}
                state.user.username = user.username
                state.user.investorId = user.investorId                                                
                axios.defaults.headers.common['Authorization'] = `Bearer ${user.accessToken}`
            }
            else
                delete axios.defaults.headers.common['Authorization']
        },
        setFunds(state, newFunds){            
            state.user.funds = newFunds
        }
    },
    actions,
    getters: {
        funds(state){
            return state.user.funds
        }
    }    
})