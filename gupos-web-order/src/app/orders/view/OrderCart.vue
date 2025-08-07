<template>
  <div class="page">
    <SubHeaderBar title="장바구니"></SubHeaderBar>

    <div class="main-layout">

      <div class="contents">

        <section class="product-section">
          <h2 class="product-section__title">내 메뉴</h2>

          <div
              v-for="(cartProduct, index) in cart.productList" :key="index"
              class="product-content"
          >
            <div class="product-card">
              <div class="product-card__info">
                <h3 class="product-card__name">{{cartProduct.product.productNm}}</h3>
                <p class="product-card__price">{{ totalPrice(cartProduct) }}원</p>
                <p class="product-card__desc">
                  {{ cartProduct.options.map(option => `${option.productOptionNm}(+${option.optionPrice}원)`).join(', ') }}
                </p>
              </div>
              <div class="product-remove">
                <button class="remove-btn" aria-label="Remove item" @click="remove(index)">
                  <svg class="remove-icon" viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="white" stroke-width="4" stroke-linecap="round" stroke-linejoin="round"><line x1="18" y1="6" x2="6" y2="18" /><line x1="6" y1="6" x2="18" y2="18" /></svg>
                </button>
              </div>
            </div>
            <div class="product-detail__price">
              <span class="option-edit-btn">옵션 변경</span>
              <div class="quantity-control">
                <button class="quantity-btn" @click="decreaseQuantity(index)">-</button>
                <div class="quantity-value-box">
                  <span class="fs-14 fw-semibold">{{cartProduct.quantity}}</span>
                </div>
                <button class="quantity-btn" @click="increaseQuantity(index)">+</button>
              </div>
            </div>
          </div>

        </section>

      </div>

      <div class="product-section-bottom"></div>
    </div>
    <!-- 하단 주문 버튼 -->
    <div class="bottom-bar">
      <a href="javascript:" class="order-btn">
        <span class="order-count">{{cartTotalQuantity}}</span>{{ formatPrice(cartTotalPrice) }} 주문하기
      </a>
    </div>
  </div>
</template>
<script setup>
import {computed, defineOptions} from 'vue';
import SubHeaderBar from "@/components/layout/SubHeaderBar.vue";
import { useCartStore } from "@/app/orders/store/cart_store";

defineOptions({
  name: "OrderCart"
});

const cart = useCartStore();

const formatPrice = (price) => `${price.toLocaleString()}원`;

const totalPrice = (cartProduct) => {
  if (!cartProduct.product) return 0;
  const optionsPrice = cartProduct.options?.reduce((sum, option) => {
    return sum + option.optionPrice;
  }, 0) ?? 0;
  return (cartProduct.product.productPrice + optionsPrice) * cartProduct.quantity;
};

// 장바구니 총 수량 합계
const cartTotalQuantity = computed(() =>
    cart.productList.reduce((sum, item) => sum + (item.quantity || 1), 0)
);

// 장바구니 총 가격 계산
const cartTotalPrice = computed(() =>
    cart.productList.reduce((sum, item) => {
      // 옵션 가격 합산
      const optionsPrice = item.options
          ? item.options.reduce((optSum, opt) => optSum + (opt.optionPrice || 0), 0)
          : 0;
      return sum + (item.product.productPrice + optionsPrice) * item.quantity;
    }, 0)
);

const increaseQuantity = (index) => {
  cart.increaseQuantity(index);
};

const decreaseQuantity = (index) => {
  cart.decreaseQuantity(index);
};

const remove = (index) => {
  console.log("아이디 :" ,index)
  cart.delete(index)
}

// const formatPrice = (value) => `${value.toLocaleString()}원`;

</script>
<style scoped>
.main-layout {
  display: flex;
  flex-direction: column;
  flex: 1;
  overflow: hidden;
}

  .contents {
    flex: 0 0 auto;
    display: block;
    background: var(--default-border); /* ✅ 변경: content 영역 배경색 */
  }

  /*!* 각 상품 섹션 *!*/
  .product-section {
    margin-bottom: 1rem; /* ✅ 하단 마진 추가 */
    background: #ffffff; /* ✅ 섹션별 배경 유지 */
    padding: 1rem; /* 내부 여백 추가 (카드 스타일) */
  }
  .product-section-bottom {
    display: block;
    background: var(--custom-white); /* ✅ 변경: content 영역 배경색 */
  }

  .product-section__title {
    font-size: 17px;
    font-weight: 600;
    margin-bottom: 1rem;
  }

  .product-card {
    display: flex;

  }

  .product-content {
    margin-bottom: 1.25rem;
  }

  .product-remove {
    display: flex;
    align-items: start;
    justify-content: center;
  }

  .remove-btn {
    width: 32px;
    height: 32px;
    border: none;
    border-radius: 50%;
    background: rgb(178, 184, 193);
    color: #fff;
    font-size: 20px;
    font-weight: bold;
    line-height: 1;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 0;
  }

  .remove-icon {
    pointer-events: none; /* 클릭 영역은 button만 */
  }

  .product-card__info {
    flex: 1;
  }

  .product-card__name {
    display: flex;
    align-items: center;
    gap: 1rem;
    font-size: 17px;
  }

  .product-card__price {
    font-size: 14px;
    font-weight: 600;
  }

  .product-card__desc {
    font-size: 14px;
    color: var(--dark-rgb);
    opacity: 0.5;
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

  .product-detail__price {
    display: flex;
    justify-content: flex-end; /* 오른쪽 정렬 */
    align-items: center;
    margin-top: 0.5rem;
    gap: 1rem;
  }

  /* 수량 조절 */
  .quantity-control {
    display: flex;
    align-items: center;
    background: #f3f4f6;
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

  .option-edit-btn {
    height: 50px;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    padding: 0 16px;
    font-size: 16px;
    font-weight: 500;
    background: #f3f4f6;
    box-shadow: 0 0 2px var(--default-border);
    border-radius: 8px;
    cursor: pointer;
  }
</style>