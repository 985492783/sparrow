import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '@/views/layout/index'
import LogGather from '@/views/log/index'
import ThreadPoolAgent from '@/views/threadPool/index'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/logGather',
    name: 'logGather',
    component: LogGather
  },
  {
    path: '/threadPoolAgent',
    name: 'threadPoolAgent',
    component: ThreadPoolAgent
  }
]

const router = new VueRouter({
  mode: 'hash',
  routes
})

export default router
