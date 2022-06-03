import Vue from 'vue'

export default {
    state: {
        funds: 0,
        portfolios: [],
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
        setPortfolioResume(state, resume){
            state.funds = resume.funds
            state.portfolioResumeCoins = resume.operationList            
            const resumeStats  = [
                { name: 'Balance', value: resume.totalBalance, isCurrency: true },
                { name: 'Operations', value: resume.valueAllOperations, isCurrency: true },
                { name: 'Profits $', value: resume.profitsCash, isCurrency: true },
                { name: 'Profits %', value: resume.profitsPercent, isCurrency: false }
            ]
            state.portfolioResumeStats = resumeStats
        },
        addPortfolio(state, portfolio){
            console.log(`Criando portfolio -> ${portfolio.id} / ${portfolio.name}`)
            state.portfolios.push(portfolio);
        },
        setPortfolio(state, portfolios){
            console.log('Carregando portfolios')
            state.portfolios = portfolios;
        },
        deletePortfolio(state, portfolio){            
            console.log('deletando portfolio' + portfolio.id + '/' + portfolio.name);            
            const index = state.portfolios.indexOf(portfolio);
            state.portfolios.splice(index)
        },
        editPortfolioName(state, portfolio){  
            console.log('editando portfolio' + portfolio.id + '/' + portfolio.name);
        }  
    },
    actions: {
        loadPortfolios({ commit }){
            Vue.prototype.$http('/portfolios/1').then(response => {
                const apiResponse = response.data;
                if(apiResponse.status === "SUCCESS"){                    
                    commit('setPortfolio', apiResponse.data)
                }
                else
                    alert(apiResponse.message)            
            });
        },
        sellCrypto({ commit }, order) {
            commit('sellCrypto', order)
        },
        addPortfolio({ commit }, portfolioName){
            Vue.prototype.$http
            .post("/portfolios", {
                userId: 1,
                name: portfolioName
            })
            .then(response => {
                const apiResponse = response.data;
                if(apiResponse.status === "SUCCESS"){  
                    const portfolio = apiResponse.data;
                    commit('addPortfolio', portfolio)
                }
                else
                    alert('Não foi possível deletar o portfólio.')
            });            
        },
        deletePortfolio({ commit }, portfolio){
            Vue.prototype.$http
            .delete(`/portfolios/${portfolio.id}`)
            .then(response => {
                if(response.status === 204)
                    commit('deletePortfolio', portfolio)
                else
                    alert('Não foi possível deletar o portfólio.')
            });
        },
        editPortfolioName({ commit }, portfolio){              
            commit('editPortfolioName', portfolio)
        }    
    },
    getters: {        
        portfolios(state) {
            return state.portfolios
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