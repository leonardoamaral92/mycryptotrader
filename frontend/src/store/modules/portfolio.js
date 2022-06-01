export default {
    state: {
        funds: 0,
        portfolioNames: [],
        portfolioResumeCoins: [],
        portfolioResumeStats: [],
        totalBalance: 0,
        valueAllOperations: 0,
        profitsCash: 0,
        profitsPercent: 0
    },
    mutations: {
        buyCrypto(state, order) {
            //TODO REFAZER
            console.log('coinToBuy:' + order)           
        },
        sellCrypto(state, order) {
            //TODO REFAZER
            console.log('coinToSell:' + order)            
        },
        setPortfolio(state, resume){
            console.log(resume)
            state.funds = resume.funds
            state.portfolioResumeCoins = resume.operationList
            console.log(state.portfolioResumeCoins)
            const resumeStats  = [
                { name: 'Balance', value: resume.totalBalance, isCurrency: true },
                { name: 'Operations', value: resume.valueAllOperations, isCurrency: true },
                { name: 'Profits $', value: resume.profitsCash, isCurrency: true },
                { name: 'Profits %', value: resume.profitsPercent, isCurrency: false }
            ]
            state.portfolioResumeStats = resumeStats
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
        portfolioResumeCoins(state){
            return state.portfolioResumeCoins
        },
        portfolioResumeStats(state){
            return state.portfolioResumeStats
        },
        portfolioNames(state){
            return state.portfolioNames
        },
        funds(state){
            return state.funds
        }
    }
}