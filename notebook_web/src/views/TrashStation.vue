<template>
  <v-container
    id="typography-view"
    fluid
    tag="section"
  >
    <tts></tts>
  </v-container>
</template>

<script>
  import tts from '../components2/TreeviewTrashStation'
  export default {
    name: 'TypographyView',
    data: () => ({
    }),
    components: {
      tts,
    },
  }
</script>
