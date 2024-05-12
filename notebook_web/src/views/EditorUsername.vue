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
              <h1 style="text-align: center;">修改昵称</h1>
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
                <v-text-field
                  v-model="username"
                  :rules="usernameRules"
                  type="text"
                  label="新昵称"
                  required
                ></v-text-field>
                <div style="height: 20px"></div>
                <el-button
                  type="primary"
                  :disabled="!valid"
                  @click="validate"
                >修改</el-button>

                <el-button
                  type="info"
                  @click="reset"
                >重置</el-button>

                <el-button
                  type="info"
                  @click="fun1"
                >关闭</el-button>
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
    name: 'EditorUsername',
    data: () => ({
      username: '',
      usernameRules: [
        v => !!v || '新昵称不能为空！',
      ],
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
          this.$http.post('/modify/modifyUsername.do', {
            email: _this.email,
            password: _this.password,
            username: _this.username,
          }).then(function (response) {
            if (response.data.flag) {
              _this.$that.user.username = _this.username
              _this.$message({
                message: response.data.errorMsg,
                type: 'success',
              })
              _this.$router.push({ path: '/components/userProfile' }).catch(err => { err = null })
            }
          })
        }
      },
      reset () {
        this.$refs.form.reset()
      },
      fun1: function () {
        this.$router.push({ path: '/' }).catch(err => { err = null })
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
  #appL{
    background: url(../assets/register.jpg) no-repeat;
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
