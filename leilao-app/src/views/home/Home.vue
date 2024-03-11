<template>
	<v-row>
		<v-col
			cols="12"
			md="6"
		>
			<TituloPagina
				titulo="Bem-Vindo a central de informação de Leilão House."
			/>
			<div>
				<BotaoIcone
					xLarge
					elevation="5"
					icon="mdi-domain"
					phrase="Unidades"
					where="/unidades"
				/>
				<BotaoIcone
					xLarge
					elevation="5"
					icon="mdi-tie"
					phrase="Empresas"
					where="/empresas"
				/>
				<BotaoIcone
					x-large
					elevation="5"
					icon="mdi-curtains"
					phrase="Leilões"
					where="/leiloes"
				/>
			</div>
		</v-col>
	</v-row>
</template>

<script>
	import BotaoIcone from '../../components/BotaoIcone.vue';
	import TituloPagina from '../../components/TituloPagina.vue';

	export default {
		name: 'Home',
		components: {
			BotaoIcone,
			TituloPagina,
		},
	};
</script>
