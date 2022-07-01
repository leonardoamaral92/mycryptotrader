<template>
    <div class="auth-content">
        <div class="auth-modal">
            <img src="@/assets/logo.png" width="200" alt="Logo" />
            <hr>
            <div class="auth-title">{{showSignup ? 'Cadastro' : 'Login' }}</div>
            
            <input type="text" placeholder="Usuário" v-model="login" />
            <input type="text" placeholder="Senha" v-model="password"/>
            
            <v-btn color="primary" @click="signin">Login</v-btn>
        </div>        
    </div>
</template>

<script>
import axios from 'axios'
import { baseApiUrl, userKey } from '@/global';

export default {
    data() {
        return {
            showSignup: false,
            user: {},
            login: null,
            password: null,
        }
    },
    methods: {
        signin(){

            if(!this.login || !this.password){
                alert('Login ou senha não preenchidos')
                return;
            }

            axios.post(`${baseApiUrl}/auth/signin`, {
                login: this.login,
                password: this.password
            })
            .then(response => {                            
                console.log(response.data)                          
                this.$store.commit('setUser', response.data)
                localStorage.setItem(userKey, JSON.stringify(response.data))
                this.$router.push({name: 'resume' })
                
            });
        }
    }
}
</script>

<style scoped>
    .auth-content {
        margin-top: 20px;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .auth-modal{
        background-color: #FFF;
        width: 350px;
        padding: 35px;
        box-shadow: 0 1px 5px rgba(0, 0, 0, 0.15);

        display: flex;
        flex-direction: column;
        align-items: center;
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