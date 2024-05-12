// Pathify
import { make } from 'vuex-pathify'

// Data
const state = {
  drawer: null,
  drawerImage: true,
  mini: false,
  version: 3.13,
  items: [
    {
      title: '笔记本',
      icon: 'mdi-application',
      to: '/components/category/',
    },
    {
      title: '艾宾浩斯标签',
      icon: 'mdi-tag',
      to: '/components/label/',
    },
    {
      title: '艾宾浩斯复盘',
      icon: 'mdi-av-timer',
      to: '/components/ebbinghaus/',
    },
    {
      title: '垃圾回收中心',
      icon: 'mdi-archive',
      to: '/components/trashStation/',
    },
  ],
}

const mutations = make.mutations(state)

const actions = {
  ...make.actions(state),
  init: async ({ dispatch }) => {
    //
  },
}

const getters = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
