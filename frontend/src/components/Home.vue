<template>
    <div>        
        <img src="@/assets/logo.png" width="200" alt="Logo" />
        <h2 class="font-weight-light mb-6">Gerenciador de carteiras de criptomoedas</h2>
        
        <v-sheet :elevation="6" class="pa-2 primary">
            <span class="headline white--text font-weight-light">
                Adicione operações de compra ou venda de criptomoedas</span>
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
                Na aba <strong class="black--text">RESUME</strong> você pode consultar o resumo de todas as suas carteiras</span>
        </v-sheet>
        <v-sheet v-if="!user" :elevation="3" class="mt-3 pa-2 primary">
            <span class="headline white--text font-weight-light">
                Se você já possui cadastro, faça o login <v-btn to="/auth" color="warning">AQUI</v-btn></span>
        </v-sheet>
        <v-sheet v-if="!user" :elevation="3" class="mt-3 pa-2 primary">
            <span class="headline white--text font-weight-light">
                Se você não possui cadastro, <v-btn to="/auth/register" color="warning">CADASTRE-SE</v-btn></span>
        </v-sheet>
    </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
    name: 'HomeApp',
    computed: mapState(['user'])
}
</script>

<style scoped>
    span a {
        text-decoration: none;    
        color: black;   
        font-weight: 600;     
    }
</style>