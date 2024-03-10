<template>
	<div>
		<h1>Dados das Empresas:</h1>
		<v-btn class="ma-2" to="/empresa/0">
			<v-icon>mdi-plus-circle</v-icon> Novo
		</v-btn>
		<v-spacer></v-spacer>
		<v-data-table :headers="headers" :items="responseData" :items-per-page="-1" class="elevation-1" dense expand-icon>
			<template v-slot:item="props">
				<tr>
					<td>{{ props.item.cnpj }}</td>
					<td>{{ props.item.razaoSocial }}</td>
					<td>{{ props.item.telefone }}</td>
					<td>{{ props.item.email }}</td>
					<td>
						<v-btn :to="'/empresa/' + props.item.id" icon><v-icon>mdi-pencil</v-icon></v-btn>
						<BotaoDeleteModal :item=props.item :fetchData="fetchData"></BotaoDeleteModal>
					</td>
				</tr>
			</template>
			<template v-slot:footer.page-text>
				<v-btn class="ma-2" to="/">
					<v-icon>mdi-arrow-left</v-icon> Voltar
				</v-btn>
			</template>
		</v-data-table>
	</div>
</template>

<script>
import axios from 'axios';
import BotaoDeleteModal from '../../components/BotaoDeleteModal.vue';

export default {
	name: 'MyComponent',
	data() {
		return {
			dialog: false,
			responseData: [],
			headers: [
				{ text: 'CNPJ', value: 'cnpj' },
				{ text: 'Razão Social', value: 'razaoSocial' },
				{ text: 'Telefone ', value: 'telefone ' },
				{ text: 'E-mail', value: 'email' },
				{ text: 'Ações', value: 'actions' }
			]
		};
	},
	mounted() {
		this.fetchData();
	},
	methods: {
		async fetchData() {
			try {
				const response = await axios.get('http://localhost:8081/empresa/');
				this.responseData = response.data;
			} catch (error) {
				console.error('Erro ao buscar dados da API:', error);
			}
		},
		removerItem(item) {
			console.log('Remover item:', item);
		}
	},
  components: {
    BotaoDeleteModal
  }
};
</script>
