<template>
	<div class="external-div">
		<v-container>
			<p class="topic-header">
				Conheça as {{ unidadeCounter }} Unidades que ajudaram a organização a
				arrecadar R$ {{ leilaoTotalValueFormat }}
			</p>
		</v-container>
		<v-container
			fluid
			class="card-container"
		>
			<v-row>
				<v-col
					v-for="(unidade, index) in unidadeDataTimeFormat"
					:key="index"
					cols="12"
					md="6"
					lg="6"
				>
					<home-dashboard-unidade-card :unidade="unidade" />
				</v-col>
			</v-row>
		</v-container>
	</div>
</template>

<script>
	import { mapState, mapGetters } from 'vuex';
	import HomeDashboardUnidadeCard from './HomeDashboardUnidadeCard.vue';

	export default {
		name: 'HomeDashboardUnidade',
		computed: {
			...mapState(['unidadeData']),
			...mapGetters(['unidadeCounter', 'leilaoTotalValue']),
			unidadeDataTimeFormat() {
				return this.unidadeData.map(unidade => ({
					...unidade,
					createdAt: unidade.createdAt.split('T')[0],
				}));
			},
			leilaoTotalValueFormat() {
				const numeroFormatado = this.leilaoTotalValue
					.toFixed(2)
					.replace('.', ',')
					.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
				return numeroFormatado;
			},
		},
		components: {
			HomeDashboardUnidadeCard,
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

