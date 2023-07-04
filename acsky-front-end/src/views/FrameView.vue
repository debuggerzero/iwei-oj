<!-- 首页 -->
<template>
    <NavBar />
    <div><router-view /></div>
</template>

<script>

import NavBar from '../components/NavBar.vue';

export default {
    name: 'FrameView',
    components: {
        NavBar,
    }
    
}
</script>

<style scoped></style>