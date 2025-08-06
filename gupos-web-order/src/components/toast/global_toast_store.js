import { defineStore } from "pinia";

export const useGlobalToastStore = defineStore("global_toast", {
    state: () => ({
        message: "",
        visible: false,
        timeoutId: null,
    }),
    actions: {
        show(message, duration = 3000) {
            this.message = message;
            this.visible = true;

            // 기존 타이머 제거 후 새 타이머 설정
            if (this.timeoutId) clearTimeout(this.timeoutId);
            this.timeoutId = setTimeout(() => {
                this.hide();
            }, duration);
        },
        hide() {
            this.visible = false;
            this.message = "";
            if (this.timeoutId) clearTimeout(this.timeoutId);
        },
    },
});