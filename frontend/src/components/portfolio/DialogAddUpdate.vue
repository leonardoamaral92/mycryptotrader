<template>
    <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on, attrs }">
            <v-btn v-bind="attrs" v-on="on" color="primary">
                {{ headerText }}
            </v-btn>
        </template>
        <v-card>
            <v-card-title>
                <span class="text-h5">{{ headerText }}</span>
            </v-card-title>
            <v-card-text>
                <v-text-field label="Name" v-model="textValue" />
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="closeModal">
                    Close
                </v-btn>
                <v-btn color="primary" text @click="addUpdate">
                    {{ btnText }}
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
export default {
    props: {
        btnText: String,
        headerText: String,
        actionName: String
    },
    data() {
        return {
            dialog: false,
            textValue: ''
        }
    },
    methods: {
        closeModal() {
            this.dialog = false
        },
        addUpdate() {
            this.$store.dispatch(this.actionName, this.textValue);
            this.dialog = false;
        }
    }
}
</script>