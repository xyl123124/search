<template style="width: 150%">
  <a-config-provider
    :theme="{
      token: {
        colorPrimary: '#eea6b7',
      },
    }"
  >
    <div
      v-masonry
      ref="masonryContainer"
      fit-width="true"
      transition-duration="0.3s"
      item-selector=".card"
      origin-left="false"
      style="width: 100%"
      class="pics"
      @layout-complete="checkImgLoaded"
    >
      <div
        v-masonry-tile
        v-for="(item, index) in props.pictureList"
        :class="`demo-${item}`"
        :key="index"
        class="card"
      >
        <a-card>
          <template #cover>
            <img :src="item.url" />
          </template>
          <div class="card-title">{{ item.title }}</div>
        </a-card>
      </div>
    </div>
    <!--    <a-pagination-->
    <!--      v-model:current="current"-->
    <!--      :total="50"-->
    <!--      show-less-items-->
    <!--      align="right"-->
    <!--      :v-show="showPagination"-->
    <!--      style="margin-top: 10px"-->
    <!--    />-->
  </a-config-provider>
</template>

<script setup lang="ts">
import { withDefaults, defineProps, ref, onMounted } from "vue";
interface Props {
  pictureList: any[];
}

const props = withDefaults(defineProps<Props>(), {
  pictureList: () => [],
});
// const showPagination = ref(false);
//
// const masonryContainer = ref<HTMLDivElement>(null);
//
// onMounted(() => {
//   const imgElements = masonryContainer.value?.querySelectorAll("img");
//   const isAllImagesLoaded = Array.from(imgElements!).every(
//     (img) => img.complete
//   );
//
//   if (isAllImagesLoaded) {
//     console.log("所有图片加载完成");
//     //showPagination.value = true;
//   } else {
//     console.log("还有图片未加载完成");
//   }
// });
</script>

<style scoped>
.pics {
  width: 100%;
  margin: 0 auto;
}
.gutter-sizer {
  width: 4% !important;
}
.card {
  width: 100%;
  max-width: 200px;
  margin: 0.25em;
  border-radius: 5px;
  box-shadow: 0px 2px 10px 1px rgba(0, 0, 0, 0.1);
}
.card img {
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}
.card-title {
  overflow-wrap: break-word;
  word-wrap: break-word;
  width: 100%;
  color: rgba(0, 0, 0, 0.88);
  /* font-weight: 500; */
  font-size: 5px;
  overflow: hidden;
  display: -webkit-box; /* 设置为块级弹性盒子元素 */
  -webkit-box-orient: vertical; /* 设置为垂直排列 */
  text-overflow: ellipsis; /* 使用省略号表示溢出部分 */
  -webkit-line-clamp: 2; /* 限制显示两行文本 */
}
/* 卡片内部下边距 */
:where(.css-dev-only-do-not-override-b92jn9).ant-card .ant-card-body {
  padding: 10px;
  border-radius: 0 0 8px 8px;
}
</style>
