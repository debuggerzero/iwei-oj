<template>
  <div class="login">
    <global-background />
    <div class="loginPart">
      <h2 class="title">IWEI OJ</h2>
      <router-view />
    </div>
  </div>
</template>

<script setup>
import GlobalBackground from "@/components/GlobalBackground.vue";
</script>

<style scoped>
.login {
  overflow: hidden;

  height: 100%;
  width: 100%;
  background-image: url("@/assets/background.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  position: fixed;

  .loginPart {
    position: absolute;
    top: 50%;
    left: 80%;
    transform: translate(-50%, -50%);
    width: 500px;
    padding: 50px;
    background: #f3e9f3b2;
    box-sizing: border-box;
    box-shadow: 0 15px 25px rgba(0, 0, 0, 0.5);
    border-radius: 15px;

    .title {
      margin: 0 0 30px;
      padding: 0;
      color: #3b3b3b;
      font-size: 20px;
      font-weight: bold;
      text-align: center;
    }
  }
}
</style>
