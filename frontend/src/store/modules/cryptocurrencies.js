import { baseApiUrl } from "@/global";
import axios from "axios";

export default {
    state: {
        cryptocurrencies: []
    },
    mutations: {
        setCryptocurrencies(state, cryptocurrencies) {
            state.cryptocurrencies = JSON.parse(JSON.stringify(cryptocurrencies))
        }
    },
    actions: {
        // eslint-disable-next-line no-unused-vars
        buyCrypto({ commit }, order) {
            console.log("Entrei na action buy...")
            return new Promise((resolve, reject) => {
                axios.post(`${baseApiUrl}/api/operations/buy`, order)
                    .then(response => {
                        const apiResponse = response.data;
                        if (apiResponse.status === "SUCCESS") {                            
                            const responseModal = {
                                status: apiResponse.status,
                                message: 'Purchase made successfully.'
                            }
                            resolve(responseModal)
                        }
                        else
                            alert('Não foi possível realizar a compra.')
                    }, error => {
                        console.log(error)
                        reject(error);
                    });
            });
        },
        // eslint-disable-next-line no-unused-vars
        sellCrypto({commit}, order){
            console.log("Entrei na action sell...")
            return new Promise((resolve, reject) => {
                axios.post(`${baseApiUrl}/api/operations/sell`, order)
                    .then(response => {
                        const apiResponse = response.data;
                        if (apiResponse.status === "SUCCESS") {                            
                            const responseModal = {
                                status: apiResponse.status,
                                message: 'Sell made successfully.'
                            }
                            resolve(responseModal)
                        }
                        else
                            alert('Não foi possível realizar a venda.')
                    }, error => {
                        console.log(error)
                        reject(error);
                    });
            });
        }
    },
    getters: {
        cryptocurrencies(state) {
            return state.cryptocurrencies
        },
        getPriceById: (state) => (cryptoId) => {
            return state.cryptocurrencies.find(coin => coin.id == cryptoId).quote.USD.price
        }
    }
}