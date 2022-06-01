<template>
    <v-row class="coinLine">
        <v-col>{{ coin.coinName }} | <small>{{ coin.coinSymbol }}</small> </v-col>
        <v-col> {{ price | currency }} </v-col>
        <v-col>{{ coin.qtdTotalCoin }}</v-col>
        <v-col>{{ coin.totalValue | currency }}</v-col>
        <v-col>{{ coin.balance | currency }}</v-col>
        <v-col>
            {{ coin.profitsPercent }}%
            <v-icon :class="isProfit ? 'profit' : 'loss'">{{ isProfit ? 'mdi-arrow-up' : 'mdi-arrow-down' }}</v-icon>
        </v-col>
        <v-col>
            {{ coin.profitsCash | currency }}
            <v-icon :class="isProfit ? 'profit' : 'loss'">{{ isProfit ? 'mdi-arrow-up' : 'mdi-arrow-down' }}</v-icon>
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
        }
    }
}
</script>

<style scoped>
.coinLine {
    border-bottom: 1px solid #ddd;
}

.profit {
    color: green;
}

.loss {
    color: red;
}
</style>