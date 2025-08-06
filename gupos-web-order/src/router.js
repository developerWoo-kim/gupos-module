import {createRouter, createWebHistory} from "vue-router";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            name: "main",
            component: () => import("./components/MainView.vue"),
        },
        {
            path: "/test",
            name: "test",
            component: () => import("./components/TestView.vue"),
        },
        {
            path: "/table/:tableId",
            name: "order_table",
            component: () => import("./app/orders/view/OrderTable.vue"),
            meta: { keepAlive: true }
        },
        {
            path: "/table/:tableId/menu/:menuId",
            name: "order_table_menu",
            component: () => import("./app/orders/view/OrderTableMenu.vue"),
        },
    ],
    scrollBehavior(to, from, savedPosition) {
        if (savedPosition) {
            return {
                ...savedPosition,
                behavior: 'instant' // ✅ 저장된 위치로 즉시 이동
            };
        } else {
            return { left: 0, top: 0, behavior: 'instant'}; // 기본은 맨 위로 이동
        }
    }
});
export default router;
