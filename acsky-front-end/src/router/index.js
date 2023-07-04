//路由
import {createRouter, createWebHistory} from 'vue-router';
import UserProfile from "../views/UserProfile";
import ProblemList from '../views/ProblemList';
import RankingList from '../views/RankingList';
import LoginView from '../views/LoginView';
import NotFonudView from '../views/NotFonudView';
import FrameView from '../views/FrameView';
import ProblemDetails from '../views/ProblemDetails';

const routes = [
    {
        path: '/',
        name: 'home',
        redirect: '/welcome.html'
    },
    {
        path: '/frame',
        name: 'frame',
        component: FrameView,
        children: [
            {
                path: '/userprofile',
                name: 'userprofile',
                component: UserProfile
            },
            {
                path: '/problem',
                name: 'problem',
                component: ProblemList
            },
            {
                path: '/problem/:id',
                name: 'detail',
                component: ProblemDetails
            },
            {
                path: '/rank',
                name: 'rank',
                component: RankingList
            },
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: LoginView
    },
    {
        path: '/404',
        name: '404',
        component: NotFonudView
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

let refreshed = false;
router.afterEach(to => {
    console.log(to.path);
    const shouldRefresh = to.path === '/welcome.html'; // 根据路由路径判断是否需要刷新
    if (shouldRefresh && !refreshed) {
        location.reload();
        refreshed = true;
    }
});


export default router
