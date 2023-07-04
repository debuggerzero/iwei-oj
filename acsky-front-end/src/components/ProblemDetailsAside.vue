<template>
    <el-aside width="400px">
        <div class="card">
            <div class="card-body">
                <el-container class="containe">
                    <h2>
                        <pre>{{ problem_info_new.id }}  {{ problem_info_new.title }}</pre>
                    </h2>
                </el-container>
                <el-divider class="line" />
                <div>
                    <div v-html="renderedHTML"></div>
                </div>
            </div>
        </div>
    </el-aside>
</template>

<script>
import { ref, reactive, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios'
import MarkdownIt from 'markdown-it'

export default {
    name: 'ProblemDetailsAside',
    setup() {
        const route = useRoute();
        const problem_id = route.params.id;
        const problem_info = reactive({
            id: '',
            title: '',
            difficulty: '',
            submit_cnt: '',
            pass_cnt: '',
            passing_rate: '',
            time_limit: '',
            description: '',
            input_desc: '',
            output_desc: '',
            hint: ''
        });

        const problem_info_new = reactive({
            id: '',
            title: '',
            difficulty: '',
            submit_cnt: '',
            pass_cnt: '',
            passing_rate: '',
            time_limit: '',
            context: '',
        });

        const problem_samples = reactive([]);

        onMounted(async () => {
            // axios.get('http://47.100.31.141:8083/problem/one/' + problem_id, {})
            //     .then((res) => {
            //         problem_info.id = res.data.id; // 直接修改属性
            //         problem_info.title = res.data.title;
            //         problem_info.difficulty = res.data.difficulty;
            //         problem_info.submit_cnt = res.data.submitCnt;
            //         problem_info.pass_cnt = res.data.passCnt;
            //         problem_info.passing_rate = `${res.data.passCnt / res.data.submitCnt * 100}%`;
            //         problem_info.time_limit = res.data.timeLimit;
            //         problem_info.description = res.data.description;
            //         problem_info.input_desc = res.data.inputDesc;
            //         problem_info.output_desc = res.data.outputDesc;
            //         problem_info.hint = res.data.hint;
            //         for (let i = 0; i < res.data.samples.length; i++) {
            //             problem_samples.push({
            //                 sample_input: res.data.samples[i].input,
            //                 sample_output: res.data.samples[i].output
            //             });
            //         }
            //         renderHTML();
            //     })
            //     .catch((err) => {
            //         console.log(err);
            //     })

            axios.get('/path/problem/oneByMd/' + problem_id
            )
                .then((res) => {
                    problem_info_new.id = res.data.id; // 直接修改属性
                    problem_info_new.title = res.data.title;
                    problem_info_new.difficulty = res.data.difficulty;
                    problem_info_new.submit_cnt = res.data.submitCnt;
                    problem_info_new.pass_cnt = res.data.passCnt;
                    problem_info_new.passing_rate = `${res.data.passCnt / res.data.submitCnt * 100}%`;

                    problem_info_new.time_limit = res.data.timeLimit;
                    problem_info_new.context = res.data.context;
                    renderHTML();
                    console.log(renderedHTML);
                })
                .catch((err) => {
                    console.log(err);
                })
        });

        const renderedHTML = ref('');
        const renderHTML = () => {
            const md = new MarkdownIt();
            // const description = problem_info.description ;
            // renderedHTML.value = md.render(description.toString());
            const context = problem_info_new.context;
            renderedHTML.value = md.render(context.toString());


        };

        return {
            problem_info,
            problem_info_new,
            problem_samples,
            renderedHTML,
        }
    }

}
</script>

<style scoped>
.containe h2 {
    margin-right: 20px;
    /* 可选的间距设置 */
}

.card {
    min-height: 885px;
}

.line {
    margin-top: 0px;
}
</style>
