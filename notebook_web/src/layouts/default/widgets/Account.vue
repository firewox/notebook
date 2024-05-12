<template>
  <v-menu
    bottom
    left
    min-width="200"
    offset-y
    origin="top right"
    transition="scale-transition"
  >
    <template v-slot:activator="{ attrs, on }">
      <v-btn
        class="ml-2"
        min-width="0"
        text
        v-bind="attrs"
        v-on="on"
      >
        <v-icon>mdi-account</v-icon>
      </v-btn>
    </template>

    <v-list
      :tile="false"
      flat
      nav
    >
      <template v-for="(p, i) in profile">
        <v-divider
          v-if="p.divider"
          :key="`divider-${i}`"
          class="mb-2 mt-2 primary"
        />

        <app-bar-item
          v-else
          :key="`item-${i}`"
          :to="p.action"
          target=""
        >
          <v-list-item-title
            v-text="p.title"
          />
        </app-bar-item>
      </template>
    </v-list>
  </v-menu>
</template>

<script>
  export default {
    name: 'DefaultAccount',

    data: () => ({
      profile: [
        { title: '个人介绍', action: '/components/userProfile' }, // TODOq 个人介绍模块
        { title: '修改用户名', action: '/components/editorUsername' }, // TODO1 修改用户名待续...
        { title: '修改密码', action: '/components/editorPassword' }, // TODO1 修改密码待续...
        { divider: true },
        { title: '注销账号', action: '/components/deleteAccount' }, // TODOq 注销账号待续...
      ],
    }),
  }
</script>
