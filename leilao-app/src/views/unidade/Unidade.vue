<template>
	<v-data-table
		:headers="headers"
		:items="unidadeDataTimeFormat"
		sort-by="id"
		class="elevation-1"
	>
		<template v-slot:top>
			<v-toolbar flat>
				<v-toolbar-title>Unidades</v-toolbar-title>
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
						@click="openEditDialogNew"
					>
						<v-icon class="mr-2">mdi-plus-circle</v-icon>
						Nova Unidade
					</v-btn>
				</template>

				<unidade-dialog-edit
					:editedUnidade="editedUnidade"
					@save="saveEditDialog"
					@close="closeDialog"
					ref="dialogEdit"
				/>

				<dialog-delete
					:titulo="`Unidade '${editedUnidade.nome}'`"
					@deleteItem="deleteUnidade"
					@close="closeDialog"
					ref="dialogDelete"
				/>
			</v-toolbar>
		</template>
		<template v-slot:item.actions="{ item }">
			<v-icon
				small
				class="mr-2"
				@click="openEditDialog(item)"
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
	import UnidadeDialogEdit from './components/UnidadeDialogEdit';
	import DialogDelete from '../../components/DialogDelete';

	export default {
		data: () => ({
			corVinho: colors.vinho,
			corCinza: colors.cinza,
			corLaranja: colors.laranja,
			headers: [
				{ text: '#', value: 'id' },
				{ text: 'Nome', value: 'nome' },
				{ text: 'Atualizado', value: 'updatedAt' },
				{ text: 'Criado', value: 'createdAt' },
				{ text: 'Ações', value: 'actions', sortable: false },
			],
			editedId: -1,
			editedUnidade: {
				nome: '',
			},
			defaultItem: {
				nome: '',
			},
		}),

		computed: {
			...mapState(['unidadeData']),
			unidadeDataTimeFormat() {
				return this.unidadeData.map(unidade => ({
					...unidade,
					createdAt: unidade.createdAt.split('T')[0],
					updatedAt: unidade.updatedAt.split('T')[0],
				}));
			},
		},
		created() {
			this.fetchData();
		},
		methods: {
			async fetchData() {
				await this.$store.dispatch('fetchData', 'unidade');
			},

			openEditDialogNew() {
				this.$refs.dialogEdit.open();
				this.editedId = -1;
			},

			openEditDialog(item) {
				this.$refs.dialogEdit.open();
				this.editedId = item.id;
				this.editedUnidade = Object.assign({}, item);
			},

			async saveEditDialog() {
				try {
					if (this.editedId > -1) {
						const corpo = {
							nome: this.editedUnidade.nome,
						};
						await this.$store.dispatch('updateData', {
							endpoint: 'unidade',
							id: this.editedId,
							corpo: corpo,
						});
					} else {
						const corpo = {
							nome: this.editedUnidade.nome,
						};
						await this.$store.dispatch('saveNewData', {
							endpoint: 'unidade',
							corpo: corpo,
						});
					}
					await this.fetchData();
				} catch (error) {
					console.log('error: ', error);
				}
			},

			openDeleteDialog(item) {
				this.$refs.dialogDelete.open();
				this.editedId = item.id;
				this.editedUnidade = Object.assign({}, item);
			},

			closeDialog() {
				this.$nextTick(() => {
					this.editedUnidade = Object.assign({}, this.defaultItem);
					this.editedId = -1;
				});
			},

			async deleteUnidade() {
				try {
					await this.$store.dispatch('deleteData', {
						endpoint: 'unidade',
						id: this.editedId,
					});
					await this.fetchData();
				} catch (error) {
					console.log('error: ', error);
				}
			},
		},
		components: {
			UnidadeDialogEdit,
			DialogDelete,
		},
	};
</script>
