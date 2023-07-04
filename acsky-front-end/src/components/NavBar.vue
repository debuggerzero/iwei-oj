<!-- 导航栏 -->
<template>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container"><!-- container-fluid 使界面更靠近靠两边-->
            <a class="navbar-brand" :to="{ name: 'home' }">AcSky</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'home' }">首页</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'problem', params: {} }">题库</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'rank' }">排行榜</router-link>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <div v-if="isLoggedIn">
                            <router-link class="nav-link" :to="{ name: 'userprofile' }">个人信息</router-link>
                        </div>
                    </li>
                    <li class="nav-item">
                        <div v-if="!isLoggedIn">
                            <router-link class="nav-link" :to="{ name: 'login' }">登录</router-link>
                        </div>
                        <div v-if="isLoggedIn">
                            <router-link class="nav-link" :to="{ name: 'login' }">退出</router-link>
                        </div>
                    </li>
                </ul>
                <span class="navbar-text">
                </span>
            </div>
        </div>
    </nav>
</template>

<script>
//使外部可调用
import { ref } from 'vue';
import { useStore } from 'vuex';

export default {
    name: "NavBar",
    setup() {
        const store = useStore();
        const isLoggedIn = ref(store.state.user.isLoggedIn);
        return {
            isLoggedIn,
        };
    }
};
</script>

<style scoped></style>
