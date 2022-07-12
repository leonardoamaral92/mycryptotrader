<template>
	<v-app>
		<Header v-if="autenticated"/>
		<v-main>
			<v-container>
				<transition name="slide" mode="out-in">
					<router-view></router-view>
				</transition>
			</v-container>
		</v-main>
	</v-app>
</template>

<script>
import { userKey } from "./global"
import Header from './components/Header.vue'

export default {
	components: {
		Header
	},
	computed: {
		autenticated(){
			return localStorage.getItem(userKey) && this.$store.state.user
		}
	},
	methods: {
		async getLoggedUser(){
			const json = localStorage.getItem(userKey)
			const userData = JSON.parse(json)
			this.$store.commit('setUser', null)

			if(!userData){
				this.validateToken = false
				//O return é apenas porque quero sair do método
				return this.$router.push({ name: 'auth'})
			}
			
			this.$store.commit('setUser', userData)
		}
	},
	created() {
		this.getLoggedUser()
	}
}
</script>

<style>
	@keyframes slide-in {
		from { transform: translateY(-30px); opacity: 0; }
		to { transform: translateY(0px); opacity: 1; }
	}

	@keyframes slide-out {
		from { transform: translateY(0px); opacity: 1; }
		to { transform: translateY(-30px); opacity: 0; }
	}

	.slide-enter-active{
		animation: slide-in 0.3s ease;
	}

	.slide-leave-active{
		animation: slide-out 0.3s ease;
	}
</style>

