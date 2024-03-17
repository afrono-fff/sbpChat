<template>
    <div>
        <el-form class="form" :model="form" ref="formRef">
        <p class="form-title">Sign up a new account</p>
            <div class="input-container">
            <input type="username" v-model="form.username" placeholder="Enter username">
            <span>
            </span>
        </div>
        <div class="input-container">
            <input type="password" v-model="form.password" placeholder="Enter password">
        </div>
        <div class="input-container">
            <input type="password" v-model="form.confirm" placeholder="Confirm password">
        </div>
        <div class="input-container">
            <input type="email" v-model="form.email" placeholder="Email">
        </div>
            <button type="button" @click="confirm" class="submit">
            Sign up
        </button>

        <p class="signup-link">
            Already have an account?
            <a @click="gotoSignin" style="cursor: pointer;">Sign in</a>
        </p>
    </el-form>

    </div>
</template>

<script setup>
import router from "@/router"
import { ElNotification } from "element-plus";
import { h, ref } from "vue"
import httpInstance from "../../utils/http";

const form = ref({
  username: '',
  password: '',
  confirm: '',
  email: ''
})
const rules = {
  username: [
    { required: true, message: 'Username CAN\'T be blanket' , trigger: 'blur' }
  ],
  password: [
    { required: true, message: 'Password CAN\'T be blanket', trigger: 'blur' },
    { min: 6, max: 14, message: 'Limit your password to 6-14 characters', trigger: 'blur' },
  ],
  confirm: [],
  email: []
}
const formRef = ref(null)
const confirm = () => {
  const { username, password, confirm, email } = form.value
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
  }else if(confirm == ''){
    // ElMessage({ type: 'warning', message: 'Confirm your password' })
    ElNotification({
      title: 'Warning',
      message: h('i', { style: 'color: teal' }, 'Confirm your password'),
    })
  }else if(email == ''){
    // ElMessage({ type: 'warning', message: 'Email CAN\'T be blanket' })
    ElNotification({
      title: 'Warning',
      message: h('i', { style: 'color: teal' }, 'Email CAN\'T be blanket'),
    })
  }else{
    httpInstance.post('user_signup', {
      username: form.value.username,
      password: form.value.password,
      confirm: form.value.confirm,
      email: form.value.email
    }).then(res => {
      console.log(res.data)
      if(res.data.code === 200){
        // ElMessage({ type: 'success', message: 'Successfully signed up' })
        ElNotification({
          title: 'Success',
          message: h('i', { style: 'color: teal' }, 'Successfully signed up'),
          type: 'success'
        })
        router.replace('/about')
      }else if(res.data.code === 10003){
        // ElMessage({ type: 'error', message: 'Duplicated username' })
        ElNotification({
          title: 'Warning',
          message: h('i', { style: 'color: teal' }, 'Duplicated username'),
        })
      }else if(res.data.code === 10004){
        // ElMessage({ type: 'error', message: 'Limit your password to 8-16 characters containing both letter and digit' })
        ElNotification({
          title: 'Warning',
          message: h('i', { style: 'color: teal' }, 'Limit your password to 8-16 characters containing both letter and digit'),
        })
      }else if(res.data.code === 10005){
        // ElMessage({ type: 'error', message: 'Different confirm password' })
        ElNotification({
          title: 'Warning',
          message: h('i', { style: 'color: teal' }, 'Different confirm password'),
        })
      }else if(res.data.code === 10006){
        // ElMessage({ type: 'error', message: 'Invalid email' })
        ElNotification({
          title: 'Warning',
          message: h('i', { style: 'color: teal' }, 'Invalid email'),
        })
      }
    })
    
  }
}

const gotoSignin = () => {
    console.log("signin");
    router.push("/signin")
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