export default {
    state: {
        funds: 10000,
        stocks: []
    },
    mutations: {
        buyStock(state, { stockId, quantity, stockPrice }) {
            const record = state.stocks.find(element => element.id == stockId)
            if (record)
                record.quantity += quantity
            else
                state.stocks.push({
                    id: stockId,
                    quantity: quantity
                })
            state.funds -= stockPrice * quantity
        },
        sellStock(state, { stockId, quantity, stockPrice }) {
            const record = state.stocks.find(element => element.id == stockId)
            if (record.quantity > quantity)
                record.quantity -= quantity
            else
                state.stocks.splice(state.stocks.indexOf(record), 1)
            state.funds += stockPrice * quantity
        },
        setPortfolio(state, { funds, stockPortfolio }){
            state.funds = funds
            state.stocks = stockPortfolio ? stockPortfolio : []
        }
    },
    actions: {
        sellStock({ commit }, order) {
            commit('sellStock', order)
        }        
    },
    getters: {
        //A lista de ações do portfólio será a mesma da lista de ações para comprar, acrescido da quantidade        
        stockPortfolio(state, getters) {
            return state.stocks.map(stock => {
                //neste estado só temos id e quantidade, vamos procurar o resto das informações dentro do estado de stocks
                const record = getters.stocks.find(element => element.id == stock.id)
                return {
                    id: stock.id,
                    quantity: stock.quantity,
                    name: record.name,
                    price: record.price
                }
            })
        },
        funds(state){
            return state.funds
        }
    }
}