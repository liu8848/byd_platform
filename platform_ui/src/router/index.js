import {createRouter,createWebHistory} from 'vue-router'

const router=createRouter({
    history:createWebHistory(import.meta.env.BASE_URL),
    routes:[
        {path:'/login',component:()=>import('@/views/login/loginPage.vue')},
        {
            path:'/',
            component:()=>import('@/views/layout/LayoutContainer.vue'),
            redirect:'/index',
            children:[
                {
                    path:'index',
                    component:()=>import('@/views/index/index.vue')
                },  
                // {
                //     path:'auditorStandingBook',
                //     component:()=>import('@/views/auditorStandingBook/AuditorStandingBookInWork.vue')
                // }
            ]
        },
        {
            path:'/auditorStandingBook',
            component:()=>import('@/views/layout/LayoutContainer.vue'),
            redirect:'/auditorStandingBook/inWork',
            children:[
                {
                    path:'/auditorStandingBook/inWork',
                    component:()=>import('@/views/auditorStandingBook/AuditorStandingBookInWork.vue')
                },
                {
                    path:'/auditorStandingBook/addAuditor',
                    component:()=>import('@/views/auditorStandingBook/AuditorAddPage.vue')
                },
                {
                    path:'/auditorStandingBook/change',
                    component:()=>import('@/views/auditorStandingBook/AuditorStandingBookChange.vue')
                }
            ]
        }
    ]
})

export default router