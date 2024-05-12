<template>
  <v-container
    id="category-view"
    fluid
    tag="section"
  >
    <tc></tc>
  </v-container>
</template>

<script>
  import tc from '../components2/TreeviewCategory'

  export default {
    name: 'CategoryView',
    data: () => ({
    }),
    components: {
      tc,
    },
    computed: {
    },
  }
</script>
