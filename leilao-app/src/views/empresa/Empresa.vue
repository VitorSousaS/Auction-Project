<template>
	<div>
		<TituloPagina titulo="Dados das Empresas:" />
		<BotaoIcone
			classe="ma-2"
			icon="mdi-plus-circle"
			phrase="Novo"
			where="/empresa/0"
		/>
		<TabelaPadrao
			:headers="headers"
			url="empresa"
			alarmtext="Todos os registros vinculados a está empresa serão deletados"
		/>
	</div>
</template>

<script>
	import TituloPagina from '../../components/TituloPagina.vue';
	import BotaoIcone from '../../components/BotaoIcone.vue';
	import TabelaPadrao from '../../components/TabelaPadrao.vue';

	export default {
		name: 'Empresa',
		data() {
			return {
				dialog: false,
				headers: [
					{ text: 'CNPJ', value: 'cnpj' },
					{ text: 'Razão Social', value: 'razaoSocial' },
					{ text: 'Telefone ', value: 'telefone' },
					{ text: 'E-mail', value: 'email' },
					{ text: 'Ações', value: 'actions' },
				],
			};
		},
		components: {
			TituloPagina,
			TabelaPadrao,
			BotaoIcone,
		},
	};
</script>
