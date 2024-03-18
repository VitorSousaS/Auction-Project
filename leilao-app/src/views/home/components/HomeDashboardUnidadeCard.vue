<template>
	<v-card
		elevation="2"
		class="external-card"
	>
		<div class="div-card">
			<v-icon
				large
				class="icon-card"
				>mdi-domain</v-icon
			>
			<div>
				<v-card-title>{{ unidade.nome }}</v-card-title>
				<v-card-subtitle
					>Cadastrada na plataforma desde
					{{ unidade.createdAt }}</v-card-subtitle
				>
			</div>
		</div>
	</v-card>
</template>

<script>
	export default {
		name: 'HomeDashboardUnidadeCard',
		props: ['unidade'],
	};
</script>

<style>
.external-card {
	width: 100%;
	height: 100px;
}

.div-card {
	display: flex;
	align-items: center;
}

.icon-card {
	margin-left: 16px;
}
</style>