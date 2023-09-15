<template>
  <a-config-provider
    :theme="{
      token: {
        colorPrimary: '#eea6b7',
      },
    }"
  >
    <a-list
      :pagination="pagination"
      :grid="{ gutter: 30, column: 2 }"
      item-layout="horizontal"
      :data-source="props.userList"
    >
      <template #renderItem="{ item }">
        <a-list-item
          style="display: flex; justify-content: start; display: flex"
        >
          <!--          <a-card hoverable style="width: 200px; height: 240px">-->
          <!--            <template #cover>-->
          <!--              <img :src="item.userAvatar" />-->
          <!--            </template>-->
          <!--            <a-card-meta :title="item.userName">-->
          <!--              <template #description>{{ item.userProfile }}</template>-->
          <!--            </a-card-meta>-->
          <!--          </a-card>-->
          <a-avatar :size="80">
            <template #icon
              ><img :src="item.userAvatar ? item.userAvatar : birds"
            /></template>
          </a-avatar>
          <div style="margin-left: 15px">
            <p style="font-size: 18px; font-weight: bold; padding-top: 15px">
              {{ item.userName }}
            </p>
            <p style="font-size: 14px; color: gray; margin-top: -15px">
              {{ item.userProfile }}
            </p>
          </div>
        </a-list-item>
      </template>
    </a-list>
  </a-config-provider>
</template>

<script setup lang="ts">
import birds from "@/assets/birds.jpg";
import { withDefaults, defineProps, onMounted } from "vue";
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
  userList: any[];
}

const props = withDefaults(defineProps<Props>(), {
  userList: () => [],
});
</script>

<style scoped>
.birds {
  width: 150px;
}
/*.profile {*/
/*  display: flex;*/
/*  align-items: center;*/
/*}*/

/*.avatar {*/
/*  width: 100px;*/
/*  height: 100px;*/
/*}*/

/*.avatar img {*/
/*  width: 100%;*/
/*  height: 100%;*/
/*  object-fit: cover;*/
/*}*/

/*.info {*/
/*  margin-left: 10px;*/
/*}*/

/*.username {*/
/*  font-size: 18px;*/
/*  font-weight: bold;*/
/*}*/

/*.signature {*/
/*  font-size: 14px;*/
/*  color: gray;*/
/*}*/
</style>
