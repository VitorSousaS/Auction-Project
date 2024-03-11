<template>
	<div>
		<TituloPagina titulo="Dados dos Leilões:" />
		<TabelaPadrao
			:headers="headers"
			url="leilao"
			acaoDisabled="true"
		/>
	</div>
</template>

<script>
	import TituloPagina from '../../components/TituloPagina.vue';
	import TabelaPadrao from '../../components/TabelaPadrao.vue';

	export default {
		name: 'Leilao',
		data() {
			return {
				dialog: false,
				headers: [
					{ text: 'Vendedor', value: 'vendedor' },
					{ text: 'Somatório base ($)', value: 'totalLeilao' },
					{ text: 'Data de Início', value: 'inicioPrevisto' },
				],
			};
		},
		components: {
			TituloPagina,
			TabelaPadrao
		}
	};
</script>
