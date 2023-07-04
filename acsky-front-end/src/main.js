import router from './router'
import store from './store'
import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const app = createApp(App)

app.use(ElementPlus)

app.use(router)
app.use(store)

// 挂载应用程序
app.mount('#app')
