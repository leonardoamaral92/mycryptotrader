<template>
    <v-flex class="pr-3 pb-3 mt-2" xs12 md6 lg4>
        <v-card class="blue darken-3 white--text">
            <v-card-title class="headline">
                <strong>{{stock.name}} 
                    <small>(Preço: {{ 10 | currency }}| Qtde: {{ stock.quantity }})</small>
                </strong>
            </v-card-title>
        </v-card>
        <v-card>
            <v-container fill-height>
                <v-text-field label="Quantidade" type="number"
                    :error="insufficientQuantity || !Number.isInteger(quantity)"
                    v-model.number="quantity"/>
                <v-btn class="ml-2 blue darken-3 white--text" 
                    :disabled="insufficientQuantity || quantity <= 0 || !Number.isInteger(quantity)"
                    @click="sellStock">{{ insufficientQuantity ? 'Insuficiente' : 'Vender' }}</v-btn>
            </v-container>
        </v-card>
    </v-flex>
</template>

<script>
export default {
    name: 'StockApp',
    props: ['stock'],    
    created() {
        console.log(this.stock)
    },
    data() {
        return {
            quantity: 0
        }
    },
    computed: {
        stocks(){
            return this.$store.getters.stocks
        },
        insufficientQuantity(){
            return this.quantity > this.stock.quantity
        }
    },
    methods: {
        sellStock(){
            const order = {
                stockId: this.stock.id,
                stockPrice: this.stock.price,
                quantity: this.quantity
            }

            this.$store.dispatch('sellStock', order)
            this.quantity = 0
        }
    }
}
</script>

<style scoped>

</style>