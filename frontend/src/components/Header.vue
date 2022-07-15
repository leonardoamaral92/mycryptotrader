<template>
    <v-app-bar app>
        <v-toolbar-title class="headline text-uppercase mr-4">
            <router-link to="/" class="btnHome">
                <span>Crypto</span>
                <span class="font-weight-light">Trader</span>
            </router-link>
        </v-toolbar-title>
        <v-toolbar-items>            
            <v-btn text to="/portfolios/resume">Resume</v-btn>
            <v-btn text to="/cryptocurrencies">Coins</v-btn>
        </v-toolbar-items>

        <v-spacer></v-spacer>

        <v-toolbar-items>            
            <v-btn text to="/portfolios">Portfolios</v-btn>
            <DialogBuy /> 
            <DialogSell />
            <v-menu offset-y>
                <template v-slot:activator="{ on }">
                    <v-btn text v-on="on">Config</v-btn>
                </template>
                <v-list>            
                    <v-list-item class="mst-item-button">                        
                        <v-dialog v-model="showDialog" persistent max-width="300px">
                            <template v-slot:activator="{ on, attrs }">
                                <v-list-item-title v-bind="attrs" v-on="on">Add Funds</v-list-item-title>
                            </template>
                            <v-card>
                                <v-card-title>
                                    <span class="text-h5">Add Funds</span>
                                </v-card-title>
                                <v-card-text>
                                    <v-text-field label="Quantity" v-model="qtdFunds" />
                                </v-card-text>
                                <v-card-actions>
                                    <v-spacer></v-spacer>
                                    <v-btn color="blue darken-1" text @click="closeFundsModal">
                                        Close
                                    </v-btn>
                                    <v-btn color="primary" text @click="addFunds">
                                        Add
                                    </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-dialog>                        
                    </v-list-item>                    
                    <v-list-item class="mst-item-button">
                        <v-list-item-title @click="loadData">Load Data from CMC</v-list-item-title>
                    </v-list-item>
                    <v-list-item class="mst-item-button">
                        <v-list-item-title @click.prevent="logout">Logout</v-list-item-title>
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
import DialogBuy from './DialogBuy.vue'
import DialogSell from './DialogSell.vue'
import { userKey } from '@/global';

export default {
    name: "HeaderApp",
    components: { DialogBuy, DialogSell },
    data(){
        return {
            qtdFunds: 0,
            showDialog: false
        }
    },
    computed: {
        funds() {
            return this.$store.getters.funds;
        }
    },
    methods: {
        ...mapActions(["loadCoinList"]),
        loadData() {
            this.loadCoinList();
        },
        logout(){
            localStorage.removeItem(userKey)
            this.$store.commit('setUser', null)
            this.$router.push({name: 'home'})
            this.$toasted.global.defaultSuccess()
        },
        closeFundsModal(){
            this.showDialog = false
        },
        addFunds(){
            const request = {
                depositValue: this.qtdFunds,
                investorId: this.$store.state.user.investorId
            }            
            this.$store.dispatch('addFunds', request)
            this.qtdFunds = 0
            this.closeFundsModal()
        }
    }    
}
</script>

<style scoped>
.mst-item-button:hover{
    cursor: pointer;
    background-color: rgb(206, 206, 206);
}
.btnHome{
    text-decoration: none;
}
</style>