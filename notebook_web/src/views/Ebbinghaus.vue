<template>
  <v-container
    id="regular-tables-view"
    fluid
    tag="section"
  >
    <cke></cke>
  </v-container>
</template>

<script>
  import cke from '../components2/TreeviewEbbinghaus'
  export default {
    name: 'EbbinghausView',
    components: {
      cke,
    },
  }
</script>
