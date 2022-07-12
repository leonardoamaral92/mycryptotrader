import axios from "axios"
import { baseApiUrl, showError } from "@/global"

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
            const portIndex = state.portfolios.findIndex(p => p.id == portfolio.id)
            state.portfolios[portIndex].name = portfolio.name
        }  
    },
    actions: {
        loadPortfolioResume({ commit }, investorId){
            axios(`${baseApiUrl}/api/portfolios/${investorId}/resume`).then(response => {
                const apiResponse = response.data;
                //TODO MUDAR TRATAMENTO PARA O CASO DE 401, REFAZER REQUISIÇÃO
                if(apiResponse.status === "SUCCESS"){
                    commit('setPortfolioResume', apiResponse.data)
                }
                else
                    showError(apiResponse)
            });
        },
        loadPortfolios({ commit }, investorId){
            axios(`${baseApiUrl}/api/portfolios/${investorId}`).then(response => {                
                const apiResponse = response.data;
                //TODO MUDAR TRATAMENTO PARA O CASO DE 401, REFAZER REQUISIÇÃO
                console.log(response);
                if(apiResponse.status === "SUCCESS"){                    
                    commit('setPortfolio', apiResponse.data)
                }
                else
                    showError(apiResponse)
            });
        },
        sellCrypto({ commit }, order) {
            commit('sellCrypto', order)
        },
        addPortfolio({ commit }, portfolioName){
            axios.post(`${baseApiUrl}/api/portfolios`, {
                userId: 1,
                name: portfolioName
            })
            .then(response => {
                const apiResponse = response.data;
                //TODO MUDAR TRATAMENTO PARA O CASO DE 401, REFAZER REQUISIÇÃO
                if(apiResponse.status === "SUCCESS"){  
                    const portfolio = apiResponse.data;
                    commit('addPortfolio', portfolio)
                }
                else
                    alert('Não foi possível criar o portfólio.')
            });            
        },
        deletePortfolio({ commit }, portfolio){
            axios.delete(`${baseApiUrl}/api/portfolios/${portfolio.id}`)
            .then(response => {
                //TODO MUDAR TRATAMENTO PARA O CASO DE 401, REFAZER REQUISIÇÃO
                if(response.status === 204)
                    commit('deletePortfolio', portfolio)
                else
                    alert('Não foi possível deletar o portfólio.')
            });
        },
        editPortfolioName({ commit }, portfolio){
            axios.put(`${baseApiUrl}/api/portfolios`, {
                id: portfolio.id,
                name: portfolio.name
            })
            .then(response => {
                const apiResponse = response.data;
                //TODO MUDAR TRATAMENTO PARA O CASO DE 401, REFAZER REQUISIÇÃO
                if(apiResponse.status === "SUCCESS")
                    commit('editPortfolioName', apiResponse.data)
                else
                    showError(apiResponse)
            });
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