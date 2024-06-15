import { createRouter, createWebHistory } from "vue-router"


import HomeFoodBalance from "./views/HomeFoodBalance.vue"


 
const routes = [
    {
        name: 'homeFoodBalance',
        path: '/',
        component: HomeFoodBalance
    }
]


const router = createRouter({
    history: createWebHistory(),
    routes: routes
})


export default router