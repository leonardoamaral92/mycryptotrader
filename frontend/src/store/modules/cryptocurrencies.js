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
        buyStock({ commit }, order ){
            commit('buyCryptocurrencies', order)
        }               
    },
    getters: {
        cryptocurrencies(state){
            return state.cryptocurrencies
        }
    }
}