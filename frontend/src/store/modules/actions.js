import axios from "axios";
import { baseApiUrl } from "@/global";


export default {
    loadCoinList({ commit }) {   
        axios(`${baseApiUrl}/cmc/cryptocurrencies`)
        .then(response => {
            const cmcResponse = response.data;
            if (cmcResponse ){
                if(!cmcResponse.status.error_code){
                    commit('setCryptocurrencies', cmcResponse.data)
                }
                else
                    alert(cmcResponse.status.error_message)
            }
        }); 
    },
    authenticate({commit}, user){
        commit('setUser', user)
        commit('setFunds', 0);
    },
    addFunds({ commit }, request){
        axios.post(`${baseApiUrl}/api/investor/deposit`, request)
        .then(response => {            
            if(response.data.status === "SUCCESS"){
                const apiResponse = response.data
                alert(apiResponse.data.newFunds)
                commit('setFunds', apiResponse.data.newFunds)
            }
        })
    }
}