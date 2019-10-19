<template>
  <div>
  	<v-header></v-header>
  	<user-center></user-center>
  	<v-footer></v-footer>
  	<v-aside></v-aside>
  </div>
</template>
<script>
import VHeader from '../components/header'
import VFooter from '../components/footer'
import VAside from '../components/aside.vue'
import UserCenter from '../user/userCenter.vue'
export default {
  components: {
    VHeader,
    VFooter,
    VAside,
    UserCenter
  }
}

</script>
<style scoped>


</style>
