<template>
	<v-app id="inspire">
		<v-navigation-drawer
			:color="corVinho"
			v-model="drawer"
			app
		>
			<v-list-item>
				<v-list-item-content>
					<div class="drawer-header">
						<v-img
							max-width="32"
							src="./assets/logoDrawer.svg"
						></v-img>
						<v-list-item-title class="text-h6 gray-text-color">
							Bem Vindo
						</v-list-item-title>
					</div>
				</v-list-item-content>
			</v-list-item>

			<v-divider></v-divider>

			<v-list
				dense
				nav
			>
				<v-list-item
					v-for="item in items"
					:key="item.title"
					link
					:to="item.to"
					v-on:mouseenter="item.hover = true"
					v-on:mouseleave="item.hover = false"
				>
					<v-list-item-icon>
						<v-icon :color="item.hover ? corLaranja : corCinza">{{
							item.icon
						}}</v-icon>
					</v-list-item-icon>
					<v-list-item-content>
						<v-list-item-title
							:style="{ color: item.hover ? corLaranja : corCinza }"
							>{{ item.title }}</v-list-item-title
						>
					</v-list-item-content>
				</v-list-item>
			</v-list>

			<template v-slot:append>
				<div class="pa-2">
					<v-divider></v-divider>
					<div class="drawer-text-footer">©LeilaoHouse</div>
				</div>
			</template>
		</v-navigation-drawer>

		<v-app-bar
			app
			:color="corVinho"
		>
			<v-app-bar-nav-icon
				:color="corCinza"
				@click="drawer = !drawer"
			></v-app-bar-nav-icon>

			<v-toolbar-title class="gray-text-color">Leilão House</v-toolbar-title>
		</v-app-bar>

		<v-main>
			<router-view />
		</v-main>

		<!-- <v-footer
			:color="corVinho"
			padless
		>
			<v-col
				class="text-center"
				cols="12"
			>
				{{ new Date().getFullYear() }} — <strong>Leilão House</strong> —
				Iniciativa em prol do desenvolvimento.
			</v-col>
		</v-footer> -->
	</v-app>
</template>

<script>
	import colors from './assets/colors.js';

	export default {
		name: 'App',
		data() {
			return {
				corVinho: colors.vinho,
				corCinza: colors.cinza,
				corLaranja: colors.laranja,
				items: [
					{ title: 'Home', icon: 'mdi-home', to: '/' },
					{ title: 'Unidades', icon: 'mdi-domain', to: '/unidades' },
					{ title: 'Empresas', icon: 'mdi-tie', to: '/empresas' },
					{ title: 'Leilões', icon: 'mdi-curtains', to: '/leiloes' },
				],
				drawer: null,
			};
		},
	};
</script>

<style>
	.drawer-header {
		display: flex;
		align-items: center;
		gap: 24px;
		margin-bottom: 8px;
	}

	.drawer-text-footer {
		font-weight: bold;
		text-align: center;
	}

	.gray-text-color {
		color: #b0bec5;
	}
</style>
