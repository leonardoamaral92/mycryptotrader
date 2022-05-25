<template>
    <v-layout>
        <Stock v-for="stock in stocks" :key="stock.id" :stock="stock" />
    </v-layout>
</template>

<script>
import { mapGetters } from 'vuex'
import Stock from './Stock.vue'

export default {
    name: 'PortfolioApp',
    components: { Stock },
    computed: {
        ...mapGetters({
            stocks: 'stockPortfolio'
        })
    }
}
</script>

<style scoped>
</style>