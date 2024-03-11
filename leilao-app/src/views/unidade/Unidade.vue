<template>
	<div>
		<Snackbar
			cor="success"
			texto="Unidade atualizada com sucesso!"
			icon="mdi-checkbox-marked-circle"
			:ativo="snackbar"
		/>
		<TituloPagina titulo="Dados da Unidade:" />
		<TabelaEditavel
			:headers="headers"
			url="unidade"
		/>
	</div>
</template>

<script>
	import Snackbar from '../../components/Snackbar.vue';
	import TituloPagina from '../../components/TituloPagina.vue';
	import TabelaEditavel from '../../components/TabelaEditavel.vue';

	export default {
		name: 'Unidade',
		data() {
			return {
				headers: [
					{ text: '#', value: 'id' },
					{ text: 'Nome', value: 'nome' },
					{ text: 'Atualizado', value: 'updatedAt' },
					{ text: 'Criado', value: 'createdAt' },
					{ text: 'Ações', value: 'actions' },
				],
				snackbar: false,
			};
		},
		components: {
			TituloPagina,
			Snackbar,
			TabelaEditavel,
		},
	};
</script>
