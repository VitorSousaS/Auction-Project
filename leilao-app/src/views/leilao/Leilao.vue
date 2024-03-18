<template>
	<v-data-table
		:headers="headers"
		:items="leilaoData"
		sort-by="id"
		class="elevation-1"
	>
		<template v-slot:item.inicioPrevisto="{ item }">
			{{ leilaoDataTimeFormat(item.inicioPrevisto) }}
		</template>
		<template v-slot:item.totalLeilao="{ item }">
			R$ {{ leilaoTotalLeilaoFormat(item.totalLeilao) }}
		</template>
		<template v-slot:top>
			<v-toolbar flat>
				<v-toolbar-title>Leilão</v-toolbar-title>
				<v-divider
					class="mx-4"
					inset
					vertical
				></v-divider>
			</v-toolbar>
		</template>
	</v-data-table>
</template>

<script>
	import { mapState } from 'vuex';

	export default {
		name: 'Leilao',
		data: () => ({
			headers: [
				{ text: 'Vendedor', value: 'vendedor' },
				{ text: 'Inicio Previsto', value: 'inicioPrevisto' },
				{ text: 'Total do Leilão', value: 'totalLeilao' },
			],
		}),
		methods: {
			async fetchData() {
				await this.$store.dispatch('fetchData', 'leilao');
			},
			leilaoDataTimeFormat(leilaoTime) {
				return new Date(leilaoTime).toLocaleString('pt-BR');
			},
			leilaoTotalLeilaoFormat(moneyValue) {
				return moneyValue.toFixed(2).toLocaleString('pt-BR', {
					tyle: 'currency',
					cusrrency: 'BRL',
				});
			},
		},
		computed: {
			...mapState(['leilaoData']),
		},
		created() {
			this.fetchData();
		},
	};
</script>
