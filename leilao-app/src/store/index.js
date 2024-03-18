import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		empresaData: [],
		unidadeData: [],
		leilaoData: [],
		empresaItemData: {},
	},
	mutations: {
		setEmpresaData(state, list) {
			state.empresaData = list;
		},
		setUnidadeData(state, list) {
			state.unidadeData = list;
		},
		setLeilaoData(state, list) {
			state.leilaoData = list;
		},
		setEmpresaItemData(state, list) {
			state.empresaItemData = list;
		},
	},
	actions: {
		async fetchData(context, endpoint) {
			try {
				const response = await axios.get('http://localhost:8081/' + endpoint);
				const list = response.data;
				context.commit(
					'set' + endpoint.charAt(0).toUpperCase() + endpoint.slice(1) + 'Data',
					list
				);
			} catch (error) {
				console.error('Erro ao buscar dados da API:', error);
			}
		},
		async fetchDataById(context, { endpoint, id }) {
			try {
				const response = await axios.get(
					`http://localhost:8081/${endpoint}/${id}`
				);
				const list = response.data;
				context.commit(
					'set' +
						endpoint.charAt(0).toUpperCase() +
						endpoint.slice(1) +
						'ItemData',
					list
				);
			} catch (error) {
				console.error('Erro ao buscar dados da API:', error);
			}
		},
		async updateData(context, { endpoint, id, corpo }) {
			try {
				const response = await axios.put(
					`http://localhost:8081/${endpoint}/${id}`,
					corpo
				);
				return response;
			} catch (error) {
				console.error('Erro ao enviar requisição:', error);
			}
		},
		async saveNewData(context, { endpoint, corpo }) {
			try {
				const response = await axios.post(
					`http://localhost:8081/${endpoint}`,
					corpo
				);
				return response;
			} catch (error) {
				console.error('Erro ao enviar requisição:', error);
			}
		},
		async deleteData(context, { endpoint, id }) {
			try {
				const response = await axios.delete(
					`http://localhost:8081/${endpoint}/${id}`
				);
				return response;
			} catch (error) {
				console.error('Erro ao enviar requisição:', error);
			}
		},
	},
	getters: {
		empresaCounter(state) {
			return state.empresaData.length;
		},
		unidadeCounter(state) {
			return state.unidadeData.length;
		},
		leilaoTotalValue(state) {
			let total = 0;
			for (const leilao of state.leilaoData) {
				total += leilao.totalLeilao;
			}
			return parseFloat(total);
		},
	},
	modules: {},
});
