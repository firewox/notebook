<template>
  <v-container
    id="label-view"
    fluid
    tag="section"
  >
    <tl></tl>
  </v-container>
</template>

<script>
  import tl from '../components2/TreeviewLabel'
  export default {
    name: 'LabelView',
    components: {
      tl,
    },
  }
</script>
