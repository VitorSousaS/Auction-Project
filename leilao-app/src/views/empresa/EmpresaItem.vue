<template>
	<v-card flat>
		<Snackbar
			cor="success"
			:texto="mensagemEmpresa"
			icon="mdi-checkbox-marked-circle"
			:ativo="snackbar"
		/>
		<TituloPagina :titulo="tituloEmpresa" />
		<v-form
			ref="form"
			@submit.prevent="submit"
		>
			<v-container fluid>
				<v-row>
					<CampoEntrada
						:vModel="form.razaoSocial"
						:rules="[rules.required, rules.counter64]"
						color="blue darken-2"
						label="Razão Social"
						@updateField="updateField('razaoSocial', $event)"
					/>
					<CampoEntrada
						:vModel="form.cnpj"
						:rules="[rules.required, rules.counter32]"
						color="blue darken-2"
						label="CNPJ"
						type="number"
						@updateField="updateField('cnpj', $event)"
					/>
					<CampoEntrada
						:vModel="form.logradouro"
						:rules="[rules.counter64]"
						color="blue darken-2"
						label="Logradouro"
						@updateField="updateField('logradouro', $event)"
					/>
					<CampoEntrada
						:vModel="form.municipio"
						:rules="[rules.counter64]"
						color="blue darken-2"
						label="Município"
						@updateField="updateField('municipio', $event)"
					/>
					<CampoEntrada
						:vModel="form.numero"
						:rules="[rules.counter10]"
						color="blue darken-2"
						label="Número"
						type="number"
						@updateField="updateField('numero', $event)"
					/>
					<CampoEntrada
						:vModel="form.complemento"
						:rules="[rules.counter64]"
						color="blue darken-2"
						label="Complemento"
						@updateField="updateField('complemento', $event)"
					/>
					<CampoEntrada
						:vModel="form.bairro"
						:rules="[rules.counter64]"
						color="blue darken-2"
						label="Bairro"
						@updateField="updateField('bairro', $event)"
					/>
					<CampoEntrada
						:vModel="form.cep"
						:rules="[rules.counter16]"
						color="blue darken-2"
						label="CEP"
						type="number"
						@updateField="updateField('cep', $event)"
					/>
					<CampoEntrada
						:vModel="form.telefone"
						:rules="[rules.counter32]"
						color="blue darken-2"
						label="Telefone"
						type="number"
						@updateField="updateField('telefone', $event)"
					/>
					<CampoEntrada
						:vModel="form.email"
						:rules="[rules.required, rules.counter254, rules.email]"
						color="blue darken-2"
						label="E-mail"
						@updateField="updateField('email', $event)"
					/>
					<CampoEntrada
						:vModel="form.site"
						:rules="[rules.counter254]"
						color="blue darken-2"
						label="Site"
						@updateField="updateField('site', $event)"
					/>
					<CampoEntrada
						:vModel="form.usuario"
						:rules="[rules.required, rules.counter20]"
						color="blue darken-2"
						label="Usuário"
						@updateField="updateField('usuario', $event)"
					/>
					<CampoEntrada
						:vModel="form.senha"
						:rules="[rules.counter128]"
						color="blue darken-2"
						label="Senha"
						sm="12"
						@updateField="updateField('senha', $event)"
					/>
					<CampoEntrada
						:vModel="form.createdAt"
						solo
						disabled
						label="Criado"
						@updateField="updateField('createdAt', $event)"
					/>
					<CampoEntrada
						:vModel="form.updatedAt"
						solo
						disabled
						label="Atualizado"
						@updateField="updateField('updatedAt', $event)"
					/>
				</v-row>
			</v-container>
			<v-card-actions>
				<BotaoIcone
					text
					@botaoClicado="resetForm"
					icon="mdi-reload"
					phrase="Recarregar"
				/>
				<BotaoIcone
					text
					where="/empresas"
					icon="mdi-arrow-left"
					phrase="Voltar"
				/>
				<v-spacer></v-spacer>
				<BotaoIcone
					text
					:disabled="!formIsValid"
					color="primary"
					type="submit"
					:icon="
						this.$route.params.id > 0
							? 'mdi-content-save-edit'
							: 'mdi-content-save'
					"
					phrase="Salvar"
				/>
			</v-card-actions>
		</v-form>
	</v-card>
</template>

<script>
	import axios from 'axios';
	import Snackbar from '../../components/Snackbar.vue';
	import TituloPagina from '../../components/TituloPagina.vue';
	import CampoEntrada from '../../components/CampoEntrada.vue';
	import BotaoIcone from '../../components/BotaoIcone.vue';

	export default {
		name: 'EmpresaItem',
		data() {
			const defaultForm = Object.freeze({
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
			});
			return {
				responseData: null,
				responseSavedData: null,
				form: Object.assign({}, defaultForm),
				rules: {
					required: value => !!value || 'Campo obrigatório.',
					counter10: value =>
						value === undefined ||
						(value.length >= 0 && value.length <= 10) ||
						'Máximo de 10 caracteres.',
					counter16: value =>
						value === undefined ||
						(value.length >= 0 && value.length <= 16) ||
						'Máximo de 16 caracteres.',
					counter32: value =>
						value === undefined ||
						(value.length >= 0 && value.length <= 32) ||
						'Máximo de 32 caracteres.',
					counter64: value =>
						value === undefined ||
						(value.length >= 0 && value.length <= 64) ||
						'Máximo de 64 caracteres.',
					counter128: value =>
						value === undefined ||
						(value.length >= 0 && value.length <= 128) ||
						'Máximo de 128 caracteres.',
					counter254: value =>
						value === undefined ||
						(value.length >= 0 && value.length <= 254) ||
						'Máximo de 254 caracteres.',
					email: value => {
						const pattern =
							/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
						return pattern.test(value) || 'E-mail inválido.';
					},
				},

				snackbar: false,
			};
		},
		computed: {
			formIsValid() {
				return (
					this.form.razaoSocial &&
					this.form.cnpj &&
					this.form.email &&
					this.form.usuario
				);
			},
		},
		mounted() {
			if (this.$route.params.id > 0) this.fetchData();
		},
		methods: {
			async fetchData() {
				try {
					const response = await axios.get(
						'http://localhost:8081/empresa/' + this.$route.params.id
					);
					this.responseData = response.data;

					this.form = {
						razaoSocial: this.responseData.razaoSocial,
						cnpj: this.responseData.cnpj,
						logradouro: this.responseData.logradouro,
						municipio: this.responseData.municipio,
						numero: this.responseData.numero,
						complemento: this.responseData.complemento,
						bairro: this.responseData.bairro,
						cep: this.responseData.cep,
						telefone: this.responseData.telefone,
						email: this.responseData.email,
						site: this.responseData.site,
						usuario: this.responseData.usuario,
						senha: this.responseData.senha,
						createdAt: this.responseData.createdAt,
						updatedAt: this.responseData.updatedAt,
					};
				} catch (error) {
					console.error('Erro ao buscar dados da API:', error);
				}
			},
			async saveData() {
				const requestData = {
					razaoSocial: this.form.razaoSocial,
					cnpj: this.form.cnpj,
					logradouro: this.form.logradouro,
					municipio: this.form.municipio,
					numero: this.form.numero,
					complemento: this.form.complemento,
					bairro: this.form.bairro,
					cep: this.form.cep,
					telefone: this.form.telefone,
					email: this.form.email,
					site: this.form.site,
					usuario: this.form.usuario,
					senha: this.form.senha,
					createdAt: this.form.createdAt,
					updatedAt: this.form.updatedAt,
				};

				if (this.$route.params.id > 0) {
					try {
						const responseSaved = await axios.put(
							'http://localhost:8081/empresa/' + this.$route.params.id,
							requestData
						);
						this.responseSavedData = responseSaved.data;
					} catch (error) {
						console.error('Erro ao atualizar dados:', error);
					}
				} else {
					try {
						const responseSaved = await axios.post(
							'http://localhost:8081/empresa/',
							requestData
						);
						this.responseSavedData = responseSaved.data;
					} catch (error) {
						console.error('Erro ao cadastrar dados:', error);
					}
				}
			},
			resetForm() {
				if (this.$route.params.id > 0) {
					this.form = {
						razaoSocial: this.responseData.razaoSocial,
						cnpj: this.responseData.cnpj,
						logradouro: this.responseData.logradouro,
						municipio: this.responseData.municipio,
						numero: this.responseData.numero,
						complemento: this.responseData.complemento,
						bairro: this.responseData.bairro,
						cep: this.responseData.cep,
						telefone: this.responseData.telefone,
						email: this.responseData.email,
						site: this.responseData.site,
						usuario: this.responseData.usuario,
						senha: this.responseData.senha,
						createdAt: this.responseData.createdAt,
						updatedAt: this.responseData.updatedAt,
					};
				} else {
					this.form = {
						razaoSocial: '',
						cnpj: '',
						logradouro: '',
						municipio: '',
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
					};
				}
			},
			async submit() {
				this.snackbar = true;
				await this.saveData();
				if (this.$route.params.id == 0)
					this.$router.push('/empresa/' + this.responseSavedData.id);
				setTimeout(() => {
					this.snackbar = false;
				}, 2000);
			},
			updateField(fieldName, value) {
				this.form[fieldName] = value;
			},
		},
		created() {
			this.mensagemEmpresa = `Empresa ${
				this.$route.params.id > 0 ? 'editada' : 'cadastrada'
			} com sucesso!`;
			this.tituloEmpresa = `${
				this.$route.params.id > 0 ? 'Edite' : 'Cadastre'
			} a Empresa`;
		},
		components: {
			Snackbar,
			TituloPagina,
			CampoEntrada,
			BotaoIcone,
		},
	};
</script>
