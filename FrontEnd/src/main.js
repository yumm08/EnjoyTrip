import "./assets/main.css";

import { createApp } from "vue";
import { Quasar } from "quasar";
import { createPinia } from "pinia";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
import InfiniteLoading from "v3-infinite-loading";

import App from "./App.vue";
import router from "./router";

import "@quasar/extras/material-icons/material-icons.css";
import "quasar/src/css/index.sass";

import "@quasar/extras/material-icons/material-icons.css";
import "quasar/src/css/index.sass";
const app = createApp(App);
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

// app.use(createPinia())
app.use(pinia).use(router);
app.use(Quasar, {
	plugins: {}, // import Quasar plugins and add here
});
app.component("infinite-loading", InfiniteLoading);

app.mount("#app");
