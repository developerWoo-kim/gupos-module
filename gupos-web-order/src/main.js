// main.js
import {createApp} from "vue";
import App from "./App.vue";
// 라우터
import router from "./router";
import {createPinia} from "pinia";

const pinia = createPinia()
const app = createApp(App)


app.use(pinia)
app.use(router).mount("#app");

