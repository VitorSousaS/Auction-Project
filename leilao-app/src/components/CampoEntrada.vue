<template>
	<v-col
		cols="12"
		:sm="columnSize"
	>
		<v-text-field
			v-model="internalValue"
			:rules="rules"
			:color="color"
			:label="label"
			:type="type"
			:solo="solo"
			:disabled="disabled"
			counter
			@input="updateField"
		/>
	</v-col>
</template>

<script>
	export default {
		name: 'CampoEntrada',
		props: [
			'vModel',
			'rules',
			'color',
			'label',
			'type',
			'solo',
			'disabled',
			'sm',
		],
		data() {
			return {
				internalValue: this.vModel,
			};
		},
		computed: {
			columnSize() {
				return this.sm ? this.sm : '6';
			},
		},
		watch: {
			vModel(newVal) {
				this.internalValue = newVal;
			},
			internalValue(newVal) {
				this.$emit('update:vModel', newVal);
			},
		},
		methods: {
			updateField() {
				this.$emit('updateField', this.internalValue);
			},
		},
	};
</script>
