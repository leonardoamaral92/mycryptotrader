import axios from "axios";
import { baseApiUrl } from "@/global";


export default {
    loadCoinList({ commit }) {   
        axios(`${baseApiUrl}/cmc/cryptocurrencies`).then(response => {
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