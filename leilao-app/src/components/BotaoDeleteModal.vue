<template>
  <div>
    <v-btn @click.stop="dialog = true" icon ><v-icon>mdi-close-circle</v-icon></v-btn>

    <v-dialog
      v-model="dialog"
      max-width="500"
    >
      <v-card>
        <v-card-title class="text-h6">
					Confirma a ação de deletar:
        </v-card-title>
        <v-card-title class="text-h6 bold">
					{{ item.razaoSocial }} ({{ item.id }})
        </v-card-title>
				
        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="green"
            text
            @click="fecharDialogEremoverItem"
          >
            Sim
          </v-btn>

          <v-btn
            color="red"
            text
            @click="dialog = false"
          >
            Não
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import axios from 'axios';

export default {
	name: 'BotaoDeleteModal',
  props: ['item','fetchData'],
	data() {
		return{
			dialog: false,
		}
	},
	methods: {
    async fecharDialogEremoverItem() {
			this.dialog = false;
			
			console.log('this.item.id: ', this.item.id);
      await this.deleteData();
			this.fetchData();
    },
		async deleteData() {
			try {
				console.log('URL: http://localhost:8081/empresa/' + this.item.id);
				const response = await axios.delete('http://localhost:8081/empresa/' + this.item.id);
				console.log('response: ', response);
				console.log('response.data: ', response.data);
			} catch (error) {
				console.error('Erro ao buscar dados da API:', error);
			}
		}
  },
}
</script>