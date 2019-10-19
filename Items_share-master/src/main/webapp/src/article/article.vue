<template>
  <div>
    <v-header></v-header>
    <article-main></article-main>
    <v-footer></v-footer>
    <v-aside></v-aside>
  </div>
</template>
<script>
import VHeader from '../components/header'
import VFooter from '../components/footer'
import VAside from '../components/aside.vue'
import ArticleMain from './aritcleMain.vue'
export default {
  components: {
    VHeader,
    VFooter,
    VAside,
    ArticleMain
  }
}

</script>
<style scoped>


</style>
