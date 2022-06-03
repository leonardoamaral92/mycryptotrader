<template>
    <v-container>
        <v-row class="d-flex justify-center mt-2">
            <v-col lg="6" class="d-flex justify-space-between lighten-2 rounded-lg pt-5 pb-5 text-h4">
                <h4>Portfolios</h4>                
                <DialogAddUpdate headerText="Add Portfolio" btnText="Add Portfolio" actionName="addPortfolio" />
            </v-col>
        </v-row>
        <PortfolioCard v-for="portfolio in portfolios" :key="portfolio.id" :portfolio="portfolio" />
    </v-container>
</template>

<script>
import PortfolioCard from './PortfolioCard.vue';
import DialogAddUpdate from './DialogAddUpdate.vue';

export default {
    name: 'PortfoliosApp',
    components: {
        PortfolioCard, DialogAddUpdate
    },
    created() {
        this.$store.dispatch('loadPortfolios')
    },
    computed: {
        portfolios() {
            return this.$store.getters.portfolios;
        }
    },
    methods: {
        deleteButton(){

        }
    }
}
</script>

<style scoped>
</style>
