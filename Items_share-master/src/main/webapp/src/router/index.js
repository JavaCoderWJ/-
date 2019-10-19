import Vue from 'vue'
import Router from 'vue-router'
import Index from '../index/index'
import Article from '../article/article.vue'
import WritePage from '../writeArticle/writePage.vue'
import User from '../user/user'
import New from '../index/new.vue'
import Manage from '../manage/manage.vue'
Vue.use(Router)

export default new Router({
  routes: [{
      path: '/',
      component: Index
    },
    {
      path: '/article/:id',
      component: Article
    },
    {
      path: '/write',
      component: WritePage
    },
    {
      path: '/user',
      component: User
    },
    {
      path: '/newest',
      component: New
    },
    {
      path: '/recommend',
      component: New
    },
    {
      path: '/manage',
      component: Manage
    }
  ]
})
