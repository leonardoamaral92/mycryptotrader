import Vue from 'vue'
import vuetify from './plugins/vuetify'
import App from './App.vue'

import router from './router'
import store from './store/store'

import './plugins/axios'

Vue.config.productionTip = false

Vue.filter('currency', value => {
	var formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD'
    });
    return formatter.format(value)
})

new Vue({
	router,
	vuetify,
	store,
	render: h => h(App),
}).$mount('#app')
