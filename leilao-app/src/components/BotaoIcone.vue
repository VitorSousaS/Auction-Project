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
		:hidden="hidden"
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
			'hidden'
		],
		methods: {
			handleButtonClick() {
				this.$emit('botaoClicado');
			},
		},
	};
</script>
