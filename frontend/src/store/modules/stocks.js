import stocks from '@/data/stocks'

export default {
    state: {
        stocks: []
    },
    mutations: {
        setStocks(state, stocks){
            const listStocks = JSON.parse(JSON.stringify(stocks))
            console.log(listStocks)
            state.stocks = listStocks.data
        }        
    },
    actions: {
        buyStock({ commit }, order ){
            commit('buyStock', order)
        },
        initStocks({ commit }){
            commit('setStocks', stocks)
        }       
    },
    getters: {
        stocks(state){
            return state.stocks
        }
    }
}