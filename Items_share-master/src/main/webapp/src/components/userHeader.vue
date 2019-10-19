<template>
  <div class="deputy-header">
    <div class="header-wrap">
      <h1>全麦</h1>
      <div>个人中心</div>
      <el-autocomplete
        v-model="searchStr"
        :fetch-suggestions="querySearch"
        placeholder="搜索想要的物品类别"
        @select="handleSelect"
        prefix-icon="el-icon-search"
        class="search-box"
      ></el-autocomplete>
    </div>
  </div>
</template>
<script>
  import axios from 'axios'
  export default{
    data(){
      return {
        searchStr: '',

      }
    },
    methods: {
      querySearch(str,cb){
        axios.get(`http://203.195.151.80:8080/Items_share/HomePage/getItemsByUser?goods_type=${this.searchStr}`).then(res=>{
          if(res.data.statue == 1){
            let data = res.data.data
            for(let i of data){
              i.value = i.artical_name
            }
            cb(data)
          }
        })
      },
      handleSelect(item){
        this.$router.push(`/article/${item.goods_id}`)
      }
    }
  }

</script>
<style scoped lang="scss">
@import '../common/mixin.scss';
.deputy-header {
  width: 100%;
  min-width: 1080px;
  height: 80px;
  background-color: #f0f0f0;
  border-bottom: 1px solid #dcdcdc;
}

.header-wrap {
  width: 1080px;
  height: 80px;
  margin: 0 auto;
  h1 {
    float: left;
    @include titleNormal('h1');
    margin-top: (80px - 28) / 2;
    color: #F36363;
    border-right: 1px solid #dcdcdc;
    padding-right: 15px;
  }
  div {
    color: #333;
    line-height: 40px;
    font-size: 20px;
    font-weight: normal;
    width: 148px;
    float: left;
    margin: {
      top: 20px;
      left: 20px;
    }
  }
  .search-box {
    float: right;
    width: 260px;
    height: 34px;
    font-size: 14px;
  }
}

</style>
