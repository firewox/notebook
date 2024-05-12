<template>
  <div id="appL">
    <el-row
      type="flex"
      justify="center"
    >
      <!-- justify 对齐方式 -->
      <el-col
        :span="12"
        style="height:100px"
      >
      </el-col>
    </el-row>
    <el-row
      type="flex"
      justify="center"
    >
      <!-- justify 对齐方式 -->
      <el-col :span="12">
        <el-container>
          <el-main style="display:flex;justify-content:center;">
            <el-card
              class="el-card"
              shadow="always"
            >
              <h1 style="text-align: center;">欢迎登陆</h1>
              <el-divider></el-divider>
              <v-form
                ref="form"
                v-model="valid"
                lazy-validation
              >
                <v-text-field
                  v-model="email"
                  :rules="emailRules"
                  label="邮箱"
                  required
                ></v-text-field>

                <v-text-field
                  v-model="password"
                  :rules="passwordRules"
                  type="password"
                  label="密码"
                  required
                ></v-text-field>
                <div style="height: 20px"></div>
                <el-button
                  type="primary"
                  :disabled="!valid"
                  @click="validate"
                >登录</el-button>

                <el-button
                  type="info"
                  @click="reset"
                >重置</el-button>

                <el-button
                  type="info"
                  @click="fun1"
                >注册</el-button>
              </v-form>
            </el-card>
          </el-main>
        </el-container>
      </el-col>
    </el-row>
    <el-row
      type="flex"
      justify="center"
    >
      <!-- justify 对齐方式 -->
      <el-col
        :span="6"
        style="height:100px"
      >
      </el-col>
    </el-row>
    <el-row
      type="flex"
      justify="center"
    >
      <!-- justify 对齐方式 -->
      <el-col
        :span="6"
        style="height:100px"
      >
      </el-col>
    </el-row>
    <el-row
      type="flex"
      justify="center"
    >
      <!-- justify 对齐方式 -->
      <el-col
        :span="6"
        style="height:100px"
      >
      </el-col>
    </el-row>
    <el-row
      type="flex"
      justify="center"
    >
      <!-- justify 对齐方式 -->
      <el-col
        :span="6"
        style="height:100px"
      >
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: 'Login',
    data: () => ({
      valid: true,
      email: '',
      emailRules: [
        v => !!v || '邮箱不能为空！',
        v => /.+@.+\..+/.test(v) || '邮箱格式非法！',
      ],
      password: '',
      passwordRules: [
        v => !!v || '密码不能为空！',
      ],
    }),

    methods: {
      validate () {
        this.$refs.form.validate()
        if (this.$refs.form.validate()) {
          var _this = this
          this.$http.post('/login/loginUser.do', {
            email: _this.email,
            password: _this.password,
          }).then(function (response) {
            if (response.data.flag) {
              _this.$that.user.email = _this.email
              _this.$that.user.userId = response.data.data.userId
              _this.$that.user.username = response.data.data.username
              _this.$that.user.gender = response.data.data.gender
              _this.$that.user.birthday = dateFormat(response.data.data.birthday)
              _this.$that.user.created = dateFormat(response.data.data.created)
              sessionStorage.setItem('email', JSON.stringify(_this.$that.user.email))
              _this.$message({
                message: response.data.errorMsg,
                type: 'success',
              })
              _this.$router.push({ path: '/' }).catch(err => { err = null })
            } else {
              _this.$message({
                message: response.data.errorMsg,
                type: 'warning',
              })
            }
          })
        }
      },
      reset () {
        this.$refs.form.reset()
      },
      fun1: function () {
        this.$router.push({ path: '/components/register' }).catch(err => { err = null })
      },
    },
  }

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
</script>

<style lang="scss" scoped>
  #submit{
    color: #FFFFFF;
    background: rgb(24,103,192);
  }
  #reset{
    color: #FFFFFF;
    background: rgb(251,140,0);
  }
  #appL{
    background: url(../assets/login.jpg) no-repeat;
    background-size: cover;
    height: 100%;
  }
  .el-card{
    border-radius:30px;
    width: 580px;
    display:flex;
    justify-content:center;
  }
  .el-row {
    margin-bottom: 20px;
  }
  .v-form {
    width: 380px;
  }
</style>
