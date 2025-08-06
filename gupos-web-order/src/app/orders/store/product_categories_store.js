import {defineStore} from "pinia";
import axios from "axios";

export const useProductCategoriesStore = defineStore('product_categories', {
    state: () => ({
        categoryList: [],
        activeCategory: null,
        loading: false,
        error: null,
    }),
    actions: {
        async fetchCategories() {
            this.loading = true;
            this.error = null;
            try {
                const { data } = await axios.get(`/api/v1/pos/product/categories/list`);
                this.categoryList = data;
                this.activeCategory = data[0]?.categoryId || null;
            } catch (err) {
                console.error("카테고리 API 호출 실패:", err);
                this.error = err;
            } finally {
                this.loading = false;
            }
        },
        setActiveCategory(categoryId) {
            this.activeCategory = categoryId;
        }
    },
    getters: {
        findProductById: (state) => (menuId) => {
            for (const category of state.categoryList) {
                const product = category.productList.find(
                    (p) => p.productId === Number(menuId)
                );
                if (product) return product;
            }
            return null;
        }
    }
})