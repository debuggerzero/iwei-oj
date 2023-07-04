<template>
    <div class="card">
        <div class="card-body">
            <div class="common-layout">
                <el-container>
                    <el-main class="main">
                        <div>
                            <el-input class="code" v-model="code_input" resize="none" :rows="26" type="textarea" @keydown="insertSpace"
                                placeholder="//在这里写下你的代码"
                                input-style=" background-color: #f5f5f5;border-radius: 5px;padding: 10px;font-family: Consolas, Monaco, monospace;font-size: 14px;line-height: 1.5;color: #333; overflow-x: auto;" />
                        </div>
                    </el-main>
                    <el-divider class="line" />
                    <el-header class="header" style="margin-left: -30px;">
                        <el-form :model="form" label-width="120px" class="el-from">
                            <el-row>
                                <el-col :span="12">
                                    <el-form-item label="输入:">
                                        <el-input v-model="textarea_input" resize="none"
                                            :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" placeholder="" />
                                    </el-form-item>
                                    <el-form-item label="输出:" style="margin-top: 20px;">
                                        <el-input v-model="textarea_output" resize="none"
                                            :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" disabled
                                            placeholder=" " />
                                    </el-form-item>
                                    <el-form-item>
                                        <div :style="state_color[rst.state_code]" class="state-value">{{ prob_state }}</div>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                    <el-form-item label="语言:" class="select">
                                        <el-select v-model="form.type" placeholder="选择语言" style="width: 200px;">
                                            <el-option label="C++" value="c++" />
                                            <el-option label="Java" value="java" />
                                        </el-select>
                                    </el-form-item>

                                    <div v-if="button_state">
                                        <el-form-item class="button-form">
                                            <div class="button-container-1">
                                                <el-button @click="onDebug" class="debug-btn-1">调试</el-button>
                                            </div>
                                            <div class="button-container-2">
                                                <el-button type="primary" @click="onSubmit">提交</el-button>
                                            </div>
                                        </el-form-item>
                                    </div>

                                    <div v-else>
                                        <el-form-item class="button-form">
                                            <div class="button-container-1">
                                                <el-button loading class="debug-btn-2">调试</el-button>
                                            </div>
                                            <div class="button-container-2">
                                                <el-button type="primary" loading>提交</el-button>
                                            </div>
                                        </el-form-item>
                                    </div>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-header>
                </el-container>
            </div>
        </div>
    </div>
</template>

<script>
import { useStore } from 'vuex'
import { useRoute } from 'vue-router';
import { reactive, ref, onMounted, nextTick } from 'vue'
import axios from 'axios';
import {ElMessage} from "element-plus";

export default {
    name: 'ProblemDetailsMain',

    setup() {
        const store = useStore();
        const route = useRoute();
        var form = reactive({
            pid: route.params.id,
            uid: store.state.user.id,
            code: '',
            tase: '',
            type: '',
            time_limit: '',
            space_limit: '',
        })

        var rst = reactive({
            state_code: '1',
            message: '',
            stdout: ''
        })
        const code_input = ref(' ');

        const insertSpace = (event) => {
            if (event.keyCode === 9 || event.code === 'Tab') {  // 判断按下的是否是 Tab 键
                event.preventDefault();  // 阻止默认行为（即将 Tab 键切换到下一个可聚焦元素）
                code_input.value += '    ';
            }
        };


        var textarea_input = ref('')
        var textarea_output = ref('')

        const state_color = ['color: #409EFF;', 'color: #F56C6C;', 'color: #67C23A', 'color: #F56C6C;', 'color: #909399']

        var prob_state = ref('')
        var button_state = ref(true)

        const onSubmit = async () => {
            button_state.value = false;
            form.code = code_input.value;

            await axios.post('/path/problem/commit', {
                pid: form.pid,
                uid: form.uid,
                code: form.code,
                type: form.type,
            }, {
                headers: {
                    'Content-Type': 'application/json', // 设置请求头为 JSON
                    'Access-Control-Allow-Origin': '*'
                }
            })
                .then((res) => {
                    rst.state_code = res.data.code + 1;
                    rst.message = res.data.message;
                    if (rst.state_code == 0) {
                        rst.stdout = res.data.stdout;
                        textarea_output.value = rst.stdout;
                    }
                    button_state.value = true;
                    prob_state.value = rst.message;
                })
                .catch((err) => {
                    // button_state.value = true;
                    // rst.state_code = 1;
                    // prob_state.value = err.response.data.message;
                    ElMessage.error(err.response.data.message);
                })
        }

        const onDebug = async () => {
            button_state.value = false;
            form.tase = textarea_input.value;
            form.code = code_input.value;

            await axios.post('/path/problem/debug', {
                pid: form.pid,
                uid: form.uid,
                code: form.code,
                tase: form.tase,
                type: form.type,
            }, {
                headers: {
                    'Content-Type': 'application/json', // 设置请求头为 JSON
                    'Access-Control-Allow-Origin': '*'
                }
            })
                .then((res) => {
                    rst.state_code = res.data.code + 1;
                    rst.message = res.data.message;
                    if (rst.state_code == 0) {
                        rst.stdout = res.data.stdout;
                        textarea_output.value = rst.stdout;
                    }
                    button_state.value = true;
                    prob_state.value = rst.message;

                    console.log(form);
                    console.log(rst);
                })
                .catch((err) => {
                    // button_state.value = true;
                    // rst.state_code = 1;
                    // prob_state.value = err.response.data.message;
                    ElMessage.error(err.response.data.message);
                })
        }


        return {
            form,
            onSubmit,
            textarea_input,
            textarea_output,
            state_color,
            prob_state,
            button_state,
            onDebug,
            rst,
            code_input,
            insertSpace,
        }
    },
}
</script>

<style scoped>
.card {
    min-width: 400px;
}

.line {
    margin-top: 0px;
}

.main {
    min-height: 600px;
}

.header {
    min-height: 200px
}


.button-form {
    margin-top: 60px;
}

.debug-btn-1 {
    margin-right: 70px;
}

.debug-btn-2 {
    margin-right: 50px;
}

.state-value {
    font-size: 36px;
    margin-top: 15px;
    margin-left: 20px;
}



.button-container-1 {
    margin: 10px;
    margin-left: 0px;
}

.button-container-2 {
    display: flex;
    justify-content: flex-end;
    margin: 10px;
    margin-left: 0px;
}

@media (max-width: 600px) {
    .button-container-2 {
        flex-direction: column;
        align-items: flex-end;
    }
}
</style>
