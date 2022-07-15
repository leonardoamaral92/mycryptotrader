<template>
    <v-row class="coinLine grey lighten-4" v-if="coin.qtdTotalCoin > 0">
        <v-col>{{ coin.coinName }} | <small>{{ coin.coinSymbol }}</small> </v-col>
        <v-col> {{ coin.currentPrice | currency }} </v-col>
        <v-col class="orange--text"> {{ coin.averagePrice | currency }} </v-col>
        <v-col>{{ coin.qtdTotalCoin.toFixed(4) }}</v-col>
        <v-col>{{ coin.totalValue | currency }}</v-col>
        <v-col>{{ coin.balance | currency }}</v-col>
        <v-col :class="colorByProfit">
            {{ coin.profitsPercent.toFixed(2) }}%
            <v-icon :class="colorByProfit">{{ isProfit ? 'mdi-arrow-up' : 'mdi-arrow-down'  }}</v-icon>
        </v-col>
        <v-col :class="colorByProfit">
            {{ coin.profitsCash | currency }}
            <v-icon :class="colorByProfit">{{ isProfit ? 'mdi-arrow-up' : 'mdi-arrow-down' }}</v-icon>
        </v-col>
    </v-row>
</template>

<script>
export default {
    name: 'cryptocurrencyApp',
    props: ['coin'],
    created() {
    },
    data() {
        return {

        }
    },
    computed: {
        price() {
            return 0; //this.$store.getters.getPriceById(this.cryptocurrency.id)
        },
        isProfit() {
            return this.coin.profitsCash > 0
        },
        colorByProfit(){
            return this.isProfit ? 'green--text' : 'red--text'
        }
    }
}
</script>

<style scoped>
.coinLine {
    border-bottom: 1px solid #ddd;
}
</style>