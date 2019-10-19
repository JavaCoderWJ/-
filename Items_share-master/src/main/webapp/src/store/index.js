import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const store = new Vuex.Store({
  state: {
    userData: {

    },
    ifLogin: false
  },
  mutations: {
    setUserData(state, data) {
      state.userData = data;
    },
    loginStateChange(state, bool) {
      state.ifLogin = bool;
    }
  }
})
export default store
