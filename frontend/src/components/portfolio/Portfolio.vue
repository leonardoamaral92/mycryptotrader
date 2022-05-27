<template>
    <v-container>
        <v-row class="coinHeader">
            <v-col><strong>Coin</strong></v-col>
            <v-col><strong>Price</strong></v-col>
            <v-col><strong>Quantity</strong></v-col>
            <v-col><strong>Total Spent</strong></v-col>
            <v-col><strong>Balance </strong></v-col>
            <v-col><strong>Profit %</strong></v-col>
            <v-col><strong>Profit $</strong></v-col>            
        </v-row>
        <Cryptocurrency v-for="crypto in cryptocurrencies" :key="crypto.id" :cryptocurrency="crypto" />
    </v-container>

</template>

<script>
import { mapGetters } from 'vuex'
import Cryptocurrency from './Cryptocurrency.vue'

export default {
    name: 'PortfolioApp',
    components: { Cryptocurrency },
    computed: {
        ...mapGetters({
            cryptocurrencies: 'portfolio'
        })
    }
}
</script>

<style scoped>
.coinHeader{
    border-bottom: 1px solid #000;
}
</style>