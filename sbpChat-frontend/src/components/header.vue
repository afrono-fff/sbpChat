<template>
  <div>
    <el-affix :offset="10">
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        style="margin-top: -20px;"
        mode="horizontal"
        @select="handleSelect"
        background-color="rgba(255, 255, 255, 0.55)" 
        router
      >
        <el-menu-item @click="drawer=true">Friends</el-menu-item>
        <el-menu-item index="4">Groups</el-menu-item>
      </el-menu>
    </el-affix>
    <el-drawer
      v-model="drawer"
      direction='ltr'
      :before-close="handleClose"
      :modal=true
    >
      
    </el-drawer>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userStore'
import { ref } from 'vue';
const userStore = useUserStore()
const drawer = ref()
</script>



<style scoped>

</style>