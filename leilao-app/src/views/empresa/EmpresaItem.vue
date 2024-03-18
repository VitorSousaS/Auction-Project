<template>
	<div class="style-page">
		<p class="topic-header">
			{{
				this.$route.params.id > 0
					? `Editar Unidade ${empresaForm.razaoSocial}`
					: 'Cadastrar Unidade'
			}}
		</p>
		<v-form
			ref="form"
			@submit.prevent="submit"
		>
			<empresa-input-info :empresaForm="empresaForm" />
			<v-divider class="divider-style" />
			<empresa-input-cep :empresaForm="empresaForm" />
			<v-divider class="divider-style" />
			<empresa-input-contato :empresaForm="empresaForm" />
			<v-divider class="divider-style" />
			<empresa-input-login :empresaForm="empresaForm" />
			<v-divider class="divider-style" />

			<v-row>
				<v-col
					cols="12"
					class="d-flex col-btn-style"
				>
					<v-btn type="submit">
						<v-icon>{{
							this.$route.params.id > 0
								? 'mdi-content-save-edit'
								: 'mdi-content-save'
						}}</v-icon>
						Salvar
					</v-btn>
					<v-snackbar v-model="snackbar">
						{{ text }}

						<template v-slot:action="{ attrs }">
							<v-btn
								:color="color"
								text
								v-bind="attrs"
								@click="snackbar = false"
							>
								Close
							</v-btn>
						</template>
					</v-snackbar>
				</v-col>
			</v-row>
		</v-form>
	</div>
</template>

<script>
	import { mapState } from 'vuex';
	import EmpresaInputInfo from './components/EmpresaInputInfo.vue';
	import EmpresaInputCep from './components/EmpresaInputCep.vue';
	import EmpresaInputContato from './components/EmpresaInputContato.vue';
	import EmpresaInputLogin from './components/EmpresaInputLogin.vue';

	export default {
		name: 'EmpresaItem',
		data: () => ({
			snackbar: false,
			text: '',
			color: '',
			empresaForm: {
				razaoSocial: '',
				cnpj: '',
				logradouro: '',
				minicipio: '',
				numero: '',
				complemento: '',
				bairro: '',
				cep: '',
				telefone: '',
				email: '',
				site: '',
				usuario: '',
				senha: '',
				createdAt: '',
				updatedAt: '',
			},
		}),
		computed: {
			...mapState(['empresaItemData']),
		},
		methods: {
			async fetchData() {
				await this.$store.dispatch('fetchDataById', {
					endpoint: 'empresa',
					id: this.$route.params.id,
				});
			},
			async submit() {
				try {
					if (this.$route.params.id > 0) {
						await this.$store.dispatch('updateData', {
							endpoint: 'empresa',
							id: this.$route.params.id,
							corpo: this.empresaForm,
						});
						this.text = 'Sucesso ao editar';
					} else {
						const response = await this.$store.dispatch('saveNewData', {
							endpoint: 'empresa',
							corpo: this.empresaForm,
						});
						console.log('response: ', response);
						this.$router.push(`/empresa/${response.data.id}`);
						this.text = 'Sucesso ao cadastrar';
					}
					this.color = 'green';
					this.snackbar = true;
				} catch (error) {
					this.text = 'Requisição falhou, revise os dados';
					this.color = 'red';
					(this.snackbar = true), console.log('Eror: ' + error);
				}
			},
		},
		async created() {
			if (this.$route.params.id > 0) {
				await this.fetchData();
				this.empresaForm = this.empresaItemData;
				console.log('this.empresaForm: ', this.empresaForm);
			}
		},
		components: {
			EmpresaInputInfo,
			EmpresaInputCep,
			EmpresaInputContato,
			EmpresaInputLogin,
		},
	};
</script>

<style>
	.style-page {
		background-color: #d9d9d9;
		height: 100vh;
	}

	.topic-header {
		font-size: 28px;
		font-weight: bold;
		text-align: center;
		margin-bottom: 20px;
		color: #5e2129
	}

	.divider-style {
		margin: 10px 0px
	}

	.col-btn-style {
		justify-content: end;
		padding-right: 20px
	}
</style>
