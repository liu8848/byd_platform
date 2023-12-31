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
                },
                {
                    path: '/auditorStandingBook/outWork',
                    component:()=>import('@/views/auditorStandingBook/AuditorStandingBookOutWork.vue')
                }

            ]
        },
        {
            path:'/contact',
            component:()=>import('@/views/layout/LayoutContainer.vue'),
            children:[
                {
                    path:'/contact/factoryContact',
                    component:()=>import('@/views/contact/factoryContact/FactoryContactList.vue')
                },
                {
                    path: '/contact/factoryContact/add',
                    component:()=>import('@/views/contact/factoryContact/FactoryContactCreate.vue')
                }
            ]
        }
    ]
})

export default router