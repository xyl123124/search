<template>
  <a-config-provider
    :theme="{
      token: {
        colorPrimary: '#eea6b7',
      },
    }">
    <div>
      <div class="global-search-wrapper" style="width: 100%">
        <a-auto-complete
          v-model:value="searchParams.text"
          @select="urlChange"
          :options="options"
          style="width: 100%"
        >
          <a-input-search
            v-model:value="searchParams.text"
            placeholder="input search text"
            enter-button="Search"
            size="large"
            @search="urlChange"
          />
        </a-auto-complete>
      </div>

      <MyDivider />
      <a-tabs
        v-model:activeKey="activeKey"
        @change="urlChange"
        style="lighting-color: #eea6b7"
      >
        <a-tab-pane key="post" tab="文章">
          <PostList :post-list="postList" />
        </a-tab-pane>
        <a-tab-pane key="picture" tab="图片">
          <a-spin :spinning="spinning" id="spin" />
          <PictureList :picture-list="pictureList" :v-if="!spinning" />
        </a-tab-pane>
        <a-tab-pane key="user" tab="用户">
          <UserList :user-list="userList" />
        </a-tab-pane>
      </a-tabs>
    </div>
    <a-float-button
      shape="square"
      description="HELP"
      class="pink"
      :style="{
        right: '164px',
        color: '#eea6b7',
      }"
      @click="showDrawer"
    >
      <template #icon>
        <img
          :src="require('@/assets/logo.png')"
          style="width: 25px; height: 25px"
        />
      </template>
    </a-float-button>
    <a-drawer
      width="640"
      placement="right"
      :closable="true"
      :open="open"
      @close="onClose"
      title="BFL聚合搜索平台-项目说明"
    >
      <div class="container">
        <h2>项目介绍</h2>
        <br />
        <pre class="text">{{ intro }}</pre>
        <br />
        <h2>主要工作</h2>
        <br />
        <h3>前端</h3>
        <pre class="text">{{ front }}</pre>
        <h3>后端</h3>
        <pre class="text">{{ backend }}</pre>
      </div>
      <div>
        <a-divider />
        <h2>效果展示</h2>
        <br />
        <p>1. 文章</p>
        <a-image-preview-group>
          <a-image :src="require('@/assets/display1.png')" class="display" />
          <p>2. 图片</p>
          <a-image :src="require('@/assets/display2.png')" class="display" />
          <p>3. 用户</p>
          <a-image :src="require('@/assets/display3.png')" class="display" />
        </a-image-preview-group>
      </div>
    </a-drawer>
  </a-config-provider>
</template>

<script setup lang="ts">
import PostList from "@/components/PostList.vue";
import PictureList from "@/components/PictureList.vue";
import UserList from "@/components/UserList.vue";
import MyDivider from "@/components/MyDivider.vue";
import { ref, watchEffect, onMounted, watch } from "vue";
import { useRouter, useRoute } from "vue-router";
import myAxios from "@/plugins/myAxios";

interface Option {
  value: string;
}
const filterOption = (input: string, option: Option) => {
  return option.value.toUpperCase().indexOf(input.toUpperCase()) >= 0;
};
const value = ref("");
const options = ref<Option[]>([]);

const open = ref<boolean>(false);
const spinning = ref<boolean>(true);
const router = useRouter();
const route = useRoute();
const activeKey = ref(route.params.category);
const initSearchParams = {
  text: "",
  pageSize: 100,
  pageNum: 1,
};
const searchParams = ref(initSearchParams);
const postList = ref();
const pictureList = ref();
const userList = ref();

// 说明
const intro = ref(
  "基于 Spring Boot + Elastic Stack + Vue3 的一站式信息聚合搜索平台，用户可在同一页面集中搜索出不同来源、不同类型的内容，提升搜索体验。"
);
const front = ref(
  "1. 基于Vue 3 + Ant Design Vue 4.0.0 实现响应式页面开发，使用 Tab 组件 + Vue Router 动态路由实现统一页面布局，通过用户点击 Tab 时更改路由来切换各类数据（文章、图片、用户）的搜索结果，并选用不同的组件进行展示。\n" +
    "2. 为解决刷新页面后搜索结果丢失的问题，定义 searchParams 响应式变量来集中保存当前的搜索条件（比如关键词、搜索类别、分页），并通过 Vue Router 的 query 参数将搜索条件同步到 url 的 querystring 中。\n" +
    "3. 使用 masonry 插件，固定每张图片的宽度，高度自适应，实现图片的瀑布流布局。\n"
);
const backend = ref(
  "1. 基于 Spring Boot 框架 + MyBatis X 插件，快速生成基本数据源的增删改查（比如用户、文章）。\n" +
    "2. 数据源获取：\n" +
    "   - 使用 HttpClient 请求离线获取外部网站的文章，并使用 Hutool 的 JSONUtil 解析和预处理文章，最终入库。\n" +
    "   - 使用 jsoup 实时请求必应搜索接口获取图片，并使用 CSS Selector 语法解析和预处理图片信息，最终返回给前端。\n" +
    "3. 搜索词高亮：使用 Elasticsearch 的 highlight 语法实现搜索词高亮。\n" +
    "4. 数据同步：使用 Spring Scheduler 定时同步近5分钟内发生更新的 MySQL 的文章数据到 ES ，通过唯一 id 来保证每条数据的准确性。\n" +
    "5. 模糊查询：为解决文章搜不出的问题，自主搭建 Elasticsearch 来代替 MySQL 的模糊查询，并通过索引绑定ik分词器实现了更灵活的分词搜索。\n" +
    "6. 开发搜索功能时，使用 Kibana DevTools + DSL 调试 ES 的搜索效果，并使用 Spring Data Elasticsearch 的 QueryBuilder 组合查询条件，实现对 ES 内文章的灵活查询（比如同时查询标题和文章中带有指定关键字的内容）。"
);

// 监听，路由改变，页面随之改变
watchEffect(() => {
  searchParams.value = {
    ...initSearchParams,
    text: route.query.text,
    pageNum: route.query.pageNum,
  } as any;
});

// 在tab为post时，监听搜索内容，开启搜索建议
watch(
  () => searchParams.value.text,
  (newValue) => {
    if (activeKey.value === "post") {
      // 启用监听
      myAxios.get("/suggest/post?pre=" + searchParams.value.text)
        .then((res: any) => {
          options.value = [];
          if (res.length === 0) {
            options.value.push({ value: "找不到符合的文章！" });
          } else {
          res.forEach((item: string) => {
            options.value.push({value: item});
          });
        }
        });
    } else {
    }
  }
);

// 切换tab/点击搜索 => url更改，搜索数据
const urlChange = () => {
  options.value = [];
  router.push({
    path: `${activeKey.value}`,
    query: searchParams.value,
  });
  const query = {
    searchText: searchParams.value.text,
    type: activeKey.value,
    pageSize: 100,
    current: 1,
  };
  spinning.value = true;
  pictureList.value = null;
  myAxios.post("/search/all", query).then((res: any) => {
    if (query.type === "post") {
      postList.value = res.dataList;
      return;
    } else if (query.type === "picture") {
      const query = { ...route.query };
      query.pageNum = 1;
      router.replace({ query });
      pictureList.value = res.dataList;
      spinning.value = false;
      return;
    } else if (query.type === "user") {
      userList.value = res.dataList;
    }
  });
};

// 初始化，默认tab为post
onMounted(() => {
  const navigationType = window.performance.navigation.type;
  if (navigationType === 0) {
    console.log("页面初次加载");
    activeKey.value = "post";
  } else if (navigationType === 1) {
    console.log("页面刷新");
  }
  urlChange();
});

// 抽屉
const showDrawer = () => {
  open.value = true;
};
const onClose = () => {
  open.value = false;
};
</script>

<style>
:where(.css-ykw4qh).ant-float-btn
  .ant-float-btn-body
  .ant-float-btn-content
  .ant-float-btn-icon {
  text-align: center;
  margin: auto;
  width: 25px;
  font-size: 18px;
  line-height: 1;
}
:where(.css-ykw4qh).ant-float-btn-default
  .ant-float-btn-body
  .ant-float-btn-content
  .ant-float-btn-description {
  display: flex;
  align-items: center;
  line-height: 16px;
  color: #eea6b7;
  font-size: 12px;
}
#spin {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.container {
  text-align: justify;
}

/*pre {*/
/*  word-wrap: break-word;*/
/*  overflow-wrap: break-word;*/
/*}*/
.text {
  white-space: pre-wrap;
  word-break: break-word;
  line-height: 20px;
}

.display {
  width: 100%;
  height: auto;
}
</style>
