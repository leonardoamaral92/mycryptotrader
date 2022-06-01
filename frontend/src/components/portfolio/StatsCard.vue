<template>
    <v-col class="stat">
        <div class="header">{{ stat.name }}</div>
        <div class="value" :class="isProfit ? 'profit' : 'loss'">{{ stat.value }}</div>
    </v-col>
</template>

<script>
export default {
    props: ['stat'],
    computed: {
        isProfit() {
            return this.stat.value > 0
        }
    }
}
</script>

<style scoped>
.stat{
    border: 1px solid #000;
    border-radius: 1rem;
}

.profit {
    color: green;
}

.loss {
    color: red;
}
</style>