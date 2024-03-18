<template>
	<div class="style-page">
		<home-dashboard-empresa />
		<home-dashboard-unidade />
	</div>
</template>

<script>
	import HomeDashboardEmpresa from './components/HomeDashboardEmpresa.vue';
	import HomeDashboardUnidade from './components/HomeDashboardUnidade.vue';

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
		components: {
			HomeDashboardEmpresa,
			HomeDashboardUnidade,
		},
	};
</script>

<style>
	.style-page {
		background-color: #d9d9d9;
		height: 100vh;
	}
</style>
