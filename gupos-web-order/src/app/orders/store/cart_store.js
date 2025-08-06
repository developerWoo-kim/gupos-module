import { defineStore} from "pinia";

export const useCartStore = defineStore("cart_store", {
    state: () => ({
        productList: [],
    }),
    actions: {
        add(product) {
            this.productList.push(product)
        },
        delete(productId) {
            this.productList = this.productList.filter(
                product => product.productId !== productId
            )
        }
    }
})