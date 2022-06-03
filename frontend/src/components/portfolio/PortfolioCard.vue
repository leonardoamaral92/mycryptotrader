<template>
    <v-row class="d-flex justify-center mt-6">
        <v-col class="d-flex justify-space-between rounded-lg grey lighten-2" lg="6">
            <h3 class="ml-3">{{ portfolio.name }}</h3>
            <div>
                <v-btn class="mr-2" @click="editPortfolioName">
                    <v-icon>mdi-pencil</v-icon>
                </v-btn>
                <v-btn @click="deletePortfolio">
                    <v-icon>mdi-delete</v-icon>
                </v-btn>
            </div>
        </v-col>
    </v-row>
</template>

<script>
export default {
    props: ['portfolio'],
    data() {
        return {
            newName: ''
        }
    },
    methods: {
        deletePortfolio() {
            this.$store.dispatch('deletePortfolio', this.portfolio)
        },
        editPortfolioName() {
            const newPortfolio = {
                id: this.portfolio.id,
                name: this.newName
            }
            this.$store.dispatch('editPortfolioName', newPortfolio)
        }
    }
}
</script>