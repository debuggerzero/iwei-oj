import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "@/access";
import "@/assets/css/style.css";
import "@/assets/font/iconfont.css";

import * as ElementPlusIconsVue from "@element-plus/icons-vue";

import ElementPlus from "element-plus";
import "element-plus/dist/index.css";

import Particles from "vue3-particles";

const app = createApp(App);
app.use(ElementPlus).use(Particles).use(store).use(router);
app.mount("#app");
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}
