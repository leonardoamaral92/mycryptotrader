<template>
    <div class="d-flex justify-center align-center mt-4">
        <div class="auth-modal d-flex flex-column align-center">
            <img src="@/assets/logo.png" width="200" alt="Logo" />
            <hr>
            <div class="auth-title">{{showSignup ? 'Cadastro' : 'Login' }}</div>
            
           <template v-if="showSignup">
                <input type="text"    placeholder="Nome Completo" v-model="user.fullName" />
                <input type="email"    placeholder="Email" v-model="user.email" />
                <input type="password" placeholder="Senha" v-model="user.password"/>                
                <input type="password" placeholder="Confirme a senha" v-model="confirmPassword" />
                <label v-if="passwordDismatch" class="red--text align-start">Senhas incompatíveis</label>
                <v-btn color="primary" class="mt-2 d-flex justify-end" @click="signup">Criar conta</v-btn>
           </template>
           <template v-else>
                <input  type="email" placeholder="Email" v-model="user.login" />
                <input type="password" placeholder="Senha" v-model="user.password"/>
                <v-btn color="primary" class="mt-2 d-flex justify-end" @click="signin">Login</v-btn>
           </template>
            
        </div>        
    </div>
</template>

<script>
import axios from 'axios'
import { baseApiUrl, userKey, showError } from '@/global';

export default {
    data() {
        return {
            showSignup: false,
            user: {},
            confirmPassword: ""
        }
    },
    computed: {
        passwordDismatch(){
            return this.user.password && this.confirmPassword && (this.user.password != this.confirmPassword)
        }
    },
    methods: {
        signin(){
            if(!this.user.login || !this.user.password){
                showError('Login ou senha não preenchidos')
                return;
            }

            axios.post(`${baseApiUrl}/auth/signin`, this.user)
            .then(response => {                            
                console.log(response.data)
                this.$store.dispatch('authenticate', response.data)
                localStorage.setItem(userKey, JSON.stringify(response.data))
                this.$router.push({name: 'resume' })          
                this.$toasted.global.defaultSuccess()
            })
            .catch(showError)
        },
        signup(){
            if(!this.user.fullName || !this.user.email || !this.user.password || !this.confirmPassword){
                showError('Um ou mais campos não preenchidos')
                return;
            }

            axios.post(`${baseApiUrl}/auth/signup`, this.user)
            .then(() => {
                this.$toasted.global.defaultSuccess()
                this.user = {}
                this.showSignup = false
            })
            .catch(showError)
        }
    }
}
</script>

<style scoped>

    .auth-modal{
        background-color: #FFF;
        width: 350px;
        padding: 35px;
        box-shadow: 0 1px 5px rgba(0, 0, 0, 0.15);
    }

    .auth-title{
        font-size: 1.2rem;
        font-weight: 500;
        margin-top: 10px;
        margin-bottom: 15px;
    }

    .auth-modal input {
        border: 1px solid #BBB;
        width: 100%;
        margin-bottom: 15px;
        padding: 3px 8px;
        outline: none;
    }

    .auth-modal hr {
        border: 0;
        width: 100%;
        height: 1px;
        background-image: linear-gradient(to right,
            rgba(120, 120, 120, 0),
            rgba(120, 120, 120, 0.75),
            rgba(120, 120, 120, 0));
    }
</style>