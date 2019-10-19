<template>
	<div>
		<v-header></v-header>
		<user-header></user-header>
		<writer-area></writer-area>
		<v-footer></v-footer>
		<v-aside></v-aside>
	</div>
</template>
<script>
	import VHeader from '../components/header.vue'
	import VFooter from '../components/footer.vue'
	import VAside from '../components/aside.vue'
	import UserHeader from '../components/userHeader.vue'
	import WriterArea from './writeArea.vue'
	export default{
		components: {
			VHeader,
			VFooter,
			VAside,
			UserHeader,
			WriterArea
		}
	}
</script>
<style scoped>
	
</style>