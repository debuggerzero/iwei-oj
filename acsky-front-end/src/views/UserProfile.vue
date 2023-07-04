<!-- 个人主页 --> 
<template>
    <ContentBase>
        <div class="row">
            <div class="col-4">
                <UserInfo/>
            </div>
            <viv class="col-8">
                <ProblemHistory />
            </viv>
        </div>
    </ContentBase>
</template>;

<script>
import ContentBase from "../components/ContentBase.vue"
import UserInfo from "../components/UserInfo.vue"
import ProblemHistory from "../components/ProblemHistory.vue";

export default {
    name: 'UserProfile',
    components: {
        ContentBase,
        UserInfo,
        ProblemHistory,
    },
}
</script>

<style scoped>
/* .row {
    text-align: center;
} */
</style>