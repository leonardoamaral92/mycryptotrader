<template>
    <v-row class="lineCoin">
        <v-col>{{ cryptocurrency.cmc_rank }} </v-col>
        <v-col>{{ cryptocurrency.name }} | <small>{{ cryptocurrency.symbol }}</small> </v-col>        
        <v-col> {{cryptocurrency.quote.USD.price | currency }} </v-col>
        <v-col> {{ cryptocurrency.quote.USD.market_cap | currency }} </v-col>
    </v-row>
</template>

<script>

export default {
    name: 'CryptocurrencyApp',
    props: ['cryptocurrency'],    
    data(){
        return {
            quantity: 0
        }
    },
    computed:{
        funds(){
            return this.$store.getters.funds
        }
    }
}
</script>

<style scoped>
.lineCoin{
    border-bottom: 1px solid rgb(209, 209, 209);
}
</style>