<template>
    <v-container>
        <v-row class="d-flex justify-center mt-2">
            <v-col lg="6" class="d-flex justify-space-between lighten-2 rounded-lg pt-5 pb-5 text-h4">
                <h4>Portfolios</h4>
                <v-btn color="primary" @click="dialog = true">Add Portfolio</v-btn>
            </v-col>
        </v-row>
        <PortfolioCard v-for="portfolio in portfolios" :key="portfolio.id" :portfolio="portfolio" />

        <!--Dialog Add Portfolio -->
        <v-dialog v-model="dialog" persistent max-width="600px">
            <v-card>
                <v-card-title>
                    <span class="text-h5">Add portfolio</span>
                </v-card-title>
                <v-card-text>
                    <v-text-field label="Name" v-model="newName" />
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="closeModal">
                        Close
                    </v-btn>
                    <v-btn color="primary" text @click="addPortfolio">
                        Add portfolio
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-container>
</template>

<script>
import PortfolioCard from './PortfolioCard.vue';

export default {
    name: 'PortfoliosApp',
    components: {
        PortfolioCard
    },
    created() {
        if(this.$store.state.user)
            this.$store.dispatch('loadPortfolios', this.$store.state.user.investorId)
    },
    data() {
        return {
            dialog: false,
            newName: ''
        }
    },
    computed: {
        portfolios() {
            return this.$store.getters.portfolios;
        }
    },
    methods: {
        closeModal() {
            this.dialog = false
        },
        addPortfolio() {
            const request = { 
                investorId: this.$store.state.user.investorId, 
                name: this.newName 
            };
            this.$store.dispatch('addPortfolio', request).then;
            this.dialog = false;
        }
    }
}
</script>

<style scoped>
</style>
