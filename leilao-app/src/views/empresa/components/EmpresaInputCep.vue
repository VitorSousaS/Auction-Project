<template>
	<v-card
		cols="12"
		class="col-12 d-flex flex-column external-card"
	>
		<v-row>
			<v-col cols="6">
				<v-text-field
					v-model="empresaForm.logradouro"
					label="Logradouro"
					counter
					maxlength="64"
				/>
			</v-col>
			<v-col cols="6">
				<v-text-field
					v-model="empresaForm.municipio"
					label="Municipio"
					counter
					maxlength="64"
				/>
			</v-col>
		</v-row>
		<v-row>
			<v-col cols="6">
				<v-text-field
					v-model="empresaForm.numero"
					:rules="[rules.onlyNumbers]"
					label="Numero"
					counter
					maxlength="32"
				/>
			</v-col>
			<v-col cols="6">
				<v-text-field
					v-model="empresaForm.complemento"
					label="Complemento"
					counter
					maxlength="64"
				/>
			</v-col>
		</v-row>
		<v-row>
			<v-col cols="6">
				<v-text-field
					v-model="empresaForm.bairro"
					label="Bairro"
					counter
					maxlength="64"
				/>
			</v-col>
			<v-col cols="6">
				<v-text-field
					v-model="empresaForm.cep"
					:rules="[rules.onlyNumbers]"
					label="CEP"
					counter
					maxlength="16"
				/>
			</v-col>
		</v-row>
	</v-card>
</template>

<script>
	export default {
		name: 'EmpresaInputCep',
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
