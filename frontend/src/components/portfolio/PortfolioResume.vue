<template>
    <v-container>
        <v-row>
            <StatsCardVue v-for="stat in resumeStats" :key="stat.name"  :stat="stat" />
        </v-row>
        <v-row class="coinHeader mt-10">
            <v-col><strong>Coin</strong></v-col>
            <v-col><strong>Price</strong></v-col>
            <v-col><strong>Average Price</strong></v-col>
            <v-col><strong>Quantity</strong></v-col>
            <v-col><strong>Total Spent</strong></v-col>
            <v-col><strong>Balance </strong></v-col>
            <v-col><strong>Profit %</strong></v-col>
            <v-col><strong>Profit $</strong></v-col>
        </v-row>
        <Cryptocurrency v-for="coin in resumeCoins" :key="coin.coinId" :coin="coin" />
    </v-container>

</template>

<script>
import { mapGetters } from 'vuex'
import Cryptocurrency from './Cryptocurrency.vue'
import StatsCardVue from './StatsCard.vue'

export default {
    name: 'PortfolioResume',
    components: { Cryptocurrency, StatsCardVue },
    computed: {
        ...mapGetters({
            resumeCoins: 'portfolioResumeCoins',
            resumeStats: 'portfolioResumeStats'
        })
    },
    created() {
        this.$store.dispatch('loadCoinList')
		this.$store.dispatch('loadPortfolios')
    }
}
</script>

<style scoped>
.coinHeader{
    border-bottom: 1px solid #000;    
    background-color: #ddd;
}
</style>