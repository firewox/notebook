<template>
  <el-container>
    <el-aside width="260px">
      <v-card
        class="mx-auto"
      >
        <v-toolbar
          color="teal"
          dark
        >
          <input
            type="button"
            value="新建标签"
            @click="dialog2 = true"
          />
          <v-spacer></v-spacer>

        </v-toolbar>

        <v-list>
          <v-list-item
            v-for="(l,lIndex) in labels"
            :key="l.label_name"
            :prepend-icon="l.action"
            no-action
          >
            <v-list-item-content>
              <a
                @click="fun_tl_2(lIndex)"
              ><v-list-item-title v-text="l.label_name"></v-list-item-title></a>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-card>
    </el-aside>
    <el-main>
      <el-row>
        <si></si>
      </el-row>
    </el-main>
    <!--新建标签对话框-->
    <v-dialog
      v-model="dialog2"
      max-width="500"
    >
      <v-card>
        <v-card-title>
          <v-spacer />
          <v-icon
            aria-label="Close"
            @click="dialog2 = false"
          >
            mdi-close
          </v-icon>
        </v-card-title>

        <v-card-text class="text-body-1 text-center">
          <v-row>
            <v-col
              cols="12"
              md="10"
            >
              <el-form
                label-position="right"
                label-width="80px"
              >
                <el-form-item label="新标签:">
                  <el-input
                    placeholder="请输入新标签的名称"
                    v-model="newLabelName"
                    clearable
                  ></el-input></el-form-item>
              </el-form>
            </v-col>

          </v-row>

          <v-btn
            class="mt-6 primary"
            depressed
            default
            rounded
            @click="fun_tl_1"
          >
            保存
          </v-btn>
        </v-card-text>
      </v-card>
    </v-dialog>
    <!--保存标签名称 和 删除标签 对话框-->
    <v-dialog
      v-model="dialog3"
      max-width="500"
    >
      <v-card>
        <v-card-title>
          <v-spacer />
          <v-icon
            aria-label="Close"
            @click="dialog3 = false"
          >
            mdi-close
          </v-icon>
        </v-card-title>

        <v-card-text class="text-body-1 text-center">
          <v-row>
            <v-col
              cols="12"
              md="10"
            >
              <el-form
                label-position="right"
                label-width="80px"
              >
                <el-form-item label="标签名称:">
                  <el-input
                    placeholder="请输入修改后的标签名称"
                    v-model="newLabelName"
                    clearable
                  ></el-input></el-form-item>
              </el-form>
            </v-col>

          </v-row>

          <v-btn
            class="mt-6 primary"
            depressed
            default
            rounded
            @click="fun_tl_3"
          >
            保存名称
          </v-btn>
          <v-btn
            class="mt-6 primary"
            depressed
            default
            rounded
            @click="fun_tl_4"
          >
            删除标签
          </v-btn>
        </v-card-text>
      </v-card>
    </v-dialog>
  </el-container>
</template>
<script>
  import si from '../components2/SlotImage'
  export default {
    name: 'tl',
    data: () => ({
      dialog2: false,
      dialog3: false,
      labels: [
        {
          action: 'mdi-tag',
          lid: -1,
          uid: -1,
          label_name: '标签一',
        },
        {
          action: 'mdi-tag',
          lid: -2,
          uid: -1,
          label_name: '标签二',
        },
        {
          action: 'mdi-tag',
          lid: -3,
          uid: -1,
          label_name: '标签三',
        },
      ],
      lIndex: -1,
      newLabelName: '',
    }),
    methods: {
      fun_tl_1: function () {
        this.dialog2 = false
        var _this = this
        this.$http.post('/editor/createNewLabel.do', {
          userId: _this.$that.user.userId,
          newLabelName: _this.newLabelName,
        }).then(function (response) {
          var list = response.data
          _this.labels = []
          var i = 0
          for (i = 0; i < list.length; i++) {
            _this.labels.push({ action: 'mdi-tag', lid: list[i].labelId, uid: _this.$that.user.userId, label_name: list[i].labelName })
          }
        }).catch(function (error) {
          console.log(error)
        })
      },
      // TODOq 艾宾浩斯标签编辑对话框
      fun_tl_2: function (lIndex) {
        this.lIndex = lIndex
        this.newLabelName = this.labels[lIndex].label_name
        this.dialog3 = true
      },
      // TODOq 艾宾浩斯标签编辑——保存名称
      fun_tl_3: function () {
        this.dialog3 = false
        var _this = this
        this.$http.post('/editor/updateLabelName.do', {
          lid: _this.labels[_this.lIndex].lid,
          uid: _this.labels[_this.lIndex].uid,
          labelName: _this.newLabelName,
        }).then(function (response) {
          var list = response.data
          _this.labels = []
          var i = 0
          for (i = 0; i < list.length; i++) {
            _this.labels.push({ action: 'mdi-tag', lid: list[i].labelId, uid: _this.$that.user.userId, label_name: list[i].labelName })
          }
        }).catch(function (error) {
          console.log(error)
        })
      },
      // TODOq 艾宾浩斯标签编辑——删除标签
      fun_tl_4: function () {
        this.dialog3 = false
        var _this = this
        this.$http.post('/editor/deleteLabel.do', {
          lid: _this.labels[_this.lIndex].lid,
          uid: _this.labels[_this.lIndex].uid,
          labelName: _this.labels[_this.lIndex].label_name,
        }).then(function (response) {
          var list = response.data
          _this.labels = []
          var i = 0
          for (i = 0; i < list.length; i++) {
            _this.labels.push({ action: 'mdi-tag', lid: list[i].labelId, uid: _this.$that.user.userId, label_name: list[i].labelName })
          }
        }).catch(function (error) {
          console.log(error)
        })
      },
    },
    components: {
      si,
    },
    created () {
      var _this = this
      this.$http.post('/login/indexHtml.do', {
        email: _this.$that.user.email,
      }).then(function (response) {
        var listA = response.data.data.labels
        _this.labels = []
        var i = 0
        for (i = 0; i < listA.length; i++) {
          _this.labels.push({ action: 'mdi-tag', lid: listA[i].labelId, uid: response.data.data.userId, label_name: listA[i].labelName })
        }
      })
    },
  }
</script>

<style scoped>

</style>
