<template>
	<div class="external-div">
		<v-container>
			<p class="topic-header">
				Veja as nossas {{ empresaCounter }} Empresas Parceiras
			</p>
		</v-container>
		<v-container
			fluid
			class="card-container"
		>
			<v-row>
				<v-col
					v-for="(empresa, index) in empresaDataTimeFormat"
					:key="index"
					cols="12"
					md="6"
					lg="6"
				>
					<home-dashboard-empresa-card :empresa="empresa" />
				</v-col>
			</v-row>
		</v-container>
	</div>
</template>

<script>
	import { mapState, mapGetters } from 'vuex';
	import HomeDashboardEmpresaCard from './HomeDashboardEmpresaCard.vue';

	export default {
		name: 'HomeDashboardEmpresa',

		computed: {
			...mapState(['empresaData', 'unidadeData']),
			...mapGetters(['empresaCounter', 'unidadeCounter', 'leilaoTotalValue']),
			empresaDataTimeFormat() {
				return this.empresaData.map(empresa => ({
					...empresa,
					createdAt: empresa.createdAt.split('T')[0],
				}));
			},
		},
		components: {
			HomeDashboardEmpresaCard,
		},
	};
</script>

<style>
	.card-container {
		max-height: 400px;
		overflow-y: auto;
	}

	.card-container::-webkit-scrollbar {
		width: 10px;
	}

	.card-container::-webkit-scrollbar-thumb {
		background-color: rgba(0, 0, 0, 0.5);
		border-radius: 10px;
	}

	.card-container::-webkit-scrollbar-track {
		background-color: rgba(0, 0, 0, 0.1);
	}

	.topic-header {
		font-size: 28px;
		font-weight: bold;
		text-align: center;
		margin-top: 20px;
		margin-bottom: 20px;
		color: #5e2129;
	}

	.external-div {
		margin: 0px 10px 0px 10px;
	}
</style>
