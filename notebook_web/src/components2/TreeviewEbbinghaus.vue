<template>
  <el-container>
    <el-aside width="260px">
      <v-card
        class="mx-auto"
      >

        <v-list>
          <v-list-group
            v-for="(l,lIndex) in te_labels"
            :key="l.label_name"
            :prepend-icon="l.action"
            no-action
          >
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title v-text="l.label_name"></v-list-item-title>
              </v-list-item-content>
            </template>

            <v-list-item
              v-for="(ln,lnIndex) in l.label_notebooks"
              :key="ln.notebook_name"
            >
              <v-list-item-content>
                <a
                  @click="fun_te_1(lIndex,lnIndex)"
                ><v-list-item-title v-text="ln.notebook_name"></v-list-item-title></a>
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
        justify="center"
      >
      <h4>{{te_notebook_name}}</h4>
      </el-row>
      <el-row>
        <cke></cke>
      </el-row>
      <el-row>
        <el-button
          type="success"
          @click="fun_te_2(5)"
        >Great</el-button>
        <el-button
          type="warning"
          @click="fun_te_2(3)"
        >Not Bad</el-button>
        <el-button
          type="danger"
          @click="fun_te_2(1)"
        >Worse</el-button>
      </el-row>
    </el-main>
  </el-container>
</template>
<script>
  import cke from '../components2/CKEditorEbbinghaus'
  export default {
    name: 'te',
    data: () => ({
      te_notebook_name: 'cke的笔记名称',
      te_labels: [
        {
          action: 'mdi-tag',
          lid: -1,
          uid: -1,
          label_name: '标签一',
          label_notebooks: [{ lnid: -1, notebook_name: '默认笔记', notebook_content: 'ck测试2' }],
        },
        {
          action: 'mdi-tag',
          lid: -2,
          uid: -1,
          label_name: '标签二',
          label_notebooks: [
            { lnid: -2, notebook_name: '百年孤独2', notebook_content: 'cke测试2' },
            { lnid: -3, notebook_name: '三体2', notebook_content: 'cek测试2' },
            { lnid: -4, notebook_name: '山海经2', notebook_content: 'cke测试2' },
          ],
        },
        {
          action: 'mdi-tag',
          lid: -3,
          uid: -1,
          label_name: '标签三',
          label_notebooks: [{ lnid: -5, notebook_name: '数据链路层2', notebook_content: 'cke测试2' }],
        },
        {
          action: 'mdi-tag',
          lid: -4,
          uid: -1,
          label_name: '标签四',
          label_notebooks: [{ lnid: -6, notebook_name: '计算机网络2', notebook_content: 'cke测试2' }],
        },
      ],
      cke_editorData: '<p>请选择一个笔记复盘。</p>>',
      lIndex: -1,
      lnIndex: -1,
    }),
    methods: {
      // TODOq 艾宾浩斯复盘模块 回显指定笔记内容
      fun_te_1: function (lIndex, lnIndex) {
        this.lIndex = lIndex
        this.lnIndex = lnIndex
        this.te_notebook_name = this.te_labels[lIndex].label_notebooks[lnIndex].notebook_name
        this.$bus.$emit('cke_editorData', this.te_labels[lIndex].label_notebooks[lnIndex].notebook_content)
      },
      // TODO 艾宾浩斯复盘模块 复盘
      fun_te_2: function (grade) {
        var _this = this
        if (_this.lIndex >= 0 && _this.lnIndex >= 0) {
          this.$http.post('/review/ebbinghausReview.do', {
            lnid: _this.te_labels[_this.lIndex].label_notebooks[_this.lnIndex].lnid,
            uid: _this.te_labels[_this.lIndex].uid,
            lid: _this.te_labels[_this.lIndex].lid,
            grade: grade,
          }).then(function (response) {
            _this.$message({
              message: 'OK！',
              type: 'success',
            })
            _this.te_notebook_name = 'cke的笔记名称'
            _this.$bus.$emit('cke_editorData', '<p>请选择一个笔记复盘。</p>')
            _this.te_labels[_this.lIndex].label_notebooks.splice(_this.lnIndex, 1)
            _this.lIndex = -1
            _this.lnIndex = -1
          })
        } else {
          _this.$message({
            message: '请选择一个笔记复盘！',
            type: 'warning',
          })
        }
      },
    },
    mounted () {
      var _this = this
      this.$bus.$on('cke_editorData', function (data) {
        _this.cke_editorData = data
      })
    },
    components: {
      cke,
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
            // TODOq 艾宾浩斯复盘模块 回显te_labels数据
            var listA = response.data.data.labels
            var i = 0
            _this.te_labels = []
            for (i = 0; i < listA.length; i++) {
              _this.te_labels.push({
                action: 'mdi-tag',
                lid: listA[i].labelId,
                uid: response.data.data.userId,
                label_name: listA[i].labelName,
                label_notebooks: [{ lnid: -1, notebook_name: 'cke的笔记名称', notebook_content: '<p>请选择一个笔记复盘。</p>' }],
              })
              // TODOq 艾宾浩斯复盘模块 回显te_labels下的label_notebooks数据
              var _2this = _this
              asyncA(_this, _2this, i, listA)
            }
          }
        })
        .catch(function (error) {
          console.log(error)
        })
    },
  }
  async function asyncA (_this, _2this, i, list) {
    await _this.$http.post('/editor/findLabelNotebooksByLidAndUid.do', {
      // TODO 待续...
      uid: _2this.$that.user.userId,
      lid: list[i].labelId,
    }).then(function (response) {
      var listB = response.data
      var j = 0
      _2this.te_labels[i].label_notebooks = []
      for (j = 0; j < listB.length; j++) {
        _2this.te_labels[i].label_notebooks.push({
          lnid: listB[j].lnid,
          notebook_name: listB[j].labelNotebookName,
          notebook_content: listB[j].labelNotebookContent,
        })
      }
    }).catch(function (error) {
      console.log(error)
    })
  }
</script>

<style scoped>

</style>
