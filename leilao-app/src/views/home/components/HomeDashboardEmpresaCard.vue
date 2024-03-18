<template>
	<v-card
		elevation="2"
		class="external-card"
	>
		<div class="div-card">
			<v-icon
				large
				class="icon-card"
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
</template>

<script>
	export default {
		name: 'HomeDashboardEmpresaCard',
		props: ['empresa'],
	};
</script>

<style>
.external-card {
	width: 100%;
	height: 190px;
}

.div-card {
	display: flex;
	align-items: center;
}

.icon-card {
	margin-left: 16px;
}
</style>
