<template>    
    <v-row class="d-flex justify-center mt-6">
        <v-col class="d-flex justify-space-between rounded-lg grey lighten-2" lg="6">
            <h3 class="ml-3">{{ portfolio.name }}</h3>
            <div>
                <v-btn class="mr-2" @click="dialogEdit = true">
                    <v-icon>mdi-pencil</v-icon>
                </v-btn>                
                <v-btn @click="dialogDelete = true">
                    <v-icon>mdi-delete</v-icon>
                </v-btn>
            </div>
        </v-col>

        <!--Dialog for edit portfolio name -->
        <v-dialog v-model="dialogEdit" persistent max-width="600px">
            <v-card>
                <v-card-title>
                    <span class="text-h5">Edit portfolio</span>
                </v-card-title>
                <v-card-text>
                    <v-text-field label="Name" v-model="newName" />
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="closeModal('dialogEdit')">
                        Close
                    </v-btn>
                    <v-btn color="primary" text @click="editPortfolioName">
                        Save portfolio
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>

        <!--Dialog for confirm delete portfolio -->
        <v-dialog v-model="dialogDelete" persistent max-width="600px">
            <v-card>
                <v-card-title>
                    <span class="text-h5">Delete portfolio</span>
                </v-card-title>
                <v-card-text>
                    <span>This portfolio has operations, you are sure?</span>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="closeModal('dialogDelete')">
                        Close
                    </v-btn>
                    <v-btn color="red" text @click="deletePortfolio">
                        DELETE
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>
</template>

<script>

export default {
    props: ['portfolio'],
    data() {
        return {
            dialogEdit: false,
            dialogDelete: false,
            newName: this.portfolio.name
        }
    },
    methods: {
        closeModal(dialogName){
            this[dialogName] = false
        },
        deletePortfolio() {
            this.$store.dispatch('deletePortfolio', this.portfolio)
        },
        editPortfolioName() {            
            const newPortfolio = {
                id: this.portfolio.id,
                name: this.newName
            }
            this.$store.dispatch('editPortfolioName', newPortfolio)
            this.dialogdit = false
        }
    }
}
</script>