import ElementPlus from 'element-plus'
import {createApp} from 'vue'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router/index'
import pinia from './stores/index'

const app = createApp(App)
app.use(ElementPlus)
app.use(pinia)
app.use(router)
app.mount('#app')
