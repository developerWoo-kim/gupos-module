<template>
  <div v-if="toast.visible" id="toast" :class="{ show: toast.visible }">
    {{ toast.message }}
  </div>
</template>
<script setup>
import { useGlobalToastStore } from "@/components/toast/global_toast_store";
const toast = useGlobalToastStore();
</script>

<style scoped>
  #toast {
    visibility: hidden; /* Hidden by default */
    min-width: 250px;
    margin-left: -125px; /* Center it horizontally */
    font-size: 15px;
    background-color: #8c94a0;
    color: #fff;
    text-align: center;
    border-radius: 30px;
    padding: 14px;
    position: fixed;
    z-index: 1;
    left: 50%;
    bottom: 30px;
    opacity: 0; /* For fade-in/out animation */
    transition: opacity 0.5s ease-in-out, transform 0.5s ease-in-out; /* For smooth transitions */
  }
  #toast.show {
    visibility: visible;
    animation: slidein 0.5s forwards; /* Use the keyframe animation */
  }
  @keyframes slidein {
    from {
      bottom: 0;
      opacity: 0;
    }
    to {
      bottom: 60px;
      opacity: 1;
    }
  }
</style>