<template>
    <v-row align-content="end">
        <v-col>{{ cryptocurrency.cmc_rank }} </v-col>
        <v-col>{{ cryptocurrency.name }} | <small>{{ cryptocurrency.symbol }}</small> </v-col>        
        <v-col> {{cryptocurrency.quote.USD.price | currency}} </v-col>
        <v-col> {{ cryptocurrency.quote.USD.market_cap | currency}} </v-col>
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
        },
        insufficientFunds(){
            return this.quantity * this.cryptocurrency.price > this.funds
        }
    },
    methods: {
        buyCryptocurrency(){
            const order = {
                cryptocurrencyId: this.cryptocurrency.id,
                cryptocurrencyPrice: this.cryptocurrency.quote.USD.price,
                quantity: this.quantity
            }

            this.$store.dispatch('buyCryptocurrency', order)
            this.quantity = 0
        }
    }
}
</script>

<style scoped>

</style>