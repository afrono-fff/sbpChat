<template>
    <div>
        <el-form class="form" id="form" :model="form" :rules="rules" ref="formRef">
            <p class="form-title">Sign in to your account</p>
            <div class="input-container">        
              <input class="custom-input" type="username" v-model="form.username" id="username" placeholder="Enter username" style="width: 300px;height: 50px;margin-top: 15px;"/>
            </div>
            <div class="input-container">
              <input type="password" v-model="form.password" id="password" placeholder="Enter password" style="width: 300px;height: 50px;"/>
            </div>
            <button type="button" class="submit" @click="confirm()">
            Sign in
        </button>
        <p class="signup-link">
            No account?
            <a @click="gotoSignup()" style="cursor: pointer;">Sign up</a>
        </p>
    </el-form>

    </div>
</template>

<script setup>
import router from "@/router"
import httpInstance from "@/utils/http";
import { ElNotification } from "element-plus";
import 'element-plus/theme-chalk/el-message.css'
import { h, ref } from "vue";

const form = ref({
  username: '',
  password: ''
})
const rules = {
  username: [
    { required: true, message: 'Username CAN\'T be blanket' , trigger: 'blur' }
  ],
  password: [
    { required: true, message: 'Password CAN\'T be blanket', trigger: 'blur' },
    { min: 6, max: 14, message: 'Limit your password to 6-14 characters', trigger: 'blur' },
  ],
}
const formRef = ref(null)
const confirm = () => {
  const { username, password } = form.value
  console.log(username, password)
  if(username == ''){
    // ElMessage({ type: 'warning', message: 'Username CAN\'T be blanket' })
    ElNotification({
      title: 'Warning',
      message: h('i', { style: 'color: teal' }, 'Username CAN\'T be blanket'),
    })
  }else if(password == ''){
    // ElMessage({ type: 'warning', message: 'Password CAN\'T be blanket' })
    ElNotification({
      title: 'Warning',
      message: h('i', { style: 'color: teal' }, 'Password CAN\'T be blanket'),
    })
  }else{
    httpInstance.post('user_signin', {
      username: form.value.username,
      password: form.value.password
    }).then(res => {
      console.log(res.data)
      if(res.data.code === 200){
        // ElMessage({ type: 'success', message: 'Successfully signed in' })
        ElNotification({
          title: 'Success',
          message: h('i', { style: 'color: teal' }, 'Welcom back'),
          type: 'success'
        })
        router.replace('/about')
      }else if(res.data.code === 10001){
        // ElMessage({ type: 'error', message: 'Nonexistent username' })
        ElNotification({
          title: 'Warning',
          message: h('i', { style: 'color: teal' }, 'Nonexistent username'),
        })
      }else{
        // ElMessage({ type: 'error', message: 'Wrong password' })
        ElNotification({
          title: 'Error',
          message: h('i', { style: 'color: teal' }, 'Wrong password'),
          type: 'error'
        })
      }
    })
    
  }
}

const gotoSignup = () => {
    router.push("/signup")
}
</script>

<style scoped>

.form {
  background-color: #fff;
  display: block;
  padding: 1rem;
  max-width: 350px;
  border-radius: 0.5rem;
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.form-title {
  font-size: 1.25rem;
  line-height: 1.75rem;
  font-weight: 600;
  text-align: center;
  color: #000;
}

.input-container {
  position: relative;
}

.input-container input, .form button {
  outline: none;
  border: 1px solid #e5e7eb;
  margin: 8px 0;
}

.input-container input {
  background-color: #fff;
  padding: 1rem;
  padding-right: 3rem;
  font-size: 0.875rem;
  line-height: 1.25rem;
  width: 300px;
  border-radius: 0.5rem;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
}

.submit {
  display: block;
  padding-top: 0.75rem;
  padding-bottom: 0.75rem;
  padding-left: 1.25rem;
  padding-right: 1.25rem;
  background-color: #4F46E5;
  color: #ffffff;
  font-size: 0.875rem;
  line-height: 1.25rem;
  font-weight: 500;
  width: 100%;
  border-radius: 0.5rem;
  text-transform: uppercase;
}

.signup-link {
  color: #6B7280;
  font-size: 0.875rem;
  line-height: 1.25rem;
  text-align: center;
}

.signup-link a {
  text-decoration: underline;
}
</style>