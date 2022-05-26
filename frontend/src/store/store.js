import Vue from 'vue'
import Vuex from 'vuex'

import cryptocurrencies from './modules/cryptocurrencies'
import portfolio from './modules/portfolio'
import actions from './modules/actions'

Vue.use(Vuex)

export default new Vuex.Store({
    actions,
    modules: {
        cryptocurrencies,
        portfolio        
    }
})