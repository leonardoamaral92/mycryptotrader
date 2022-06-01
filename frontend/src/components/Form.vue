<template>
  <v-dialog v-model="dialog" persistent max-width="600px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn v-bind="attrs" v-on="on" :class="`darken-3 white--text ${classAtr}`" x-large>
        {{ text }}
      </v-btn>
    </template>
    <v-card>
      <v-card-title>
        <span class="text-h5">{{ text }} Coin</span>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-select :items="coinNames" label="Coin*" @change="selectCoin" required></v-select>
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field label="Price*" v-model="price" />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field label="Quantity Coin*" v-model="qtdCoin" required />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field label="Total" :value="opTotal" disabled />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field label="Date" v-model="opDate" />
            </v-col>
          </v-row>
        </v-container>
        <small>*indicates required field</small>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text @click="closeModal">
          Close
        </v-btn>
        <v-btn :class="`${classAtr} darken-1 white--text`" text @click="operateCrypto">
          {{ text }}
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'FormDialog',
  props: {
    text: String,
    classAtr: String,
    coinNames: Array,
    operation: String
  },
  computed: {
    opTotal() {
      return this.price * this.qtdCoin || 0
    }
  },
  data() {
    return {
      dialog: false,
      coinSelected: null,
      qtdCoin: 0,
      price: 0,
      opDate: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)
    }
  },
  methods: {
    selectCoin(name) {
      const filteredCoin = this.$store.getters.cryptocurrencies.filter(coin => name == coin.name)
      console.log(filteredCoin)
      if (filteredCoin) {
        this.coinSelected = filteredCoin[0]
        const coinPrice = this.coinSelected.quote.USD.price
        this.price = coinPrice >= 1 ? coinPrice.toFixed(2) : coinPrice
      }
    },
    operateCrypto() {
      const order = {
        id: this.coinSelected.id,
        name: this.coinSelected.name,
        symbol: this.coinSelected.symbol,
        price: this.price,
        quantity: this.qtdCoin
      }

      console.log(order)
      this.$store.dispatch(this.operation, order)
      //this.quantity = 0
      setTimeout(() => {
        this.dialog = false
      }, 1000)
    },
    closeModal(){
      this.coinSelected = null
      this.qtdCoin = 0
      this.price = 0
      this.dialog = false
    }
  }
}
</script>

<style scoped>
</style>