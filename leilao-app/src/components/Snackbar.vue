<template>
	<v-snackbar
		v-model="snackbarLocal"
		absolute
		top
		right
		:color="cor"
	>
		<span>{{ texto }}</span>
		<v-icon dark>{{ icon }}</v-icon>
	</v-snackbar>
</template>

<script>
	export default {
		name: 'Snackbar',
		props: ['cor', 'texto', 'icon', 'ativo'],
		data() {
			return {
				snackbarLocal: this.ativo,
			};
		},
		watch: {
			ativo(newAtivo) {
				this.snackbarLocal = newAtivo;
			},
		},
	};
</script>
