<template>
    <v-flex class="pr-3 pb-3 mt-2" xs12 md6 lg4>
        <v-card class="green darken-3 white--text">
            <v-card-title class="headline">
                <strong>{{cryptocurrency.name}} <small>(Preço: {{cryptocurrency.quote.USD.price | currency}})</small></strong>
            </v-card-title>
        </v-card>
        <v-card>
            <v-container fill-height>
                <v-text-field label="Quantidade" type="number"
                    :error="insufficientFunds || !Number.isInteger(quantity)"
                    v-model.number="quantity"/>
                <v-btn class="ml-2 green darken-3 white--text" 
                    :disabled="insufficientFunds || quantity <= 0 || !Number.isInteger(quantity)"
                    @click="buycryptocurrency">{{ insufficientFunds ? 'Insuficiente' : 'Comprar'  }}</v-btn>
            </v-container>
        </v-card>
    </v-flex>
</template>

<script>
export default {
    name: 'CryptocurrencyApp',
    props: ['cryptocurrency'],
    data(){
        return {
            quantity: 0
        }
    },
    computed:{
        funds(){
            return this.$store.getters.funds
        },
        insufficientFunds(){
            return this.quantity * this.cryptocurrency.price > this.funds
        }
    },
    methods: {
        buycryptocurrency(){
            const order = {
                cryptocurrencyId: this.cryptocurrency.id,
                cryptocurrencyPrice: this.cryptocurrency.quote.USD.price,
                quantity: this.quantity
            }

            this.$store.dispatch('buyCryptocurrency', order)
            this.quantity = 0
        }
    }
}
</script>

<style scoped>

</style>