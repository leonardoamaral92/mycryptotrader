<template>
    <v-container>
        <v-row class="coinHeader">
            <v-col><strong>CMCRanking</strong></v-col>
            <v-col><strong>Coin</strong></v-col>            
            <v-col><strong>Price</strong></v-col>
            <v-col><strong>MarketCap</strong></v-col>
        </v-row>        
        <Cryptocurrency v-for="cryptocurrency in cryptocurrencies" :key="cryptocurrency.id" :cryptocurrency="cryptocurrency" />        
    </v-container>
</template>

<script>
import Cryptocurrency from './Cryptocurrency.vue'

export default {
    name: 'CryptocurrenciesApp',
    components: { Cryptocurrency },
    computed: {
        cryptocurrencies(){
            return this.$store.getters.cryptocurrencies
        }
    }
}
</script>

<style scoped>
.coinHeader{
    border-bottom: 1px solid #000;
}
</style>