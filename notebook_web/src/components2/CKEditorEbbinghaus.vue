<template>
  <div id="cke">
    <el-row>
      <ckeditor
        v-model="editorData"
        :config="editorConfig"
      ></ckeditor>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: 'cke',
    data () {
      return {
        editorData: '<p>请选择一个笔记复盘。</p>',
        editorConfig: {
          uiColor: '#AADC6E',
          toolbarCanCollapse: true,
        },
      }
    },
    watch: {
      editorData (newVal, oldVal) {
        this.$bus.$emit('cke_editorData', newVal)
      },
    },
    mounted () {
      var _this = this
      this.$bus.$on('cke_editorData', function (data) {
        _this.editorData = data
      })
    },
  }
</script>
