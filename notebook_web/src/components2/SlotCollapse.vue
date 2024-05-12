<template>
  <v-expansion-panels>
    <v-expansion-panel
      v-for="(item,i) in items"
      :key="i"
    >
      <v-expansion-panel-header>
        {{item.title}}
      </v-expansion-panel-header>
      <v-expansion-panel-content>
        {{item.content}}
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
  export default {
    name: 'sc',
    data () {
      return {
        items: [
          { title: '艾宾浩斯', content: '赫尔曼·艾宾浩斯（Hermann Ebbinghaus 1850年1月24日 - 1909年2月26日），德国心理学家。艾宾浩斯出生于德国巴门，1890年艾宾浩斯和他人共同创办了《心理学和感觉生理学杂志》。\n' + '艾宾浩斯一生致力于有关记忆的实验心理学研究，在1885年出版了《关于记忆》一书，提出了著名的“艾宾浩斯遗忘曲线”，这一成就也令他成为与冯特齐名的心理学家。' },
          { title: '艾宾浩斯遗忘曲线', content: '艾宾浩斯遗忘曲线由德国心理学家艾宾浩斯(H.Ebbinghaus)研究发现，描述了人类大脑对新事物遗忘的规律。人体大脑对新事物遗忘的循序渐进的直观描述，人们可以从遗忘曲线中掌握遗忘规律并加以利用，从而提升自我记忆能力。该曲线对人类记忆认知研究产生了重大影响。' },
          { title: 'SM-2记忆算法', content: 'http://www.blueraja.com/blog/477/a-better-spaced-repetition-learning-algorithm-sm2' },
          { title: '持久性记忆', content: '长时性记忆是指永久性的信息存贮，一般能保持多年甚至终身。它的信息主要来自短时记忆阶段加以复述的内容，也有由于印象深刻一次形成的。长时性记忆的容量似乎是无限的，它的信息是以有组织的状态被贮存起来的。有词语和表象两种信息组织方式，即言语编码和表象编码。言语编码是通过词来加工信息，按意义、语法关系、系统分类等方法把言语材料组成组块，以帮助记忆。' },
          { title: '重复间隔记忆法', content: '重复记忆法，就是把所记忆的内容连续重复或间隔一定时间后再重复学习一次，经过多次重复，实现永久记忆的方法。\n\n' + '中文名重复记忆法' },
        ],
      }
    },
  }
</script>

<style scoped>

</style>
