import Vue from 'vue'


export default {
    loadCoinList({ commit }) {   
        Vue.prototype.$http('/cryptocurrencies').then(response => {
            const cmcResponse = response.data;
            if (cmcResponse ){
                if(!cmcResponse.status.error_code){
                    commit('setCryptocurrencies', cmcResponse.data)
                }
                else
                    alert(cmcResponse.status.error_message)
            }
        });

        Vue.prototype.$http('/portfolios/1/resume').then(response => {
            const apiResponse = response.data;
            if(apiResponse.status === "SUCCESS"){
                commit('setPortfolioResume', apiResponse.data)
            }
            else
                alert(apiResponse.message)            
        });

    }
}