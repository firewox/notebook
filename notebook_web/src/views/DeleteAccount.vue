<template>
  <div id="appR">
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
              <h1 style="text-align: center;">注销账号</h1>
              <el-divider></el-divider>
              <v-form
                ref="form"
                v-model="valid"
                lazy-validation
              >
                <v-text-field
                  v-model="user.email"
                  :rules="emailRules"
                  label="邮箱"
                  required
                ></v-text-field>

                <v-text-field
                  v-model="user.password"
                  :rules="passwordRules"
                  type="password"
                  label="密码"
                  required
                ></v-text-field>
                <v-text-field
                  v-model="user.confirmedPassword"
                  type="password"
                  :error-messages="confirmedPasswordRules(user.confirmedPassword)"
                  label="确认密码"
                  required
                ></v-text-field>
                <v-row>
                  <v-col>
                    <v-text-field
                      v-model="user.verificationCode"
                      type="text"
                      :error-messages="verificationRules(user.verificationCode)"
                      label="验证码"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col>
                    <v-btn
                      class="ma-2"
                      outlined
                      color="indigo"
                      @click="fun_verification"
                    >
                      发送邮箱验证码
                    </v-btn>
                  </v-col>
                </v-row>
                <div style="height: 20px"></div>
                <el-button
                  type="primary"
                  :disabled="!valid"
                  @click="validate"
                >注销</el-button>

                <el-button
                  type="info"
                  @click="reset"
                >重置</el-button>
                <el-button
                  type="info"
                  @click="fun1"
                >退出</el-button>

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
    name: 'DeleteAccount',
    data: () => ({
      user: {
        email: '',
        password: '',
        confirmedPassword: '',
        verificationCode: '',
      },
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
          this.$http.post('/modify/deleteAccount.do', {
            email: _this.user.email,
            password: _this.user.confirmedPassword,
            verificationCode: _this.user.verificationCode,
          }).then(function (response) {
            if (response.data.flag) {
              _this.$message({
                message: response.data.errorMsg,
                type: 'success',
              })
              sessionStorage.removeItem('email')
              _this.$router.push({ path: '/components/login' }).catch(err => { err = null })
            } else {
              _this.$message({
                message: response.data.errorMsg,
                type: 'warning',
              })
            }
          }).catch(function (e) {
            console.log(e)
          })
        }
      },
      reset () {
        this.$refs.form.reset()
      },
      confirmedPasswordRules: function (v) {
        if (v.length === 0 && this.user.password.length !== 0) {
          return '密码不能为空！'
        } else if (v !== this.user.password) {
          return '两次的密码不一致！'
        } else {
          return ''
        }
      },
      verificationRules: function (v) {
        if (v.length === 0 && this.user.confirmedPassword.length !== 0) {
          return '验证码不能为空！'
        }
      },
      fun1: function () {
        this.$router.push({ path: '/' }).catch(err => { err = null })
      },
      fun_verification: function () {
        var _this = this
        this.$http.post('/register/checkEmailExist.do', {
          email: _this.user.email,
          deleteAccount: true,
        }).then(function (response) {
          if (response.data.flag) {
            _this.$message({
              message: response.data.errorMsg,
              type: 'success',
            })
          } else {
            _this.$message({
              message: response.data.errorMsg,
              type: 'warning',
            })
          }
        }).catch(function (e) {
          console.log(e)
        })
      },
    },
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
  #appR{
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
