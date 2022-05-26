<template>
    <v-layout row wrap>
        <Cryptocurrency v-for="cryptocurrency in cryptocurrencies" :key="cryptocurrency.id" :cryptocurrency="cryptocurrency" />
    </v-layout>
</template>

<script>
import Cryptocurrency from './Cryptocurrency.vue'

export default {
    name: 'CryptocurrenciesApp',
    components: { Cryptocurrency },
    computed: {
        cryptocurrencies(){
            return this.$store.getters.cryptocurrencies
        }
    }
}
</script>

<style scoped>

</style>