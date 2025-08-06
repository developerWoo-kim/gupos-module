<template>
  <div>
    <h1>Main</h1>
    <button type="button" @click="onClickMoveTestPage">이동</button>
  </div>
</template>
<script>
export default {
  methods: {
    onClickMoveTestPage() {
      this.$router.push('/test')
    }
  }
}</script>
<style></style>
