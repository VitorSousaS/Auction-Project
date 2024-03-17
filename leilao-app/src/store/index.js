import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		empresaData: [],
		unidadeData: [],
		leilaoData: [],
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
		}
	},
	actions: {
		async fetchData(context, endpoint) {
			try {
				const response = await axios.get('http://localhost:8081/' + endpoint);
				const list = response.data;
				context.commit('set' + endpoint.charAt(0).toUpperCase() + endpoint.slice(1) + 'Data', list);
			} catch (error) {
				console.error('Erro ao buscar dados da API:', error);
			}
		}
	},
	getters: {
    empresaCounter (state) {
      return state.empresaData.length
    },
		unidadeCounter (state) {
      return state.unidadeData.length
    },
		leilaoTotalValue (state) {
			let total = 0;
			for (const leilao of state.leilaoData) {
				total += leilao.totalLeilao;
			}	
      return parseFloat(total);
    }
  },
	modules: {},
});
