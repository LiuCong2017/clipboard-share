import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
const {resolve} = require("path");

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve:{
    alias:{
      '@': resolve(__dirname,'./src'),
    },
  },
  build:{
    exclude: ['./node_modules/**'],
    outputDir: './dist',
  },
  server:{
    host: '0.0.0.0',
    port: 20389,
    // open: true,
    proxy:{
      '/api': {
        target: 'http://192.168.2.211:20388',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '/')
      },
    },


  },

})
