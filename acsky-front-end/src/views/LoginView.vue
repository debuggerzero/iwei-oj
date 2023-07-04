<!-- 首页 -->
<template>
    <body class="body">
        <div class="container">
            <h2>登 录</h2>
            <form @submit.prevent="loginForm">
                <input type="text" id="username" placeholder="Username"> <!-- 用户名输入框 -->
                <input type="password" id="password" placeholder="Password"> <!-- 密码输入框 -->
                <text class="hint">忘记密码请联系管理员</text>
                <button type="submit" id="submit">Login</button>
            </form>
        </div>
    </body>
</template>

<script>
import { mapMutations } from 'vuex';

export default {
    name: 'LoginView',
    mounted() {
        localStorage.clear();
        this.submitButton = document.getElementById('submit');
        if (this.submitButton) {
            this.submitButton.addEventListener('click', this.validateLoginForm);
        }
    },
    beforeUnmount() {
        if (this.submitButton) {
            this.submitButton.removeEventListener('click', this.validateLoginForm);
        }
    },
    methods: {
        ...mapMutations(['updateUser']),
        validateLoginForm(event) {
            var usernameInput = document.getElementById("username");
            var passwordInput = document.getElementById("password");
            var username = usernameInput.value.trim();
            var password = passwordInput.value.trim();

            if (username === "") {
                usernameInput.setCustomValidity("请填写用户名！");
                passwordInput.setCustomValidity('');
                return false;
            }
            if (password === "") {
                passwordInput.setCustomValidity("请填写密码！");
                usernameInput.setCustomValidity('');
                return false;
            }

            usernameInput.setCustomValidity('');
            passwordInput.setCustomValidity('');

            event.preventDefault();

            var MD5 = require('md5.js');

            this.loginForm(username, new MD5().update(password).digest('hex'));
            return true;
        },

        loginForm(username, password) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "/path/login/password", true);
            xhr.setRequestHeader("Content-Type", "application/json");

            xhr.onreadystatechange = () => {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        alert("登录成功！");
                        var data = JSON.parse(xhr.responseText);
                        this.$store.commit("updateUser", {
                            isLoggedIn: true,
                            id: data.id,
                            name: data.name,
                            account: data.account,
                            email: data.email,
                            phone: data.phone,
                            profile: data.profile,
                            avatar: data.avatar,
                            createDate: data.createDate,
                            priv_level: data.level.id,
                            submit_cnt: data.submitCnt,
                            pass_cnt: data.passCnt,
                        });
                        localStorage.setItem('user', JSON.stringify(this.$store.state.user));
                        this.$router.push({ name: 'home' });
                    } else {
                        alert(xhr.responseText);
                    }
                }
            };

            var data = JSON.stringify({ "account": username, "password": password });
            xhr.send(data);
        },
    },
}
</script>

<style scoped>
body,
html {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
}

.body {
    background-image: url("@/assets/background.png");
    background-size: cover;
    background-position: center;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    /* 使用视口高度作为高度 */
}

.hint {
    float: right;
    text-align: right;
    margin-bottom: 10px;

    font-family: Arial, sans-serif;
    /* 使用 Arial 字体（备选为 sans-serif） */
    font-size: 12px;
    color: #666;
}

.container {
    width: 400px;

    background-color: #fff;
    border-radius: 5px;
    padding: 20px;
    margin-top: -520px;

    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.3);

    opacity: 0.8;
    /* 设置初始透明度为0.5 */
    transition: opacity 0.3s ease;
    /* 添加过渡效果 */
}

.container:hover {
    opacity: 1;
    /* 鼠标经过时恢复透明度为1 */
}

h2 {
    text-align: center;
    color: #333;
    margin-bottom: 20px;
    font-size: 30px;
}

input[type="text"],
input[type="password"] {
    width: 100%;
    height: 40px;
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    box-sizing: border-box;
    border-radius: 3px;
}

button[type="submit"] {
    width: 100%;
    height: 40px;
    padding: 10px;
    background-color: #4CAF50;
    border: none;
    color: white;
    cursor: pointer;
    border-radius: 3px;
    transition: background-color 0.3s ease;
}

button[type="submit"]:hover {
    background-color: #45a049;
}
</style>