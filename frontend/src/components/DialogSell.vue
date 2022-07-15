<template>
  <v-dialog v-model="dialog" persistent max-width="600px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn v-bind="attrs" v-on="on" :class="`darken-3 white--text green`" x-large>Sell</v-btn>
    </template>
    <v-card>
      <v-card-title>
        <span class="text-h5">{{ headerModalText }}</span>
      </v-card-title>
      <template v-if="currentStatusSell === StatusSell.DOING">
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-select :items="dataCoins" label="Coin*" @change="selectCoin" required></v-select>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="Price*" v-model="price" />
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="Quantity Coin*" v-model="qtdCoin" required />
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="Total" :value="opTotal" disabled/>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="Date*" v-model="opDate" />
              </v-col>
              <v-col cols="12">
                <v-select :items="dataPortfolio" label="Portfolio*" @change="selectPortfolio" required></v-select>
              </v-col>
            </v-row>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
      </template>

      <v-progress-circular v-if="currentStatusSell === StatusSell.PENDING" indeterminate color="green">
      </v-progress-circular>

      <v-card-text v-if="currentStatusSell === StatusSell.SUCCESS || currentStatusSell === StatusSell.FAILED"
        class="text-h3">{{msgSell}}</v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text @click="closeModal">
          Close
        </v-btn>
        <v-btn v-if="currentStatusSell === StatusSell.DOING"
          class="green darken-1 white--text"
          @click="operateCrypto" text width="40%">Sell
        </v-btn>
      </v-card-actions>
    </v-card>
    <v-card>
    </v-card>
    <v-card>

    </v-card>

  </v-dialog>
</template>

<script>
import { showError } from '@/global';
const StatusSell = Object.freeze({
  DOING: 'doing',
  PENDING: 'pending',
  SUCCESS: 'success',
  FAILED: 'failed'
});

export default {
  computed: {
    opTotal() {
      return this.price * this.qtdCoin || 0
    },
    dataCoins() {
      return this.$store.getters.cryptocurrencies.map(coin => ({ text: coin.name, value: coin.id }))
    },
    dataPortfolio() {
      return this.$store.getters.portfolios.map(portfolio => ({ text: portfolio.name, value: portfolio.id }))
    }    
  },
  data() {
    return {
      StatusSell,
      dialog: false,
      coinSelected: null,
      selectedPortfolio: null,
      qtdCoin: 0,
      price: 0,
      currentStatusSell: StatusSell.DOING,
      opDate: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      headerModalText: 'Sell Coin'
    }
  },
  methods: {
    selectCoin(id) {
      //Refatorar, não precisa mais buscar infos da coin
      const filteredCoin = this.$store.getters.cryptocurrencies.filter(coin => id == coin.id)
      if (filteredCoin) {
        this.coinSelected = filteredCoin[0]
        const coinPrice = this.coinSelected.quote.USD.price
        this.price = coinPrice >= 1 ? coinPrice.toFixed(2) : coinPrice
      }
    },
    selectPortfolio(id) {
      const filteredPortfolio = this.$store.getters.portfolios.filter(p => id == p.id)
      this.selectedPortfolio = filteredPortfolio[0]
    },
    operateCrypto() {

      if(!this.isValidOperation())
        return

      this.currentStatusSell = StatusSell.PENDING
      this.headerModalText = 'Pending'
      //REFATORAR passando só os parâmetros necessários -> buscar o resto no servidor
      const order = {
        coinId: this.coinSelected.id,
        portfolioId: this.selectedPortfolio.id,
        investorId: this.$store.state.user.investorId,
        coinName: this.coinSelected.name,
        coinSymbol: this.coinSelected.symbol,
        coinPrice: this.price,
        date: this.opDate,
        qtdCoin: this.qtdCoin,
        totalValue: this.opTotal
      }

      this.$store.dispatch('sellCrypto', order)
        .then(() => {
          this.closeModal()
          this.$toasted.global.defaultSuccess()
        })
        .catch(err => {
          showError(err)
        })
    },
    closeModal() {
      this.coinSelected = null
      this.qtdCoin = 0
      this.price = 0
      this.dialog = false
      this.headerModalText= 'Sell Coin'
      this.currentStatusSell =  StatusSell.DOING
    },
    isValidOperation(){
        if(!this.coinSelected || !this.selectedPortfolio || this.qtdCoin <= 0){
          showError('Verifique os campos obrigatórios.')
          return false
        }

        console.log('Validando operação...')
        const listCoins = this.$store.getters.portfolioResumeCoins
        const filteredCoin = listCoins.find(coin => this.coinSelected.id === coin.coinId)      

        if(!filteredCoin){
          showError('Verifique se possui saldo na moeda selecionada.')
          return false
        }

        if(filteredCoin.qtdTotalCoin < this.qtdCoin){
          showError(`Quantidade maior do que possui: ${filteredCoin.qtdTotalCoin}`)
          return false
        }

        return true
    }
  }
}
</script>

<style scoped>
</style>