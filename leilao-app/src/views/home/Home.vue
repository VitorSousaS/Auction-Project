<template>
	<v-row>
		<v-col cols="12" md="6">
			<div class="text-h6 bold">Bem-Vindo a central de informação de Leilão House.</div>
			<div>
				<v-btn elevation="5" x-large to="/unidades">
					<v-icon>
						mdi-domain
					</v-icon>
					Unidades
				</v-btn>
				<v-btn elevation="5" x-large to="/empresas">
					<v-icon>
						mdi-tie
					</v-icon>
					Empresas
				</v-btn>
				<v-btn elevation="5" x-large to="/leiloes">
					<v-icon>
						mdi-curtains
					</v-icon>
					Leilões
				</v-btn>
			</div>
		</v-col>
	</v-row>
</template>

<script>
export default {
	name: "Home",
};
</script>

<style scoped>
/* Adicione estilos personalizados aqui, se necessário */
.border {
	border: 1px solid #ccc;
}

.border-right {
	border-right: 1px solid #ccc;
}

.border-bottom {
	border-bottom: 1px solid #ccc;
}

.mb-2 {
	margin-bottom: 2em;
}
</style>
