<template>
  <div id="ck">
    <ckeditor
      v-model="editorData"
      :config="editorConfig"
    ></ckeditor>
  </div>
</template>

<script>
  export default {
    name: 'ck',
    data () {
      return {
        editorData: '<p>请选择一个笔记编辑。</p>',
        editorConfig: {
          uiColor: '#AADC6E',
          toolbarCanCollapse: true,
        },
      }
    },
    watch: {
      editorData (newVal, oldVal) {
        this.$bus.$emit('ck_editorData', newVal)
      },
    },
    mounted () {
      var _this = this
      this.$bus.$on('ck_editorData', function (data) {
        _this.editorData = data
      })
    },
  }
</script>
