<template>
    <div class="common-layout">
        <el-container>
        <el-header>
            <Header></Header>
        </el-header>
        <el-container>
            <el-aside width="200px">Aside</el-aside>
            <el-container>
            <el-main>
                <Main></Main>
            </el-main>
            <el-footer>Footer</el-footer>
            </el-container>
        </el-container>
        </el-container>
    </div>
</template>

<script setup>
import Header from "@/components/header.vue"
import Main from "./components/chat.vue"
</script>