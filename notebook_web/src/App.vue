<template>
  <v-fade-transition mode="out-in">
    <router-view />
  </v-fade-transition>
</template>

<script>
  import '@/styles/overrides.sass'

  export default {
    name: 'App',
    metaInfo: {
      title: 'China',
      titleTemplate: '%s | NoteBook Pro',
      htmlAttrs: { lang: 'zh-CN' },
      meta: [
        { charset: 'utf-8' },
        { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      ],
    },
  }
</script>
