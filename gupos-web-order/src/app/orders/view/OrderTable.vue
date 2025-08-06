<template>
  <div class="page">

    <div class="order-header-bar">
      <div>🔔<span class="fs-16 text-dark op-6 fw-semibold"> 직원호출</span></div>
      <span class="fs-16 text-dark op-7 fw-semibold">주문내역</span>
    </div>

    <!-- 상점 정보 -->
    <div class="store-header">
      <div class="store-header__info">
        <h1 class="store-header__name fs-22 fw-semibold">토스카페</h1>
        <span class="store-header__table text-dark op-5">테이블 : {{ tableName }}</span>
      </div>
      <div class="notice-banner">
        <span class="notice-banner__icon me-2">📢</span>
        <span class="notice-banner__text fs-14 text-dark op-6">{{ noticeText }}</span>
      </div>
    </div>

    <!-- 탭 네비게이션 -->
    <nav class="tab-nav">
      <ul class="tab-nav__list">
        <li v-for="(category, i) in categoryList" :key="i" :class="['tab-nav__item', { active: activeCategory === category.categoryId }]">
          <a href="#" @click.prevent="scrollToCategory(category.categoryId)">
            {{ category.categoryNm }}
          </a>
        </li>
      </ul>
    </nav>

    <!-- 콘텐츠 영역 -->
    <div class="order-content">
      <section
          v-for="category in categoryList"
          :key="category.categoryId"
          :id="'category_' + category.categoryId"
          class="product-section"
          ref="sections"
      >
        <h2 class="product-section__title">{{ category.categoryNm }}</h2>

        <div v-for="product in category.productList" :key="product.productId" @click="moveToMenu(product.productId)" class="product-card">
            <div class="product-card__info">
              <span class="badge badge--blue">인기</span>
              <h3 class="product-card__name">{{ product.productNm }}</h3>
              <p class="product-card__price">{{ formatPrice(product.productPrice) }}</p>
              <p class="product-card__desc">
                크림치즈, 햄, 토마토, 루꼴라, 로메인이 들어간 건강한 샌드위치
              </p>
            </div>
            <img src="/assets/food.jpeg" alt="샌드위치" class="product-card__image"/>
        </div>
      </section>
    </div>

    <!-- 하단 장바구니 버튼 -->
    <div class="bottom-bar">
      <a href="javascript:" class="order-btn add-cart">
        <span class="order-count">2</span>15,000원 장바구니 보기
      </a>
    </div>
  </div>
</template>
<script>

import {onBeforeUnmount, onMounted, ref} from "vue";
import { useProductCategoriesStore } from "@/app/orders/store/product_categories_store";
import {storeToRefs} from "pinia";
import router from "@/router";

// import axios from "axios";

export default {
  name: "OrderTable",
  setup() {
    const store = useProductCategoriesStore();
    const { categoryList, activeCategory, loading, error } = storeToRefs(store);

    const storeName = ref("토스 카페");
    const tableName = ref("1번 테이블");
    const noticeText = ref("1인당 1메뉴 부탁드려요 :)");
    const sections = ref([]);

    const formatPrice = (price) => `${price.toLocaleString()}원`;

    const scrollToCategory = (categoryId) => {
      const section = document.getElementById(`category_${categoryId}`);
      if (section) {
        window.scrollTo({
          top: section.offsetTop - 80,
          behavior: "smooth"
        });
      }
      store.setActiveCategory(categoryId);
    };

    const handleScroll = () => {
      sections.value.forEach((section) => {
        const sectionTop = section.offsetTop - 100;
        if (window.scrollY >= sectionTop) {
          store.setActiveCategory(Number(section.id.replace("category_", "")));
        }
      });
    };

    const moveToMenu = (menuId) => {
      // 상세 페이지로 이동
      // emit("navigate", menuId); // 부모가 라우터 푸시를 처리하게 하거나:
      // or 직접 이동:
      router.push(`/table/${tableName.value}/menu/${menuId}`);
    };

    onMounted(async () => {
      if (!categoryList.value.length) {
        await store.fetchCategories();
      }
      window.addEventListener("scroll", handleScroll);
    });

    onBeforeUnmount(() => {
      window.removeEventListener("scroll", handleScroll);
    });

    return {
      storeName,
      tableName,
      noticeText,
      categoryList,
      activeCategory,
      loading,
      error,
      sections,
      formatPrice,
      scrollToCategory,
      moveToMenu
    };
  }
};


// export default defineComponent({
//   setup() {
//     const store = useProductCategoriesStore()
//
//     const {getCategoryById} = storeToRefs(store)
//
//     return getCategoryById
//   }
// })

// export default {
//   name: "OrderTable",
//   data() {
//     return {
//       storeName: "토스 카페",
//       tableName: "1번 테이블",
//       noticeText: "1인당 1메뉴 부탁드려요 :)",
//       activeCategory: null,
//       categoryList: [],
//       isFetched: false, // ✅ 중복 호출 방지 플래그
//     };
//   },
//   async mounted() {
//     // 최초 진입 시 API 호출
//     if (!this.isFetched) {
//       await this.fetchCategoryList();
//       this.isFetched = true;
//     }
//     window.addEventListener("scroll", this.handleScroll);
//   },
//   beforeUnmount() {
//     window.removeEventListener("scroll", this.handleScroll);
//   },
//   methods: {
//     async fetchCategoryList() {
//       try {
//         // const tableId = this.$route.params.tableId; // ✅ 라우터에서 tableId 읽기
//         const response = await axios.get(`/api/v1/pos/product/categories/list`);
//         this.categoryList = response.data;
//
//         // 첫 번째 카테고리를 활성화 상태로 설정
//         this.activeCategory = this.categoryList[0]?.categoryId || null;
//       } catch (error) {
//         console.error("카테고리 API 호출 실패:", error);
//       }
//     },
//     formatPrice(price) {
//       return `${price.toLocaleString()}원`;
//     },
//     scrollToCategory(categoryId) {
//       const section = document.getElementById(`category_${categoryId}`);
//       if (section) {
//         window.scrollTo({
//           top: section.offsetTop - 80,
//           behavior: "smooth"
//         });
//       }
//     },
//     handleScroll() {
//       this.$refs.sections.forEach((section) => {
//         const sectionTop = section.offsetTop - 100;
//         if (window.scrollY >= sectionTop) {
//           this.activeCategory = Number(section.id.replace("category_", ""));
//         }
//       });
//     },
//     moveToMenu(menuId) {
//       this.$router.push(`/table/${this.tableName}/menu/${menuId}`);
//     }
//   }
// };
</script>
<style>
/* 최상단 바 */
.order-header-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.75rem;
  background: var(--white-9);
}

/*!* 상점 정보 *!*/
.store-header {
  padding: 0 1.25rem 0 1.25rem;;
  margin-top: 0.5rem;
}

.store-header__info {
  display: flex;
  justify-content: space-between;
}

.notice-banner {
  display: flex;
  align-items: center;
  background: #f7f9fa;
  padding: 0.65rem;
  border-radius: 50px;
  margin-top: 0.75rem;
}

/*!* 탭 네비게이션 (탭만 sticky) *!*/
.tab-nav {
  position: sticky;
  top: 0; /* header는 스크롤되지만 탭만 고정 */
  background: var(--custom-white);
  padding: 0 1rem 0 1rem;
  z-index: 5;
  border-bottom: 1px solid var(--default-border);
  scrollbar-width: none; /* Firefox 스크롤바 제거 */
  overflow-x: auto;
  margin-top: 1rem;
}

.tab-nav::-webkit-scrollbar {
  display: none; /* 크롬/사파리 스크롤바 제거 */
}

.tab-nav__list {
  display: flex;
  gap: 1rem;
  margin: 0;
  padding: 0 0.5rem;
  list-style: none;
  white-space: nowrap;
}

.tab-nav__item {
  cursor: pointer;
  font-size: 17px;
  font-weight: 500;
  color: rgb(var(--dark-rgb));
  opacity: 0.6;
  padding-bottom: 1rem;
  padding-top: 1rem;
  border-bottom: 2px solid transparent;
}

.tab-nav__item.active {
  border-bottom: 2px solid var(--custom-black);
  color: var(--custom-black) !important;
  opacity: 1;
  font-weight: 600;
}


/*!* 콘텐츠 *!*/
.order-content {
  /*padding: 1rem;*/
  background: var(--default-border); /* ✅ 변경: content 영역 배경색 */
  min-height: calc(50vh - 8rem);
  transition: all 0.05s ease;
}

/*!* 각 상품 섹션 *!*/
.product-section {
  margin-bottom: 1rem; /* ✅ 하단 마진 추가 */
  background: #ffffff; /* ✅ 섹션별 배경 유지 */
  padding: 1rem; /* 내부 여백 추가 (카드 스타일) */
}

.product-section__title {
  font-size: 17px;
  font-weight: 600;
  margin-bottom: 1rem;
}

.product-section__desc {
  font-size: 14px;
  color: var(--dark-rgb);
  opacity: 0.5;
}

.product-card {
  display: flex;
  gap: 1rem;
}

.product-card__image {
  width: 95px;
  height: 95px;
  border-radius: 15px;
  object-fit: cover;
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

/*!* 배지 *!*/
.badge {
  display: inline-block;
  font-size: 10px;
  padding: 4px 8px;
  border-radius: 12px;
  color: white;
  margin-bottom: 0.25rem;
}

.bottom-bar {
  position: sticky;
  display: flex;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 105px;
  /* 단색 대신 그라데이션을 배경으로 */
  background: linear-gradient(
      to bottom,
      rgba(255, 255, 255, 0.2),

      rgba(255, 255, 255, 1)
  );
  padding: 1rem 1rem 2rem 1rem;
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

.badge--green { background: #1cc88a; }
.badge--blue { background: #36a2eb; }
.badge--red { background: #e74a3b; }
</style>
