import Vue from 'vue'

/*Este arquivo foi criado pois essa funcionalidade aciona tanto a mutations das stocks, quanto do portfolio, 
então não pertence a nenhum dos dois, sendo melhor isolar. */

export default {
    loadPortfolio({ commit }) {
        // Vue.prototype.$http('data.json').then(response => {
        //     const data = response.data
        //     if (data) {
        //         commit('setStocks', data.stocks)
        //         commit('setPortfolio', {
        //             funds: data.funds,
        //             stockPortfolio: data.stockPortfolio
        //         })
        //     }
        // })

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

    }
}