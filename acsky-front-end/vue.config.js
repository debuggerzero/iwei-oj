const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/path': {
        target: 'http://47.100.31.141:8083',
        changeOrigin: true,
        pathRewrite: {
          '^/path': ''
        }
      }
    }
  },
})