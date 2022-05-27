<template>
    <v-row class="coinLine">
        <v-col>{{ cryptocurrency.name }} | <small>{{ cryptocurrency.symbol }}</small> </v-col>
        <v-col> {{ price | currency }} </v-col>
        <v-col>{{ cryptocurrency.quantity }}</v-col>
        <v-col>{{ cryptocurrency.totalSpent | currency }}</v-col>
        <v-col>{{ balance | currency }}</v-col>
        <v-col>
            {{ profitPercent }}
            <v-icon :class="isProfit ? 'profit' : 'loss'">{{ isProfit ? 'mdi-arrow-up' : 'mdi-arrow-down' }}</v-icon>
        </v-col>
        <v-col>
            {{ profitCash | currency }}
            <v-icon :class="isProfit ? 'profit' : 'loss'">{{ isProfit ? 'mdi-arrow-up' : 'mdi-arrow-down' }}</v-icon>
        </v-col>
    </v-row>
</template>

<script>
export default {
    name: 'cryptocurrencyApp',
    props: ['cryptocurrency'],
    created() {
    },
    data() {
        return {

        }
    },
    computed: {
        price() {
            return this.$store.getters.getPriceById(this.cryptocurrency.id)
        },
        balance() {
            return this.price * this.cryptocurrency.quantity
        },
        profitPercent() {
            return `${((this.profitCash / this.cryptocurrency.totalSpent) * 100).toFixed(2)}%`
        },
        profitCash() {
            return this.balance - this.cryptocurrency.totalSpent
        },
        isProfit() {
            return this.profitCash > 0
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