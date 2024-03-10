<template>
	<v-card flat>
		<v-snackbar v-model="snackbar" absolute top right color="success">
			<span>Empresa cadastrada com sucesso!</span>
			<v-icon dark>
				mdi-checkbox-marked-circle
			</v-icon>
		</v-snackbar>
		<v-form ref="form" @submit.prevent="submit">
			<v-container fluid>
				<v-row>
					<v-col cols="12" sm="6">
						<v-text-field v-model="form.razaoSocial" :rules="[rules.required, rules.counter64]" color="blue darken-2"
							label="Razão Social" counter maxlength="64"></v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field v-model="form.cnpj" :rules="[rules.required, rules.counter32, rules.onlyNumber]"
							color="blue darken-2" label="CNPJ" counter maxlength="32"></v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field v-model="form.logradouro" :rules="[rules.counter64]" color="blue darken-2" label="Logradouro"
							counter maxlength="64"></v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field v-model="form.municipio" :rules="[rules.counter64]" color="blue darken-2" label="Município"
							counter maxlength="64"></v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field v-model="form.numero" :rules="[rules.counter10, rules.onlyNumber]" color="blue darken-2"
							label="Número" counter maxlength="10"></v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field v-model="form.complemento" :rules="[rules.counter64]" color="blue darken-2"
							label="Complemento" counter maxlength="64"></v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field v-model="form.bairro" :rules="[rules.counter64]" color="blue darken-2" label="Bairro" counter
							maxlength="64"></v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field v-model="form.cep" :rules="[rules.counter16, rules.onlyNumber]" color="blue darken-2"
							label="CEP" counter maxlength="16"></v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field v-model="form.telefone" :rules="[rules.counter32, rules.onlyNumber]" color="blue darken-2"
							label="Telefone" counter maxlength="32"></v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field v-model="form.email" :rules="[rules.required, rules.counter254, rules.email]"
							color="blue darken-2" label="E-mail" counter maxlength="254"></v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field v-model="form.site" :rules="[rules.counter254]" color="blue darken-2" label="Site" counter
							maxlength="254"></v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field v-model="form.usuario" :rules="[rules.required, rules.counter20]" color="blue darken-2"
							label="Usuário" counter maxlength="20"></v-text-field>
					</v-col>
					<v-col cols="12" sm="12">
						<v-text-field v-model="form.senha" :rules="rules.varchar128" color="blue darken-2" label="Senha" counter
							maxlength="128"></v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field v-model="form.createdAt" label="Criado" solo disabled></v-text-field>
					</v-col>
					<v-col cols="12" sm="6">
						<v-text-field v-model="form.updatedAt" label="Atualizado" solo disabled></v-text-field>
					</v-col>
				</v-row>
			</v-container>
			<v-card-actions>
				<v-btn text @click="resetForm">
					<v-icon>mdi-reload</v-icon> Recarregar
				</v-btn>
				<v-btn text to="/empresas">
					<v-icon>mdi-arrow-left</v-icon> Voltar
				</v-btn>
				<v-spacer></v-spacer>
				<v-btn :disabled="!formIsValid" text color="primary" type="submit">
					<v-icon> {{ this.$route.params.id > 0 ? 'mdi-content-save-edit' : 'mdi-content-save' }} </v-icon>Salvar
				</v-btn>
			</v-card-actions>
		</v-form>
	</v-card>
</template>

<script>
import axios from 'axios';

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
		})
		return {
			responseData: null,
			responseSavedData: null,
			form: Object.assign({}, defaultForm),
			rules: {
				required: value => !!value || 'Obrigatório.',
				counter10: value => (value.length >= 0 && value.length <= 10) || 'Máximo de 10 caracteres',
				counter16: value => (value.length >= 0 && value.length <= 16) || 'Máximo de 16 caracteres',
				counter32: value => (value.length >= 0 && value.length <= 32) || 'Máximo de 32 caracteres',
				counter64: value => (value.length >= 0 && value.length <= 64) || 'Máximo de 64 caracteres',
				counter128: value => (value.length >= 0 && value.length <= 128) || 'Máximo de 128 caracteres',
				counter254: value => (value.length >= 0 && value.length <= 254) || 'Máximo de 254 caracteres',
				onlyNumber: value => {
					const pattern = /^[0-9]/
					return (value.length >= 0 && pattern.test(value)) || 'Apenas números.'
				},
				email: value => {
					const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
					return pattern.test(value) || 'Invalid e-mail.'
				},
			},
			snackbar: false
		};
	},
	computed: {
		formIsValid() {
			return (
				this.form.razaoSocial &&
				this.form.cnpj &&
				this.form.email &&
				this.form.usuario
			)
		},
	},
	mounted() {
		if (this.$route.params.id > 0)
			this.fetchData();
	},
	methods: {
		async fetchData() {
			try {
				const response = await axios.get('http://localhost:8081/empresa/' + this.$route.params.id);
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
					updatedAt: this.responseData.updatedAt
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
				updatedAt: this.form.updatedAt
			};

			if (this.$route.params.id > 0) {
				try {
					const responseSaved = await axios.put('http://localhost:8081/empresa/' + this.$route.params.id, requestData);
					this.responseSavedData = responseSaved.data;
					console.log('responseSavedData: ', this.responseSavedData);
				} catch (error) {
					console.error('Erro ao atualizar dados:', error);
				}
			} else {
				try {
					const responseSaved = await axios.post('http://localhost:8081/empresa/', requestData);
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
					updatedAt: this.responseData.updatedAt
				}
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
					updatedAt: ''
				}
			}
		},
		async submit() {
			this.snackbar = true;
			await this.saveData()
			if (this.$route.params.id == 0)
				this.$router.push('/empresa/' + this.responseSavedData.id);
		},
	}
};
</script>
