import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '@/views/layout/index'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  }
]

const router = new VueRouter({
  mode: 'hash',
  routes
})

export default router
