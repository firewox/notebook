<template>
  <el-container>
    <el-aside width="260px">
      <v-card
        class="mx-auto"
      >
        <v-list>
          <v-list-item
            v-for="(n,nIndex) in tts_notebooks"
            :key="n.notebook_name"
            :prepend-icon="n.action"
            no-action
          >
            <v-list-item-content>
              <a
                @click="fun_tts_1(nIndex)"
                ><v-list-item-title v-text="n.notebook_name"></v-list-item-title></a>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-card>
    </el-aside>
    <el-main>
      <el-row
        type="flex"
        class="row-bg"
        justify="center"
      >
        <el-col
          :span="24"
        ><sc></sc></el-col>
      </el-row>
    </el-main>
    <!--永久删除 和 恢复笔记 对话框-->
    <v-dialog
      v-model="dialog2"
      max-width="500"
    >
      <v-card>
        <v-card-title>
          <b>删除或恢复笔记</b>
          <v-spacer />
          <v-icon
            aria-label="Close"
            @click="dialog2 = false"
          >
            mdi-close
          </v-icon>
        </v-card-title>

        <v-card-text class="text-body-1 text-center">

          <v-btn
            class="mt-6 primary"
            depressed
            default
            rounded
            @click="fun_tts_2"
          >
            永久删除
          </v-btn>
          <v-btn
            class="mt-6 primary"
            depressed
            default
            rounded
            @click="fun_tts_3"
          >
            恢复笔记
          </v-btn>
        </v-card-text>
      </v-card>
    </v-dialog>
  </el-container>
</template>
<script>
  import sc from '../components2/SlotCollapse'
  export default {
    name: 'tts',
    data: () => ({
      dialog2: false,
      tts_notebooks: [
        {
          action: 'mdi-alphabetical',
          tsid: -1,
          uid: -1,
          nid: -1,
          notebook_name: '笔记一',
        },
        {
          action: 'mdi-alphabetical',
          tsid: -1,
          uid: -1,
          nid: -1,
          notebook_name: '笔记二',
        },
        {
          action: 'mdi-alphabetical',
          tsid: -1,
          uid: -1,
          nid: -1,
          notebook_name: '笔记三',
        },
      ],
      tsnIndex: '',
    }),
    methods: {
      // TODOq 垃圾回收模块 永久删除 和 恢复笔记
      fun_tts_1: function (nIndex) {
        this.tsnIndex = nIndex
        this.dialog2 = true
      },
      // TODOq 永久删除
      fun_tts_2: function () {
        this.dialog2 = false
        var _this = this
        this.$http.post('/editor/deleteTrashStationById.do', {
          tsid: _this.tts_notebooks[_this.tsnIndex].tsid,
          userId: _this.tts_notebooks[_this.tsnIndex].uid,
        }).then(function (response) {
          _this.tts_notebooks.splice(_this.tsnIndex, 1)
          _this.$message({
            message: '删除成功！',
            type: 'success',
          })
          _this.tsnIndex = ''
        }).catch(function (error) {
          console.log(error)
        })
      },
      // TODO 恢复笔记
      fun_tts_3: function () {
        this.dialog2 = false
        var _this = this
        this.$http.post('/editor/retrieveNotebookFromTrashStation.do', {
          tsid: _this.tts_notebooks[_this.tsnIndex].tsid,
          userId: _this.tts_notebooks[_this.tsnIndex].uid,
          categoryId: 0,
        }).then(function (response) {
          _this.tts_notebooks.splice(_this.tsnIndex, 1)
          _this.$message({
            message: '恢复成功！',
            type: 'success',
          })
          _this.tsnIndex = ''
        }).catch(function (error) {
          console.log(error)
        })
      },
    },
    components: {
      sc,
    },
    created () {
      var _this = this
      _this.$http.post('/login/indexHtml.do', {
        email: _this.$that.user.email,
      }).then(function (response) {
        var listA = response.data.data.trashStation
        _this.tts_notebooks = []
        var i = 0
        for (i = 0; i < listA.length; i++) {
          _this.tts_notebooks.push({
            action: 'mdi-alphabetical',
            tsid: listA[i].tsid,
            uid: listA[i].uid,
            nid: listA[i].nid,
            notebook_name: listA[i].notebookName,
          })
        }
      }).catch(function (error) {
        console.log(error)
      })
    },
  }
</script>

<style scoped>

</style>
