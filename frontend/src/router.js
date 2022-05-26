import Vue from 'vue'
import Router from 'vue-router'

import Home from './components/Home'
import Portfolio from './components/portfolio/Portfolio'
import Cryptocurrencies from './components/cryptocurrencies/Cryptocurrencies'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        { path: '/', component: Home },
        { path: '/portfolio', component: Portfolio },
        { path: '/cryptocurrencies', component: Cryptocurrencies },
    ]
})