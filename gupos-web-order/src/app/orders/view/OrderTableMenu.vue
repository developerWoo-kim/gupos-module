<template>
  <div class="page" v-if="product">
    <!-- 상단 헤더 -->
    <div class="header-bar">
      <div class="order-header-bar">
        <a @click.prevent="goBack" class="back-angle"></a>
        <span class="fs-16 text-dark op-7 fw-semibold">주문내역</span>
      </div>
    </div>

    <!-- 상품 정보 -->
    <div class="product-content">
      <img :src="productImage" :alt="product.productNm" class="product-image" />
      <div class="product-detail">
        <h1 class="fs-22 fw-semibold">{{ product.productNm }}</h1>
        <span class="fs-17 fw-semibold text-dark op-4">
          {{ product.description }}
        </span>

        <div class="product-detail__price">
          <span class="fs-22 fw-semibold text-black op-7">{{ formatPrice(product.productPrice) }}</span>
          <div class="quantity-control">
            <button class="quantity-btn" @click="decreaseQuantity">-</button>
            <div class="quantity-value-box">
              <span class="fs-14 fw-semibold">{{ quantity }}</span>
            </div>
            <button class="quantity-btn" @click="increaseQuantity">+</button>
          </div>
        </div>
      </div>
    </div>

    <!-- 옵션 영역 -->
    <div v-if="product.optionGroupList?.length" class="product-option-content">
      <section
          v-for="(optionGroup, groupIndex) in product.optionGroupList"
          :key="groupIndex"
          class="product-option-section"
      >
        <div class="product-option-section-title">
          <h2 class="product-option-section-title__name">
            {{ optionGroup.productOptionGroupNm }}
          </h2>
          <span class="product-option-section-title__select"
                :class="{ required: optionGroup.requiredAt === 'Y' }">
            {{ optionGroup.requiredAt === 'Y' ? '필수' : '선택' }}
          </span>
        </div>
        <span class="fs-12 text-muted op-8">최대 {{ optionGroup.maxSelectionCount }}개</span>

        <div class="options-section">
          <label
              class="product-option"
              v-for="(option, optionIndex) in optionGroup.optionList"
              :key="optionIndex"
          >
            <div class="product-option__info">
              <span class="fs-16 text-dark op-6">{{ option.productOptionNm }}</span>
              <span class="fs-15 text-dark op-6">+{{ formatPrice(option.optionPrice) }}</span>
            </div>
            <div class="form-check form-check-lg">
              <input
                  class="form-check-input"
                  type="checkbox"
                  :value="option"
                  v-model="selectedOptions"
              />
            </div>
          </label>
        </div>
      </section>
    </div>

    <!-- 하단 주문 버튼 -->
    <div class="bottom-bar">
      <a href="javascript:" class="order-btn add-cart" @click.prevent="addToCart">
        <span class="order-count">{{ quantity }}</span>{{ totalPrice }}원 담기
      </a>
    </div>
  </div>

  <!-- 상품을 못 찾았을 때 -->
  <div v-else class="page">
    <div class="empty-message">
      <p>해당 상품을 찾을 수 없습니다.</p>
      <a @click.prevent="goBack" class="back-angle">← 돌아가기</a>
    </div>
  </div>
</template>

<script>
import { ref, computed, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useProductCategoriesStore } from "@/app/orders/store/product_categories_store";
import { useCartStore } from "@/app/orders/store/cart_store";
import { useGlobalToastStore } from "@/components/toast/global_toast_store";
const toast = useGlobalToastStore();
const store = useProductCategoriesStore();
const cart = useCartStore();

export default {
  name: "OrderTableMenu",
  setup() {
    const route = useRoute();
    const router = useRouter();

    const menuId = route.params.menuId;
    const quantity = ref(1);
    const selectedOptions = ref([]);

    const product = computed(() => store.findProductById(menuId));

    const productImage = computed(() =>
        // product.value?.imageUrl || "/assets/images/admin/food.jpeg"
        "/assets/food.jpeg"
    );

    // const findOptionById = (optionId) => {
    //   if (!product.value?.optionGroupList) return null;
    //   for (const group of product.value.optionGroupList) {
    //     const option = group.optionList.find((opt) => opt.productOptionId === optionId);
    //     if (option) return option;
    //   }
    //   return null;
    // };

    const totalPrice = computed(() => {
      if (!product.value) return 0;
      const optionsPrice = selectedOptions.value.reduce((sum, option) => {
        return sum + option.optionPrice;
        // return sum + (option ? option.optionPrice : 0);
      }, 0);
      return (product.value.productPrice + optionsPrice) * quantity.value;
    });

    const formatPrice = (value) => `${value.toLocaleString()}원`;

    const increaseQuantity = () => quantity.value++;

    const decreaseQuantity = () => {
      if (quantity.value > 1) quantity.value--;
    };

    const goBack = () => router.back();

    const addToCart = () => {
      console.log("장바구니 추가:", {
        product: product.value,
        quantity: quantity.value,
        options: selectedOptions.value,
      });

      cart.add({
        product: product.value,
        quantity: quantity.value,
        options: selectedOptions.value,
      })

      toast.show("✅ 장바구니에 메뉴를 추가했어요", 2500)

      goBack()
    };

    onMounted(async () => {
      if (!store.categoryList.length) {
        await store.fetchCategories();
      }
    });

    return {
      product,
      productImage,
      quantity,
      selectedOptions,
      totalPrice,
      formatPrice,
      increaseQuantity,
      decreaseQuantity,
      addToCart,
      goBack,
    };
  },
};
</script>
<style scoped>
  .header-bar {
    position: sticky;
    top: 0;
    background: var(--custom-white);
    z-index: 5;
    border-bottom: 1px solid var(--default-border);
    scrollbar-width: none; /* Firefox 스크롤바 제거 */
    overflow-x: auto;
  }

  /* 최상단 바 */
  .order-header-bar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0.75rem;
    background: var(--white-9);
  }

  .product-content {
    flex: 1;
    display: block;
  }

  .product-image {
    width: 100%;
    height: 250px;
    object-fit: cover;
  }

  /*!* 상점 정보 *!*/
  .product-detail {
    padding: 0 1.25rem 1.25rem 1.25rem;;
    margin-top: 1.5rem;
  }

  .product-detail__price {
    display: flex;
    justify-content: space-between;
    align-items: baseline;
    margin-top: 1.5rem;
  }

  /*!* 콘텐츠 *!*/
  .product-option-content {
    padding-top: 1rem;
    background: var(--default-border); /* ✅ 변경: content 영역 배경색 */
    /*min-height: calc(50vh - 8rem);*/
    transition: all 0.05s ease;
  }

  /*!* 각 상품 섹션 *!*/
  .product-option-section {
    /*margin-bottom: 1rem; !* ✅ 하단 마진 추가 *!*/
    background: #ffffff; /* ✅ 섹션별 배경 유지 */
    padding: 1.25rem 1rem; /* 내부 여백 추가 (카드 스타일) */
  }

  /* 마우스 올렸을 때 */
  /*.product-option:hover {*/
  /*    background-color: rgba(0, 0, 0, 0.04); !* 연한 회색 배경 *!*/
  /*}*/

  /* 클릭 중(눌렸을 때) */
  .product-option:active {
    background-color: rgba(0, 0, 0, 0.08); /* 더 진한 회색 */
    transform: scale(0.98); /* 살짝 눌림 효과 */
    transition: transform 0.05s ease;
  }

  .product-option-section-title {
    display: flex;
    justify-content: start;
    align-items: center; /* ✅ 수직 정렬을 상단으로 고정 */
    gap: 2px; /* name과 select 사이 여백 */
    margin-bottom: 0.25rem;
  }

  .product-option-section-title__name {
    font-size: 19px;
    font-weight: 600;
    margin-bottom: 0; /* ✅ margin-bottom 제거 (flex 내에서 간격 문제 방지) */
  }

  .product-option-section-title__select {
    font-size: 12px;
    font-weight: 500;
    color: rgb(var(--info-rgb));
    background: #f2f8ff;
    padding: 3px 10px;
    border-radius: 12px;
    flex-shrink: 0; /* ✅ select는 크기 줄어들지 않고 유지 */
  }

  .options-section {
    display: flex;
    flex-direction: column; /* 세로 정렬 */
    gap: 0.75rem; /* ✅ 각 product-option 사이 간격 */
    margin-top: 1rem;
  }

  .bottom-bar {
    position: sticky;
    display: flex;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 120px;
    /* 단색 대신 그라데이션을 배경으로 */
    background: linear-gradient(
        to bottom,
        rgba(255, 255, 255, 0.2),
        rgba(255, 255, 255, 0.5),
        rgba(255, 255, 255, 0.8),
        rgba(255, 255, 255, 1)
    );
    padding: 2rem 1rem;
  }

  .order-btn {
    display: flex;
    width: 100%;
    height: 100%;
    background: rgb(var(--info-rgb));
    color: #fff;
    border: none;
    border-radius: 15px;
    font-size: 18px;
    font-weight: 600;
    cursor: pointer;
    justify-content: center;
    align-items: center; /* ✅ 수직 정렬을 상단으로 고정 */
    text-align: center;
  }

  .order-count {
    background: var(--custom-white);
    padding: 4px 11px;
    font-size: 13px;
    font-weight: 700;
    border-radius: 10px;
    color: rgb(var(--info-rgb));
    margin-right: 0.5rem;
  }

  .product-option {
    display: flex;
    justify-content: space-between;
    align-items: center; /* ✅ 수직 정렬을 상단으로 고정 */
    gap: 2px; /* name과 select 사이 여백 */
    margin-bottom: 0.25rem;
  }

  .product-option__info {
    display: flex;
    flex-direction: column;
  }

  /* 수량 조절 */
  .quantity-control {
    display: flex;
    align-items: center;
    background: #f3f4f6;
    /*border: 1px solid var(--default-border);*/
    border-radius: 10px;
    overflow: hidden;
  }

  .quantity-btn {
    width: 40px;
    height: 50px;
    border: none;
    background: none;
    font-size: 16px;
    cursor: pointer;
  }

  .quantity-value-box {
    display: flex;
    width: 55px;
    height: 40px;
    font-size: 16px;
    justify-content: center;
    align-items: center;
    border-radius: 10px;
    box-shadow: 2px 2px 2px var(--default-border);
    background: var(--custom-white);
  }

  .back-angle {
    width: 24px;
    height: 24px;
    background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24'%3E%3Cpath d='M15 19l-7-7 7-7' stroke='%23000' stroke-width='3' fill='none' stroke-linecap='round' stroke-linejoin='round'/%3E%3C/svg%3E");
    background-repeat: no-repeat;
    background-position: center;
    background-size: contain;
    cursor: pointer;
  }
</style>