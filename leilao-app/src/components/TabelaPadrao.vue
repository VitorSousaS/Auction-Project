<template>
	<v-data-table
		:headers="headers"
		:items="responseData"
		:items-per-page="15"
		class="elevation-1"
		dense
		expand-icon
	>
		<template v-slot:item="props">
			<tr>
				<td>{{ props.item.cnpj }}</td>
				<td>{{ props.item.razaoSocial }}</td>
				<td>{{ props.item.telefone }}</td>
				<td>{{ props.item.email }}</td>
				<td>
					<div class="button-container">
						<v-btn
							:to="url + '/' + props.item.id"
							icon
							><v-icon>mdi-pencil</v-icon></v-btn
						>
						<BotaoDeleteModal
							:item="props.item"
							:fetchData="fetchData"
						></BotaoDeleteModal>
					</div>
				</td>
			</tr>
		</template>
		<template v-slot:footer.page-text>
			<BotaoIcone
				classe="ma-2"
				icon="mdi-arrow-left"
				phrase="Voltar"
				where="/"
			/>
		</template>
	</v-data-table>
</template>

<script>
	import axios from 'axios';
	import BotaoDeleteModal from '../components/BotaoDeleteModal.vue';
	import BotaoIcone from '../components/BotaoIcone.vue';

	export default {
		name: 'TabelaPadrao',
		props: ['headers', 'url'],
		data() {
			return {
				responseData: [],
			};
		},
		mounted() {
			this.fetchData();
		},
		methods: {
			async fetchData() {
				try {
					const response = await axios.get('http://localhost:8081/' + this.url);
					this.responseData = response.data;
				} catch (error) {
					console.error('Erro ao buscar dados da API:', error);
				}
			},
		},
		components: {
			BotaoDeleteModal,
			BotaoIcone,
		},
	};
</script>

<style scoped>
	.button-container {
		display: flex;
	}

	.button-container v-btn {
		margin-right: 8px; /* Ajuste a margem conforme necessário */
	}
</style>
