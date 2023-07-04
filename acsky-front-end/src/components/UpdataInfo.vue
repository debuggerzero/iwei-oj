<template>
    <div class="card">
        <div class="card-body">
            <el-form :model="form" label-width="80px">
                <el-form-item label="用户名">
                    <el-input v-model="form.name" />
                </el-form-item>
                <el-form-item label="头像">
                    <UploadImage @update:avatar="handleUpdateAvatar" />
                </el-form-item>
                <el-form-item label="账号">
                    <el-input v-model="form.account" />
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="form.email" />
                </el-form-item>
                <el-form-item label="手机号">
                    <el-input v-model="form.phone" />
                </el-form-item>
                <el-form-item label="个人简介">
                    <el-input v-model="form.profile" type="textarea" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">提交</el-button>
                    <el-button @click="$emit('button-click')">取消</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';
import { useStore } from 'vuex';
import UploadImage from './UploadImage.vue';

export default {
    name: 'UpdataInfo',
    setup(_, { emit }) {
        const store = useStore();

        const user = ref(store.state.user);

        const form = ref({
            name: user.value.name,
            account: user.value.account,
            email: user.value.email,
            phone: user.value.phone,
            profile: user.value.profile,
            avatar: user.value.avatar,
        });

        const handleUpdateAvatar = (newAvatar) => {
            form.value.avatar = newAvatar;
            console.log(form.value.avatar);
        }

        const onSubmit = () => {
            axios
                .put('/path/user/modify/info', {

                    id: store.state.user.id,

                    name: form.value.name,
                    account: form.value.account,
                    email: form.value.email,
                    phone: form.value.phone,
                    profile: form.value.profile,
                    avatar: form.value.avatar
                },
                    {
                        headers: {
                            'Content-Type': 'application/json' // 设置请求头为 JSON
                        }
                    })
                .then((response) => {
                    store.commit("updateUser", {
                        isLoggedIn: true,
                        id: response.data.id,
                        name: response.data.name,
                        account: response.data.account,
                        email: response.data.email,
                        phone: response.data.phone,
                        profile: response.data.profile,
                        avatar: response.data.avatar,
                        createDate: response.data.createDate,
                        priv_level: response.data.level.id,
                        submit_cnt: response.data.submitCnt,
                        pass_cnt: response.data.passCnt,
                    });
                    emit('button-click');
                })
                .catch(error => {
                    console.error(error);
                    //ElMessage.error("提交失败！");
                });
        };

        return {
            user,
            form,
            onSubmit,
            handleUpdateAvatar,
        };
    },
    components: {
        UploadImage,
    },
};
</script>

<style scoped>
.container {
    margin-top: 20px;
}
</style>
