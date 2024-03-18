<template>
	<v-data-table
		:headers="headers"
		:items="empresaData"
		sort-by="id"
		class="elevation-1"
	>
		<template v-slot:item.cnpj="{ item }">
			{{ empresaDataCNPJFormat(item.cnpj) }} </template
		><template v-slot:item.telefone="{ item }">
			{{ empresaDataPhoneFormat(item.telefone) }}
		</template>
		<template v-slot:top>
			<v-toolbar flat>
				<v-toolbar-title>Empresas</v-toolbar-title>
				<v-divider
					class="mx-4"
					inset
					vertical
				></v-divider>
				<v-spacer></v-spacer>
				<template>
					<v-btn
						color="primary"
						dark
						class="mb-2"
						to="/empresa/0"
					>
						<v-icon class="mr-2">mdi-plus-circle</v-icon>
						Nova Empresa
					</v-btn>
				</template>

				<dialog-delete
					:titulo="`Empresa '${deletedEmpresa.razaoSocial}'`"
					@deleteItem="deleteEmpresa"
					@close="closeDialog"
					ref="dialogDelete"
				/>
			</v-toolbar>
		</template>
		<template v-slot:item.actions="{ item }">
			<v-icon
				small
				class="mr-2"
				@click="navigateToEmpresaItem(item.id)"
			>
				mdi-pencil
			</v-icon>
			<v-icon
				small
				@click="openDeleteDialog(item)"
			>
				mdi-delete
			</v-icon>
		</template>
	</v-data-table>
</template>

<script>
	import { mapState } from 'vuex';
	import colors from '../../assets/colors.js';
	import DialogDelete from '../../components/DialogDelete';

	export default {
		data: () => ({
			corVinho: colors.vinho,
			corCinza: colors.cinza,
			corLaranja: colors.laranja,
			headers: [
				{ text: 'CNPJ', value: 'cnpj' },
				{ text: 'Razão Social', value: 'razaoSocial' },
				{ text: 'Telefone ', value: 'telefone' },
				{ text: 'E-mail', value: 'email' },
				{ text: 'Ações', value: 'actions', sortable: false },
			],
			deletedId: -1,
			deletedEmpresa: {
				razaoSocial: '',
			},
		}),

		computed: {
			...mapState(['empresaData']),
		},
		created() {
			this.fetchData();
		},
		methods: {
			navigateToEmpresaItem(id) {
				this.$router.push(`/empresa/${id}`);
			},

			async fetchData() {
				await this.$store.dispatch('fetchData', 'empresa');
			},

			empresaDataCNPJFormat(cnpj) {
				const cnpjFormatado = cnpj.replace(
					/^(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/,
					'$1.$2.$3/$4-$5'
				);
				return cnpjFormatado;
			},

			empresaDataPhoneFormat(numero) {
				const numeroFormatado = numero.replace(
					/^(\d{2})(\d{4})(\d{4})$/,
					'($1) $2-$3'
				);
				return numeroFormatado;
			},

			openDeleteDialog(item) {
				this.$refs.dialogDelete.open();
				this.deletedId = item.id;
				this.deletedEmpresa = Object.assign({}, item);
			},

			closeDialog() {
				this.deletedId = -1;
			},

			async deleteEmpresa() {
				try {
					await this.$store.dispatch('deleteData', {
						endpoint: 'empresa',
						id: this.deletedId,
					});
					await this.fetchData();
				} catch (error) {
					console.log('error: ', error);
				}
			},
		},
		components: {
			DialogDelete,
		},
	};
</script>
