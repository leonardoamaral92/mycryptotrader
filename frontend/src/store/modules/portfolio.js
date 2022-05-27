export default {
    state: {
        funds: 10000,
        portfolio: []
    },
    mutations: {
        buyCrypto(state, coin) {
            console.log('Portfolio: ' + state.portfolio)
            const record = state.portfolio.find(element => element.id == coin.id) || null
            const total = coin.quantity * coin.price
            if (record){
                record.quantity += coin.quantity
                record.totalSpent += total
            }
            else{                
                state.portfolio.push({
                    id: coin.id,            
                    name: coin.name,
                    symbol: coin.symbol,
                    quantity: coin.quantity,                    
                    totalSpent: total
                })
            }
            state.funds -= total
        },
        sellCrypto(state, { stockId, quantity, stockPrice }) {
            const record = state.cryptocurrencies.find(element => element.id == stockId)
            if (record.quantity > quantity)
                record.quantity -= quantity
            else
                state.cryptocurrencies.splice(state.cryptocurrencies.indexOf(record), 1)
            state.funds += stockPrice * quantity
        },
        setPortfolio(state, { funds, stockPortfolio }){
            state.funds = funds
            state.cryptocurrencies = stockPortfolio ? stockPortfolio : []
        }
    },
    actions: {
        sellCrypto({ commit }, order) {
            commit('sellCrypto', order)
        }        
    },
    getters: {        
        portfolio(state) {
            return state.portfolio
        },
        funds(state){
            return state.funds
        }
    }
}