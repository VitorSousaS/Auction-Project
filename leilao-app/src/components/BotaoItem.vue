<template>
	<v-btn
		class=""
		:to="where"
	>
		<v-icon>{{ icon }}</v-icon>
		{{ text }}
	</v-btn>
</template>

<script>
	export default {
		name: 'BotaoItem',
		props: ['icon', 'text', 'where'],
	};
</script>
