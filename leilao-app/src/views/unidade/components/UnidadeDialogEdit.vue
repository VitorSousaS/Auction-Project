<template>
	<v-dialog
		v-model="dialog"
		max-width="500px"
	>
		<v-card>
			<v-card-title>
				<span class="text-h5">{{ formTitle }}</span>
			</v-card-title>

			<v-card-text>
				<v-container>
					<v-row>
						<v-col
							cols="12"
							sm="12"
							md="12"
						>
							<v-text-field
								v-model="editedUnidade.nome"
								label="Nome da Unidade"
								maxlength="128"
								counter
							></v-text-field>
						</v-col>
					</v-row>
				</v-container>
			</v-card-text>

			<v-card-actions>
				<v-spacer></v-spacer>
				<v-btn
					color="blue darken-1"
					text
					@click="close"
				>
					Cancel
				</v-btn>
				<v-btn
					color="blue darken-1"
					text
					@click="save"
				>
					Save
				</v-btn>
			</v-card-actions>
		</v-card>
	</v-dialog>
</template>

<script>
	export default {
		name: 'UnidadeDialogEdit',
		props: ['editedUnidade'],
		data: () => ({
			dialog: false,
		}),
		methods: {
			save() {
				this.dialog = false;
				this.$emit('save');
			},
			close() {
				this.dialog = false;
				this.$emit('close');
			},
			open() {
				this.dialog = true;
			},
		},
		computed: {
			formTitle() {
				return this.editedId === -1 ? 'Nova Unidade' : 'Editar Unidade';
			},
		},
	};
</script>
