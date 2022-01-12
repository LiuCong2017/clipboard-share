import {createRouter, createWebHashHistory} from "vue-router";

const routes = [
    {
        path: '/',
        name: 'home',
        redirect: '/home'
    },
    {
        path: '/home',
        name: 'Home',
        component: () => import('../views/dashboard/index.vue'),
        // component: () => import('../views/test/indexfew.vue'),
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router;
