import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
	{
		path: '/',
		name: 'Home',
		component: () => import('../views/home/Home.vue'),
	},
	{
		path: '/empresas',
		name: 'Empresa',
		component: () => import('../views/empresa/Empresa.vue'),
	},
	{
		path: '/empresa/:id',
		name: 'EmpresaItem',
		component: () => import('../views/empresa/EmpresaItem.vue'),
	},
	{
		path: '/leiloes',
		name: 'Leilao',
		component: () => import('../views/leilao/Leilao.vue'),
	},
	{
		path: '/unidades',
		name: 'Unidade',
		component: () => import('../views/unidade/Unidade.vue'),
	},
];

const router = new VueRouter({
	routes,
});

export default router;
