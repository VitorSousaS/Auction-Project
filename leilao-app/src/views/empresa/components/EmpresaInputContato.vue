<template>
	<v-card
		cols="12"
		class="col-12 d-flex flex-column external-card"
	>
		<v-row>
			<v-col cols="6">
				<v-text-field
					v-model="empresaForm.telefone"
					:rules="[rules.onlyNumbers]"
					label="Telefone"
					counter
					maxlength="32"
				/>
			</v-col>
			<v-col cols="6">
				<v-text-field
					v-model="empresaForm.email"
					:rules="[rules.required, rules.onlyEmail]"
					label="E-mail"
					counter
					maxlength="254"
				/>
			</v-col>
		</v-row>
		<v-row>
			<v-col cols="12">
				<v-text-field
					v-model="empresaForm.site"
					:rules="[rules.onlySite]"
					label="Site"
					counter
					maxlength="254"
				/>
			</v-col>
		</v-row>
	</v-card>
</template>

<script>
	export default {
		name: 'EmpresaInputContato',
		props: ['empresaForm'],
		data: () => ({
			rules: {
				required: value => !!value || 'Este campo é obrigatório',
				onlyNumbers: value =>
					/^[0-9]+$/.test(value) || 'Este campo deve conter apenas números',
				onlyEmail: value => {
					const pattern =
						/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
					return pattern.test(value) || 'E-mail inválido.';
				},
				onlySite: value => {
					const pattern =
						/^(https?:\/\/)?(www\.)?([a-zA-Z0-9-]+\.){1,}([a-zA-Z]{2,})(\/\S*)?$/;
					return pattern.test(value) || 'Site inválido.';
				},
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
