import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import Antd from "ant-design-vue";
import "ant-design-vue/dist/reset.css";
import { VueMasonryPlugin } from "vue-masonry";
createApp(App).use(router).use(Antd).use(VueMasonryPlugin).mount("#app");
