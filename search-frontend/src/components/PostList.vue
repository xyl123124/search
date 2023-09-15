<template>
  <a-config-provider
    :theme="{
      token: {
        colorPrimary: '#eea6b7',
      },
    }"
  >
    <a-list
      item-layout="horizontal"
      :pagination="pagination"
      :data-source="props.postList"
    >
      <template #renderItem="{ item }">
        <a-list-item>
          <a-list-item-meta>
            <template #title>
              <a href="https://www.antdv.com/" v-html="item.title"></a>
            </template>
            <template #avatar>
              <a-avatar :src="birds" />
            </template>
            <template #description>
              <div v-html="item.content" style="margin-bottom: 10px"></div>
              <template v-for="tag in item.tagList" :key="tag">
                <a-tag>
                  {{ tag }}
                </a-tag>
              </template>
            </template>
          </a-list-item-meta>
        </a-list-item>
      </template>
    </a-list>
  </a-config-provider>
</template>

<script setup lang="ts">
import birds from "@/assets/birds.jpg";
import { withDefaults, defineProps, ref, onMounted, watchEffect } from "vue";
import { useRoute, useRouter } from "vue-router";
const route = useRoute();
const router = useRouter();
//console.log(route.query.current);
const pagination = {
  onChange: (page: number) => {
    console.log(page);
    pagination.current = page;
    const query = { ...route.query };
    // 修改query参数中的某一项
    query.pageNum = page.toString();
    // 将修改后的query参数更新到URL中
    router.replace({ query });
  },
  pageSize: 10,
  hideOnSinglePage: true,
  //current: ref(parseInt(route.query.pageNum)),
};

onMounted(() => {
  if (route.query.pageNum != null) {
    pagination.onChange(parseInt(route.query.pageNum));
    //pagination.current = parseInt(route.query.pageNum);
  }
});
interface Props {
  postList: any[];
}

const props = withDefaults(defineProps<Props>(), {
  postList: () => [],
});
</script>

<style scoped>
.birds {
  width: 500px;
}
/* 文章标题触摸点击颜色 */
/*:where(.css-dev-only-do-not-override-b92jn9).ant-list*/
/*  .ant-list-item*/
/*  .ant-list-item-meta*/
/*  .ant-list-item-meta-title*/
/*  > a:hover {*/
/*  color: #eea6b7;*/
/*}*/
/*:where(.css-dev-only-do-not-override-b92jn9).ant-pagination*/
/*  .ant-pagination-item-active {*/
/*  font-weight: 600;*/
/*  background-color: #ffffff;*/
/*  border-color: #eea6b7 !important;*/
/*}*/
/*.ant-pagination-item {*/
/*  color: #eea6b7;*/
/*}*/
/*.test.ant-pagination .ant-pagination-item-active {*/
/*  font-weight: 600;*/
/*  background-color: #ffffff;*/
/*  border-color: #eea6b7;*/
/*}*/
</style>
