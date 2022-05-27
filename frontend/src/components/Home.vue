<template>
    <div>
        <h1 class="display-2 font-weight-light mb-4">Portfolio de Criptomoedas</h1>
        <p class="display-1"><strong>Saldo Atual:</strong> {{ funds | currency }} </p>
        <v-sheet :elevation="6" class="pa-2 primary">
            <span class="headline white--text font-weight-light">
                Você pode realizar operações de compra ou venda de criptomoedas</span>
        </v-sheet>
        <v-sheet :elevation="6" class="mt-3 pa-2 primary">
            <span class="headline white--text font-weight-light">
                Adicione saldo a hora que quiser no botão <strong class="black--text">FUNDS</strong> e simule seu dinheiro em caixa</span>
        </v-sheet>        
        <v-sheet :elevation="6" class="mt-3 pa-2 primary">
            <span class="headline white--text font-weight-light">
                Na aba <strong class="black--text">CRYPTOCURRENCIES</strong> temos uma lista de moedas listadas na CoinMarketCap</span>
        </v-sheet>        
        <v-sheet :elevation="6" class="mt-3 pa-2 primary">
            <span class="headline white--text font-weight-light">
                Na aba <strong class="black--text">PORTFOLIO</strong> você pode consultar estatísticas das moedas que possui</span>
        </v-sheet>        
    </div>
</template>

<script>
export default {
    name: 'HomeApp',
    computed: {
        funds() { return this.$store.getters.funds }
    }
}
</script>

<style scoped>
</style>