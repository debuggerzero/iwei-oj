<template>
    <el-upload class="avatar-uploader" :action="actionUrl" :show-file-list="false" :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="imageUrl" :src="imageUrl" class="avatar" />
        <el-icon v-else class="avatar-uploader-icon">
            <PlusComponent />
        </el-icon>
    </el-upload>
</template>


<script>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
import { Plus } from '@element-plus/icons-vue';
import { useStore } from 'vuex';

export default {
    name: "UploadImage",
    emits: ['update:avatar'],
    setup(_, { emit }) {
        const imageUrl = ref(useStore().state.user.avatar);
        const actionUrl = ref('/path/file/upload/image/' + useStore().state.user.id);
        const handleAvatarSuccess = (response, uploadFile) => {
            imageUrl.value = URL.createObjectURL(uploadFile.raw);
            emit('update:avatar', response.url);
        };

        const beforeAvatarUpload = (rawFile) => {
            if (rawFile.type !== 'image/jpeg') {
                ElMessage.error('请jpg格式上传!');
                return false;
            } else if (rawFile.size / 1024 / 1024 > 2) {
                ElMessage.error('图片不能大于 2MB!');
                return false;
            }
            return true;
        };

        // 使用 Plus 组件
        const PlusComponent = Plus;

        return {
            imageUrl,
            actionUrl,
            handleAvatarSuccess,
            beforeAvatarUpload,
            PlusComponent
        };
    }
};
</script>

<style scoped>
.avatar-uploader .avatar {
    width: 150px;
    height: 150px;
    display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
}
</style>
