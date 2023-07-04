//App.vue
<template>
  <router-view></router-view>
</template>
<script>
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap'
import { useStore } from 'vuex'
import { onMounted } from 'vue';
//引入组件位置

export default {
  name: "App",
  components: {

  },
  setup() {
    onMounted(async () => {
      useStore().commit("updateUser", JSON.parse(localStorage.getItem('user')));
    });
  }
}
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

  color: #2c3e50;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
