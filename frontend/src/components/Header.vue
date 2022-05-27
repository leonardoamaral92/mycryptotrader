<template>
    <v-app-bar app>
        <v-toolbar-title class="headline text-uppercase mr-4">
            <span>Crypto</span>
            <span class="font-weight-light">Trader</span>
        </v-toolbar-title>
        <v-toolbar-items>
            <v-btn text to="/">Home</v-btn>
            <v-btn text to="/portfolio">Portfolio</v-btn>
            <v-btn text to="/cryptocurrencies">Cryptocurrencies</v-btn>
        </v-toolbar-items>

        <v-spacer></v-spacer>

        <v-toolbar-items>            
            <Form text="Buy" classAtr="green" :coinNames="coinsToBuy"  operation="buyCrypto"/>
            <Form text="Sell" classAtr="red"  :coinNames="coinsToSell" operation="sellCrypto" />
            <v-menu offset-y>
                <template v-slot:activator="{ on }">
                    <v-btn text v-on="on">CoinMarketCap</v-btn>
                </template>
                <v-list>                    
                    <v-list-item class="mst-item-button">
                        <v-list-item-title @click="loadData">Load Data</v-list-item-title>
                    </v-list-item>
                </v-list>
            </v-menu>
            <v-layout align-center>
                <span class="text-uppercase grey--text text--darken-2">
                    Funds: {{ funds | currency }}
                </span>
            </v-layout>
        </v-toolbar-items>
    </v-app-bar>
</template>

<script>
import { mapActions } from 'vuex'
import Form from './Form.vue'

export default {
    name: "HeaderApp",
    computed: {
        funds() {
            return this.$store.getters.funds;
        },
        coinsToBuy(){
            return this.$store.getters.cryptocurrencies.map(coin => coin.name)
        },
        coinsToSell(){
            return this.$store.getters.portfolio.map(coin => coin.name)
        }
    },
    methods: {
        ...mapActions(["loadPortfolio"]),        
        loadData() {
            this.loadPortfolio();
        }
    },
    components: { Form }
}
</script>

<style scoped>
.mst-item-button:hover{
    cursor: pointer;
    background-color: rgb(206, 206, 206);
}
</style>