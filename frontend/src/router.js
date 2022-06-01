import Vue from 'vue'
import Router from 'vue-router'

import Home from './components/Home'
import PortfolioResume from './components/portfolio/PortfolioResume'
import Portfolios from './components/portfolio/Portfolios'
import Cryptocurrencies from './components/cryptocurrencies/Cryptocurrencies'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        { path: '/', component: Home },
        { path: '/portfolios', component: Portfolios },
        { path: '/portfolios/resume', component: PortfolioResume },
        { path: '/cryptocurrencies', component: Cryptocurrencies },
    ]
})