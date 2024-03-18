<template>
	<v-card
		cols="12"
		class="col-12 d-flex external-card"
	>
		<v-text-field
			v-model="empresaForm.usuario"
			label="Usuario"
			:rules="[rules.required]"
			counter
			maxlength="20"
		></v-text-field>
		<v-text-field
			v-model="empresaForm.senha"
			label="Senha"
			counter
			maxlength="128"
		></v-text-field>
	</v-card>
</template>

<script>
	export default {
		name: 'EmpresaInputLogin',
		props: ['empresaForm'],
		data: () => ({
			rules: {
				required: value => !!value || 'Este campo é obrigatório',
			},
		}),
	};
</script>

<style>
	.external-card {
		margin: 0px 10px 0px 10px;
		gap: 16px;
		padding: 10px 10px 10px 10px;
	}
</style>
