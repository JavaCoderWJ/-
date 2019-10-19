<!-- 主页 -->
<template>
	<div>
		<v-header></v-header>
		<v-main></v-main>
		<v-footer></v-footer>
		<v-aside></v-aside>
	</div>
</template>
<script>
import VHeader from './../components/header'
import VMain from './main'
import VFooter from './../components/footer'
import VAside from './../components/aside'
export default {
	components:{
		VHeader,
		VMain,
		VFooter,
		VAside
	}
}

</script>
<style lang="scss" scoped>

</style>
