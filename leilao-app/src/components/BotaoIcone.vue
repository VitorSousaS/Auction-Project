<template>
	<v-btn
		:elevation="elevation"
		:x-large="xLarge"
		:text="text"
		:to="where"
		:disabled="disabled"
		:color="color"
		@click="handleButtonClick"
		type="type"
		:class="classe"
	>
		<v-icon>{{ icon }}</v-icon>
		{{ phrase }}
	</v-btn>
</template>

<script>
	export default {
		name: 'BotaoIcone',
		props: [
			'icon',
			'text',
			'where',
			'elevation',
			'xLarge',
			'phrase',
			'disabled',
			'color',
			'type',
			'classe',
		],
		methods: {
			handleButtonClick() {
				this.$emit('botaoClicado');
			},
		},
	};
</script>
