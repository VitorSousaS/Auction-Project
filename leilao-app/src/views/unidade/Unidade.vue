<template>
	<div>
		<v-snackbar v-model="snackbar" absolute top right color="success">
			<span>Unidadte atualizada com sucesso!</span>
			<v-icon dark>
				mdi-checkbox-marked-circle
			</v-icon>
		</v-snackbar>
		<h1>Dados da Unidade:</h1>
		<v-data-table :headers="headers" :items="responseData" :items-per-page="-1" class="elevation-1" dense expand-icon>
			<template v-slot:item="props">
				<tr>
					<td>{{ props.item.id }}</td>
					<td>
						<template v-if="!props.item.editando">
							{{ props.item.nome }}
						</template>
						<template v-else>
							<v-text-field maxlength="128" v-model="props.item.nome"></v-text-field>
						</template>
					</td>
					<td>{{ props.item.updatedAt }}</td>
					<td>{{ props.item.createdAt }}</td>
					<td>
						<v-icon @click="editarItem(props.item)">{{ !props.item.editando ? 'mdi-pencil' : 'mdi-check' }} </v-icon>
					</td>
				</tr>
			</template>
			<template v-slot:footer.page-text>
				<v-btn class="ma-2" @click="fetchData">
					<v-icon>mdi-reload</v-icon> Recarregar
				</v-btn>
				<v-btn class="ma-2" to="/">
					<v-icon>mdi-arrow-left</v-icon> Voltar
				</v-btn>
			</template>
		</v-data-table>
	</div>
</template>

<script>
import axios from 'axios';

export default {
	name: 'MyComponent',
	data() {
		return {
			responseData: [],
			headers: [
				{ text: '#', value: 'id' },
				{ text: 'Nome', value: 'nome' },
				{ text: 'Atualizado', value: 'updatedAt' },
				{ text: 'Criado', value: 'createdAt' },
				{ text: 'Ações', value: 'actions' }
			],
			snackbar: false
		};
	},
	mounted() {
		this.fetchData();
	},
	methods: {
		async fetchData() {
			try {
				const response = await axios.get('http://localhost:8081/unidade');
				this.responseData = response.data;
				this.responseData = response.data.map(item => ({ ...item, editando: false }));
				console.log(this.responseData);
			} catch (error) {
				console.error('Erro ao buscar dados da API:', error);
			}
		},
		editarItem(item) {
			this.responseData.forEach(row => {
				if (row.editando && row.id === item.id) {
					row.editando = false;
					this.salvarNovoNome(item);
				} else {
					row.editando = row.id === item.id;
				}
			});
		},
		async salvarNovoNome(item) {
			const requestData = {
				nome: item.nome
			};
			try {
				await axios.put('http://localhost:8081/unidade/' + item.id, requestData);
				this.snackbar = true;
				this.fetchData()
			} catch (error) {
				console.error('Erro ao atualizar nome:', error);
			}
		}
	}
};
</script>
