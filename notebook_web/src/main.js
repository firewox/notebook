import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import './plugins'
import store from './store'
import { sync } from 'vuex-router-sync'
import CKEditor from 'ckeditor4-vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './http'
import ViewUI from 'view-design'
import 'view-design/dist/styles/iview.css'

Vue.use(ViewUI)

Vue.use(ElementUI)

Vue.use(CKEditor)

Vue.config.productionTip = false

sync(store, router)

new Vue({
  data: () => ({
    user: {
      userId: -1,
      username: '',
      email: '',
      gender: '',
      birthday: '',
      created: '',
      categoryId: [],
      notebookId: [],
      trashId: [],
    },
  }),
  router,
  vuetify,
  store,
  render: h => h(App),
  created () {
    Vue.prototype.$that = this
  },
  beforeCreate () {
    Vue.prototype.$bus = this
  },
  mounted () {
    var _this = this
      // TODOq 第一次登录时判断用户是否登录
    if (this.$route.path === '/components/contact/') {
      _this.$router.push({ path: '/components/contact' }).catch(err => { err = null })
    } else if (this.$route.path === '/components/terms/') {
      _this.$router.push({ path: '/components/terms' }).catch(err => { err = null })
    } else if (this.$route.path !== '/components/login') {
      var xEmail = JSON.parse(sessionStorage.getItem('email'))
      if (xEmail !== '') {
        this.user.email = xEmail
        // if (xEmail !== '') {
        //   this.user.email = xEmail
        // }
      }
      this.$http.post('/login/indexHtml.do', {
        email: _this.user.email,
      })
        .then(function (response) {
          if (!response.data.flag) {
            confirm(response.data.errorMsg)
            _this.$router.push({ path: '/components/login' }).catch(err => { err = null })
          } else {
            _this.user.username = response.data.data.username
            _this.user.userId = response.data.data.userId
            _this.user.email = response.data.data.email
            _this.user.gender = response.data.data.gender
            _this.user.birthday = dateFormat(response.data.data.birthday)
            _this.user.created = dateFormat(response.data.data.created)
          }
        })
        .catch(function (error) {
          console.log(error)
        })
    }
  },
}).$mount('#app')

// 日期格式转换函数
function dateFormat (longTypeDate) {
  var dateTypeDate = ''
  var date = new Date()
  date.setTime(longTypeDate)
  dateTypeDate += date.getFullYear() // 年
  dateTypeDate += '-' + getMonth(date) // 月
  dateTypeDate += '-' + getDay(date) // 日
  return dateTypeDate
}
// 返回 01-12 的月份值
function getMonth (date) {
  var month = ''
  month = date.getMonth() + 1 // getMonth()得到的月份是0-11
  if (month < 10) {
    month = '0' + month
  }
  return month
}
// 返回01-30的日期
function getDay (date) {
  var day = ''
  day = date.getDate()
  if (day < 10) {
    day = '0' + day
  }
  return day
}
