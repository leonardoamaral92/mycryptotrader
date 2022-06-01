<template>
    <v-col class="stat" lg="1" md="2" sm="2">
        <div class="text-h6">{{ stat.name }}</div>        
        <div v-if="isCurrency" :class="loss">{{ stat.value | currency }}</div>
        <div v-else :class="loss">{{ stat.value.toFixed(2) }}%</div>
    </v-col>
</template>

<script>
export default {
    props: ['stat'],
    computed: {
        loss() {
            return this.stat.value < 0 ? 'loss' : ''
        },
        isCurrency(){
            return this.stat.isCurrency
        }
    }
}
</script>

<style scoped>
.stat{
    border: 0.15rem solid #000;
    border-radius: 1rem;
    margin: 1rem 1rem 0 0;
    background-color: rgb(206, 206, 206);
}

.profit {
    color: green;
}

.loss {
    color: red;
}
</style>