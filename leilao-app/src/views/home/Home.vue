<template>
	<div class="style-page">
		<div style="margin: 0px 10px 0px 10px;">
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
						<v-card
							elevation="2"
							style="width: 100%; height: 190px"
						>
							<div style="display: flex; align-items: center">
								<v-icon
									large
									style="margin-left: 16px"
									>mdi-tie</v-icon
								>
								<div>
									<v-card-title>{{ empresa.razaoSocial }}</v-card-title>
									<v-card-subtitle>E-mail: {{ empresa.email }}</v-card-subtitle>
								</div>
							</div>
							<v-divider></v-divider>
							<v-card-text>
								<p>Empresa localizada em {{ empresa.logradouro }}.</p>
								<p>Cadastrada na plataforma desde {{ empresa.createdAt }}</p>
								
							</v-card-text>
						</v-card>
					</v-col>
				</v-row>
			</v-container>
		</div>
		
		<div style="margin: 0px 10px 0px 10px;">
			<v-container>
				<p class="topic-header">
					Conheça as {{ unidadeCounter }} Unidades que ajudaram a organização a arrecadar R$ {{ leilaoTotalValueFormat }}
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
						<v-card
							elevation="2"
							style="width: 100%; height: 100px"
						>
							<div style="display: flex; align-items: center">
								<v-icon
									large
									style="margin-left: 16px"
									>mdi-domain</v-icon
								>
								<div>
									<v-card-title>{{ unidade.nome }}</v-card-title>
									<v-card-subtitle>Cadastrada na plataforma
								desde {{ unidade.createdAt }}</v-card-subtitle>
								</div>
							</div>
						</v-card>
					</v-col>
				</v-row>
			</v-container>
		</div>
	</div>
</template>

<script>
	import { mapState, mapGetters } from 'vuex';

	export default {
		name: 'Home',
		methods: {
			async fetchData() {
				await this.$store.dispatch('fetchData', 'empresa');
				await this.$store.dispatch('fetchData', 'leilao');
				await this.$store.dispatch('fetchData', 'unidade');
			},
		},
		mounted() {
			this.fetchData();
		},
		computed: {
			...mapState(['empresaData', 'unidadeData']),
			...mapGetters(['empresaCounter', 'unidadeCounter', 'leilaoTotalValue']),
			empresaDataTimeFormat() {
				return this.empresaData.map( empresa => ({
					...empresa,
					createdAt: empresa.createdAt.split('T')[0]
				}))
			},
			unidadeDataTimeFormat() {
				return this.unidadeData.map( unidade => ({
					...unidade,
					createdAt: unidade.createdAt.split('T')[0]
				}))
			},
			leilaoTotalValueFormat() {
    const numeroFormatado = this.leilaoTotalValue.toFixed(2).replace('.', ',').replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    return numeroFormatado;
			}
		},
	};
</script>

<style>
	.style-page {
		background-color: #d9d9d9;
		/* display: flex; */
		/* align-items: center;
		justify-content: center; */
		height: 100vh;
	}

	.card-container {
		max-height: 400px;
		overflow-y: auto;
	}

/* Estilizando a barra de rolagem */
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
		color: #5E2129;
	}
	
</style>
