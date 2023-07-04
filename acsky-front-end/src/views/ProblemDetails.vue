<!--单个题目-->
<template>
    <ContentBase>
        <div class="common-layout">
            <div class="row">
                <div class="col-4">
                    <ProblemDetailsAside />
                </div>
                <viv class="col-8">
                    <ProblemDetailsMain />
                </viv>
            </div>
        </div>
    </ContentBase>
</template>

<script>
import ContentBase from '../components/ContentBase.vue'
import ProblemDetailsAside from '../components/ProblemDetailsAside.vue'
import ProblemDetailsMain from '../components/ProblemDetailsMain.vue'

export default {
    name: 'ProblemDetails',
    components: {
        ContentBase,
        ProblemDetailsAside,
        ProblemDetailsMain,
    },
}
</script>

<style scoped></style>