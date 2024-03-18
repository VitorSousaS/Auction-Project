<template>
	<v-dialog
		v-model="dialog"
		max-width="500px"
	>
		<v-card>
			<v-card-title class="text-h5">Deletar {{ titulo }}?</v-card-title>
			<v-card-actions>
				<v-spacer></v-spacer>
				<v-btn
					color="blue darken-1"
					text
					@click="cancelDelete"
					>Cancel</v-btn
				>
				<v-btn
					color="blue darken-1"
					text
					@click="okDelete"
					>OK</v-btn
				>
				<v-spacer></v-spacer>
			</v-card-actions>
		</v-card>
	</v-dialog>
</template>

<script>
	export default {
		name: 'DialogDelete',
		props: ['titulo'],
		data: () => ({
			dialog: false,
		}),
		methods: {
			cancelDelete() {
				this.dialog = false;
				this.$emit('close');
			},
			okDelete() {
				this.dialog = false;
				this.$emit('deleteItem');
			},
			open() {
				this.dialog = true;
			},
		},
	};
</script>
