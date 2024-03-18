<template>
	<v-card
		cols="12"
		class="col-12 d-flex external-card"
	>
		<v-text-field
			v-model="empresaForm.razaoSocial"
			label="Razão Social"
			:rules="[rules.required]"
			counter
			maxlength="64"
		></v-text-field>
		<v-text-field
			v-model="empresaForm.cnpj"
			:rules="[rules.required, rules.onlyNumbers]"
			label="CNPJ"
			counter
			maxlength="32"
		></v-text-field>
	</v-card>
</template>

<script>
	export default {
		name: 'EmpresaInputInfo',
		props: ['empresaForm'],
		data: () => ({
			rules: {
				required: value => !!value || 'Este campo é obrigatório',
				onlyNumbers: value =>
					/^[0-9]+$/.test(value) || 'Este campo deve conter apenas números',
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
