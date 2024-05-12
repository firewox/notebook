// Imports
import Vue from 'vue'
import Router from 'vue-router'
import { trailingSlash } from '@/util/helpers'
import {
  layout,
  route,
} from '@/util/routes'
import login from '../views/Login'
import register from '../views/Register'
import editorUsername from '../views/EditorUsername'
import editorPassword from '../views/EditorPassword'
import deleteAccount from '../views/DeleteAccount'
import BGImage from '../views/BGImage'
import contact from '../views/Contact'
import terms from '../views/Terms'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior: (to, from, savedPosition) => {
    if (to.hash) return { selector: to.hash }
    if (savedPosition) return savedPosition

    return { x: 0, y: 0 }
  },
  routes: [
    {
      path: '/components/login',
      name: 'login',
      component: login,
    },
    {
      path: '/components/register',
      name: 'register',
      component: register,
    },
    {
      path: '/components/editorUsername',
      name: 'editorUsername',
      component: editorUsername,
    },
    {
      path: '/components/editorPassword',
      name: 'editorPassword',
      component: editorPassword,
    },
    {
      path: '/components/deleteAccount',
      name: 'deleteAccount',
      component: deleteAccount,
    },
    {
      path: '/components/contact',
      name: 'contact',
      component: contact,
    },
    {
      path: '/components/terms',
      name: 'terms',
      component: terms,
    },
    layout('Default', [
      // Category
      route('Category', null, 'components/category'),

      // label
      route('Label', null, 'components/label'),

      // trashStation
      route('TrashStation', null, 'components/trashStation/'),

      // ebbinghaus
      route('Ebbinghaus', null, 'components/ebbinghaus/'),

      // 个人介绍
      route('UserProfile', null, 'components/userProfile'),

      // TODO 待续...
      route('BGImage', null, '/'),

    ]),
  ],
})

router.beforeEach((to, from, next) => {
  // alert('router/index.js===' + to.path)
  return to.path.endsWith('/') ? next() : next(trailingSlash(to.path))
})

export default router
