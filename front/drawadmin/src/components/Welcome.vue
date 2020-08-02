<template>
    <h3>Welcome {{userName}} 登录抽奖管理端!</h3>
</template>

<script>
export default {
    data() {
        return {
            userName: ''
        }
    },
    created() {
        this.userName = sessionStorage.getItem('token');
    }
}
</script>

<style lang="scss" scoped>

</style>