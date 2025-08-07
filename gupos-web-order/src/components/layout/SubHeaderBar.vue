<template>
  <div class="header-bar">
    <div class="left">
      <a @click.prevent="goBack" class="back-angle"></a>
    </div>
    <div class="center">
      <span class="fs-22">{{ props.title }}</span>
    </div>
    <div class="right">
      <slot name="right" />
    </div>
  </div>
</template>

<script setup>
  import { useRouter } from 'vue-router'

  const props = defineProps({
    title: String,
  })
  console.log('받은 title:', props.title)
  const router = useRouter()

  function goBack() {
    router.back()
  }
</script>

<style scoped>
.header-bar {
  position: relative;
  height: 60px;
  background: var(--custom-white);
}

.left {
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  padding-left: 14px;
}

.right {
  position: absolute;
  right: 0;
  top: 50%;
  transform: translateY(-50%);
  padding-right: 10px;
}

.center {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.back-angle {
  display: inline-block;
  vertical-align: middle;
  width: 24px;
  height: 24px;
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24'%3E%3Cpath d='M15 19l-7-7 7-7' stroke='%23000' stroke-width='3' fill='none' stroke-linecap='round' stroke-linejoin='round'/%3E%3C/svg%3E");
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
  cursor: pointer;
}
</style>