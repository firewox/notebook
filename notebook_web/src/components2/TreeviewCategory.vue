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
          value="新建笔记本"
          @click="dialog2 = true"
        />
        <v-spacer></v-spacer>
      </v-toolbar>

      <v-list>
        <v-list-group
          v-for="(c,cIndex) in categories"
          :key="c.category_name"
          :prepend-icon="c.action"
          no-action
        >
          <template v-slot:activator>
            <v-list-item-content>
              <v-list-item-title v-text="c.category_name"></v-list-item-title>
            </v-list-item-content>
          </template>

          <v-list-item
            v-for="(n,nIndex) in c.notebooks"
            :key="n.notebook_name"
          >
            <v-list-item-content>
              <a
                @click="fun_tc_4(cIndex,nIndex)"
              ><v-list-item-title v-text="n.notebook_name"></v-list-item-title></a>
            </v-list-item-content>
          </v-list-item>
        </v-list-group>
      </v-list>
    </v-card>
    </el-aside>
    <el-main>
      <el-row
        type="flex"
        class="row-bg"
        justify="end"
      >
        <el-col :span="6">
          <h3>{{tc_notebook_name}}</h3>
        </el-col>
        <el-col :span="3">
          <a
            href="#"
            @click="dialog3 = true"
          >
            <el-tag type="success">添加标签</el-tag></a>
        </el-col>
        <el-col :span="3">
          <a
            href="#"
            @click="dialog4 = true"
          >
            <el-tag type="primary">新建笔记</el-tag></a>
        </el-col>

        <el-col :span="6">
          <a
            href="#"
            @click="fun_tc_3"
          >
            <el-tag type="danger">删除笔记</el-tag></a>
        </el-col>
      </el-row>
      <el-row>
        <ck></ck>
      </el-row>
      <el-row>
        <v-btn
          small
          color="primary"
          dark
          @click="fun_tc_5()"
        >
          保存
        </v-btn>
      </el-row>
    </el-main>
    <!--新建笔记本对话框-->
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
                <el-form-item label="新名称:">
                <el-input
                  placeholder="请输入新笔记本的名称"
                  v-model="newCategoryName"
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
            @click="fun_tc_1"
          >
            保存
          </v-btn>
        </v-card-text>
      </v-card>
    </v-dialog>
    <!--给笔记添加标签对话框-->
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
              md="8"
            >
              <el-select
                v-model="tc_selected_lid"
                clearable
                placeholder="请选择标签"
              >
                <el-option
                  v-for="(label,tc_lIndex) in tc_labels"
                  :key="label.lid"
                  :label="label.label_name"
                  :value="label.lid"
                ></el-option>
              </el-select>
            </v-col>

          </v-row>

          <v-btn
            class="mt-6"
            color="info"
            depressed
            default
            rounded
            @click="fun_tc_2"
          >
            添加
          </v-btn>
        </v-card-text>
      </v-card>
    </v-dialog>
    <!--新建笔记对话框-->
    <v-dialog
      v-model="dialog4"
      max-width="500"
    >
      <v-card>
        <v-card-title>
          <v-spacer />
          <v-icon
            aria-label="Close"
            @click="dialog4 = false"
          >
            mdi-close
          </v-icon>
        </v-card-title>

        <v-card-text class="text-body-1 text-center">
          <v-row>
            <v-col
              cols="12"
              md="8"
            >
              <el-form
                label-position="right"
                label-width="80px"
              >
                <el-form-item label="笔记本:">
                  <el-select
                  v-model="tc_selected_cid"
                  clearable
                  placeholder="请选择笔记本"
                >
                  <el-option
                    v-for="(c,cIndex) in categories"
                    :key="c.cid"
                    :label="c.category_name"
                    :value="c.cid"
                  ></el-option>
                </el-select>
                </el-form-item>
                <el-form-item label="笔记名称:">
                  <el-input
                    placeholder="请输入新笔记的名称"
                    v-model="newNotebookName"
                    clearable
                  ></el-input></el-form-item>
              </el-form>
            </v-col>

          </v-row>

          <v-btn
            class="mt-6"
            color="info"
            depressed
            default
            rounded
            @click="fun_tc_6"
          >
            创建
          </v-btn>
        </v-card-text>
      </v-card>
    </v-dialog>
  </el-container>
</template>
<script>
  import ck from '../components2/CKEditor'
  export default {
    name: 'tc',
    data: () => ({
      dialog2: false,
      dialog3: false,
      dialog4: false,
      tc_notebook_name: 'ck的笔记名称',
      categories: [
        {
          action: 'mdi-arrow-down-drop-circle-outline',
          notebooks: [{ nid: -1, notebook_name: '默认笔记', notebook_content: 'ck测试2' }],
          cid: -1,
          uid: -1,
          category_name: '默认笔记本',
        },
        {
          action: 'mdi-arrow-down-drop-circle-outline',
          notebooks: [
            { nid: -2, notebook_name: '百年孤独', notebook_content: 'ck测试2' },
            { nid: -3, notebook_name: '三体', notebook_content: 'ck测试2' },
            { nid: -4, notebook_name: '山海经', notebook_content: 'ck测试2' },
          ],
          cid: -2,
          uid: -1,
          category_name: '语文笔记本',
        },
        {
          action: 'mdi-arrow-down-drop-circle-outline',
          notebooks: [{ nid: -5, notebook_name: '数据链路层', notebook_content: 'ck测试2' }],
          cid: -3,
          uid: -1,
          category_name: '计算机网络',
        },
        {
          action: 'mdi-tag',
          notebooks: [{ nid: -6, notebook_name: '标签一', notebook_content: 'ck测试2' }],
          cid: -4,
          uid: -1,
          category_name: '便签笔记本',
        },
      ],
      ck_editorData: '<p>请选择一个笔记编辑。</p>',
      cIndex: -1,
      nIndex: -1,
      notebook: {
        categoryId: -1,
        notebookId: -1,
        notebook_name: 'ck的笔记名称',
        notebook_content: '<p>请选择一个笔记编辑。</p>',
      },
      newCategoryName: '',
      tc_labels: [
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
        {
          action: 'mdi-tag',
          lid: -4,
          uid: -1,
          label_name: '标签四',
        },
        {
          action: 'mdi-tag',
          lid: -5,
          uid: -1,
          label_name: '标签五',
        },
      ],
      tc_selected_lid: '',
      tc_selected_cid: '',
      newNotebookName: '',
    }),
    methods: {
      // TODOq 新建笔记本
      fun_tc_1: function () {
        this.dialog2 = false
        var _this = this
        if (this.newCategoryName !== '') {
          this.$http.post('/editor/createNewCategory.do', {
            email: _this.$that.user.email,
            newCategoryName: _this.newCategoryName,
          }).then(function (response) {
            var list = response.data
            var i = 0
            _this.categories = []
            for (i = 0; i < list.length; i++) {
              _this.categories.push({
                action: 'mdi-arrow-down-drop-circle-outline',
                notebooks: [{ nid: -1, notebook_name: 'ck的笔记名称', notebook_content: '<p>请选择一个笔记编辑。</p>' }],
                cid: list[i].categoryId,
                uid: _this.userId,
                category_name: list[i].categoryName,
              })
              // TODOq 笔记模块 回显category下的notebook数据
              var _2this = _this
              asyncA(_this, _2this, i, list)
            }
          }).catch(function (error) {
            console.log(error)
          })
        }
      },
      // TODOq 笔记模块 给笔记添加标签
      fun_tc_2: function () {
        this.dialog3 = false
        var _this = this
        if (_this.tc_selected_lid !== '' && _this.cIndex >= 0 && _this.nIndex >= 0) {
          this.$http.post('/editor/addLabelToNotebook.do', {
            uid: _this.$that.user.userId,
            lid: _this.tc_selected_lid,
            nid: _this.categories[_this.cIndex].notebooks[_this.nIndex].nid,
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
          }).catch(function (error) {
            console.log(error)
          })
        } else if (_this.tc_selected_lid === '') {
          _this.$message({
            message: '请选择一个标签',
            type: 'warning',
          })
        } else if (_this.cIndex < 0 && _this.nIndex < 0) {
          _this.$message({
            message: '请选择一个笔记',
            type: 'warning',
          })
        }
      },
      // TODOq 笔记模块 删除指定笔记
      fun_tc_3: function () {
        var _this = this
        if (_this.notebook.notebookId >= 0) {
          this.$http.post('/editor/deleteNotebook.do', {
            categoryId: _this.notebook.categoryId,
            notebookId: _this.notebook.notebookId,
            userId: _this.$that.user.userId,
          }).then(function (response) {
            _this.$message({
              message: '删除成功！',
              type: 'success',
            })
            _this.tc_notebook_name = 'ck的笔记名称'
            _this.notebook.categoryId = -1
            _this.notebook.notebookId = -1
            _this.notebook.notebook_name = 'ck的笔记名称'
            _this.notebook.notebook_content = '<p>请选择一个笔记编辑。</p>'
            _this.$bus.$emit('ck_editorData', _this.notebook.notebook_content)
            _this.categories[_this.cIndex].notebooks.splice(_this.nIndex, 1)
            _this.cIndex = -1
            _this.nIndex = -1
          })
        }
      },
      // TODOq 笔记模块 回显指定笔记内容
      fun_tc_4: function (cIndex, nIndex) {
        this.cIndex = cIndex
        this.nIndex = nIndex
        this.tc_notebook_name = this.categories[cIndex].notebooks[nIndex].notebook_name
        this.notebook.categoryId = this.categories[cIndex].cid
        this.notebook.notebookId = this.categories[cIndex].notebooks[nIndex].nid
        this.notebook.notebook_content = this.categories[cIndex].notebooks[nIndex].notebook_content
        this.$bus.$emit('ck_editorData', this.notebook.notebook_content)
      },
      // TODOq 保存笔记
      fun_tc_5: function () {
        var _this = this
        if (this.notebook.notebookId >= 0) {
          this.$http.post('/editor/saveContent.do', {
            notebookId: _this.notebook.notebookId,
            categoryId: _this.notebook.categoryId,
            notebookContent: _this.ck_editorData,
          }).then(function (response) {
            if (response.data.flag) {
              _this.categories[_this.cIndex].notebooks[_this.nIndex].notebook_content = _this.ck_editorData
              _this.$message({
                message: response.data.errorMsg,
                type: 'success',
              })
            } else {
              this.$message({
                message: response.data.errorMsg,
                type: 'warning',
              })
            }
          }).catch(function (error) {
            console.log(error)
          })
        }
      },
      // TODO 新建笔记
      fun_tc_6: function () {
        this.dialog4 = false
        var _this = this
        if (_this.tc_selected_cid >= 0 && _this.newNotebookName !== '') {
          this.$http.post('/editor/createNewNotebook.do', {
            categoryId: _this.tc_selected_cid,
            newNotebookName: _this.newNotebookName,
            uid: _this.$that.user.userId,
          }).then(function (response) {
            var list = response.data
            var i = 0
            _this.categories = []
            for (i = 0; i < list.length; i++) {
              _this.categories.push({
                action: 'mdi-arrow-down-drop-circle-outline',
                notebooks: [{ nid: -1, notebook_name: 'ck的笔记名称', notebook_content: '<p>请选择一个笔记编辑。</p>' }],
                cid: list[i].categoryId,
                uid: _this.userId,
                category_name: list[i].categoryName,
              })
              // TODOq 笔记模块 回显category下的notebook数据
              var _2this = _this
              asyncA(_this, _2this, i, list)
            }
          }).catch(function (error) {
            console.log(error)
          })
        } else if (_this.tc_selected_cid === '') {
          _this.$message({
            message: '创建笔记前，请选择一个笔记本！',
            type: 'warning',
          })
        } else if (_this.newNotebookName === '') {
          _this.$message({
            message: '笔记名称不能为空！',
            type: 'warning',
          })
        }
      },
    },
    created () {
      var _this = this
      this.$http.post('/login/indexHtml.do', {
        email: _this.$that.user.email,
      })
        .then(function (response) {
          if (!response.data.flag) {
            _this.$message({
              message: response.data.errorMsg,
              type: 'warning',
            })
          } else {
            // TODOq 笔记模块 回显category数据
            var list = response.data.data.categories
            var i = 0
            _this.categories = []
            for (i = 0; i < list.length; i++) {
              _this.categories.push({
                action: 'mdi-arrow-down-drop-circle-outline',
                notebooks: [{ nid: -1, notebook_name: 'ck的笔记名称', notebook_content: '<p>请选择一个笔记编辑。</p>' }],
                cid: list[i].categoryId,
                uid: response.data.data.userId,
                category_name: list[i].categoryName,
              })
              // TODO 笔记模块 回显category下的notebook数据
              var _2this = _this
              asyncA(_this, _2this, i, list)
            }
            // TODOq 笔记模块 回显labels数据
            var listA = response.data.data.labels
            _this.tc_labels = []
            for (i = 0; i < listA.length; i++) {
              _this.tc_labels.push({ action: 'mdi-tag', lid: listA[i].labelId, uid: response.data.data.userId, label_name: listA[i].labelName })
            }
          }
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    mounted () {
      var _this = this
      this.$bus.$on('ck_editorData', function (data) {
        _this.ck_editorData = data
      })
    },
    components: {
      ck,
    },
  }
  async function asyncA (_this, _2this, i, list) {
    await _this.$http.post('/editor/findNotebooksByCid.do', {
      // TODO 待续...
      categoryId: list[i].categoryId,
    }).then(function (response) {
      var listB = response.data
      var j = 0
      _2this.categories[i].notebooks = []
      for (j = 0; j < listB.length; j++) {
        _2this.categories[i].notebooks.push({
          nid: listB[j].notebookId,
          notebook_name: listB[j].notebookName,
          notebook_content: listB[j].notebookContent,
        })
      }
    }).catch(function (error) {
      console.log(error)
    })
  }
</script>

<style scoped>

</style>
