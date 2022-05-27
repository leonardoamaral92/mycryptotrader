export default {
    state: {
        cryptocurrencies: []
    },
    mutations: {
        setCryptocurrencies(state, cryptocurrencies){
            state.cryptocurrencies = JSON.parse(JSON.stringify(cryptocurrencies))
        }
    },
    actions: {
        buyCrypto({ commit }, order ){
            commit('buyCrypto', order)
        }               
    },
    getters: {
        cryptocurrencies(state){
            return state.cryptocurrencies
        },
        getPriceById: (state) => (cryptoId) => {
            return state.cryptocurrencies.find(coin => coin.id == cryptoId).quote.USD.price
        }
    }
}