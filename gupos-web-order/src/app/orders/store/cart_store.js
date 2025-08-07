import { defineStore} from "pinia";

export const useCartStore = defineStore("cart_store", {
    state: () => ({
        productList: [],
    }),
    actions: {
        add(product) {
            this.productList.push(product)
        },
        delete(index) {
            this.productList.splice(index, 1);
        },
        increaseQuantity(index) {
            if (this.productList[index]) {
                this.productList[index].quantity += 1;
            }
        },
        decreaseQuantity(index) {
            if (this.productList[index] && this.productList[index].quantity > 1) {
                this.productList[index].quantity -= 1;
            }
        },
    },

    // ✨ persist 설정 추가
    persist: {
        enabled: true,
        strategies: [
            {
                storage: localStorage, // 또는 sessionStorage
                paths: ['productList'], // 저장하고 싶은 상태만 지정
            }
        ]
    }
})