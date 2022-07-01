import Vue from 'vue'
import Router from 'vue-router'

import Home from './components/Home'
import PortfolioResume from './components/portfolio/PortfolioResume'
import Portfolios from './components/portfolio/Portfolios'
import Cryptocurrencies from './components/cryptocurrencies/Cryptocurrencies'
import Auth from './components/auth/AuthPage'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        { name:'home',    path: '/', component: Home },
        { name:'auth',    path: '/login', component: Auth },
        { name: 'portfolios', path: '/portfolios', component: Portfolios },
        { name: 'resume',     path: '/portfolios/resume', component: PortfolioResume },
        { name:'coinlist',    path: '/cryptocurrencies', component: Cryptocurrencies },
    ]
})