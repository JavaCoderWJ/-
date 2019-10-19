<template>
  <div class="navbar">
    <el-menu mode="horizontal" :default-active="activeIndex"  @select="clickItem">
      <el-menu-item v-for="(item,index) in category" :key="index" :index="(index+1).toString()" class="menu-item">{{item.goods_type}}</el-menu-item>
    </el-menu>
    <keep-alive>
      <ul class="content">
        <li v-for="(item,index) in categoryData" class="content-item">
          <router-link :to="`/article/${item.goods_id}`">
            <img :src="item.picture_url" height="200" width="200">
          </router-link>
          <div class="outer-wrap">
            <h3 :title="item.artical_name">{{item.artical_name}}</h3>
            <div class="writer">
              <img :src="item.userPojo.user_head" height="20" width="20">
              <span>{{item.userPojo.user_name}}</span>
            </div>
            <span class="praise" @click="like(item.goods_id,item)"><i></i>{{item.won_praise_for}}</span>
          </div>
        </li>
      </ul>
    </keep-alive>
  </div>
</template>
<script>
import axios from 'axios'
import {formData} from '../utils/formData.js'
export default {
  created(){
    this.getCategory().then(()=>{
      this.getCategoryDetail(this.category[0].goods_type)
    });
  },
  data() {
    return {
      activeIndex: "1",
      category: [], // 分类栏
      categoryData: [] // 分类数据
    }
  },
  methods: {
    // 分类栏
    async getCategory() {
      await axios.get('http://203.195.151.80:8080/Items_share/HomePage/getDifferentGoods_type').then((response) => {
        if (response.data.statue == 1) {
          this.category = response.data.data
        } else {
          this.$message('获取数据失败，请刷新试试')
        }
      }).catch((err) => {
        console.log(err)
      })
    },
    // 分类数据
    getCategoryDetail(goods_type) {
      // let data = createFormData(goods_type
      axios.get(`http://203.195.151.80:8080/Items_share/HomePage/getItemsByGoods_type?goods_type=${goods_type}`).then((response) => {
        if (response.data.statue == 1) {
          this.categoryData = response.data.data
        } else {
          this.$message.error('获取数据失败，请刷新试试')
        }
      }).catch((err) => {
        console.log(err)
      })
    },
    clickItem(index){
      this.getCategoryDetail(this.category[index-1].goods_type)
    },
    like(goods_id,item){
      axios.defaults.withCredentials = true
      axios.get(`http://203.195.151.80:8080/Items_share/ItemsPage/addWonPraise?goods_id=${goods_id}`).then((response) => {
        if(response.data.statue == 1){
          item.won_praise_for ++;
        }else{
          this.$message(response.data.message)
        }
      })
    }
  }
}

</script>
<style scoped lang="scss">
@import '../common/mixin.scss';
.navbar {
  width: 1000px;
  margin: 50px auto 0;
}

.menu-item {
  margin-left: 35px;
}

.content {
  margin-top: 50px;
  overflow: hidden;
}

.content-item {
  float: left;
  margin-left: (960px / 4) - 200;
  width: 200px;
  height: 270px;
  .outer-wrap {
    box-sizing: border-box;
    overflow: hidden;
    h3 {
      font-size: 16px;
      font-weight: 500;
      margin-bottom: 15px;
      @include lineNoWrap();
    }
    img {
      float: left;
      border-radius: 50%;
      border: 1px solid #B7B4B4;
      vertical-align: middle;
    }
  }
}
.praise{
  float: right;
  line-height: 23px;
  cursor: pointer;
  i{
    display: inline-block;
    width: 23px;
    height: 23px;
    background: url(../assets/icon.png) no-repeat;
    vertical-align: middle;
    margin-right: 2px;
  }
}
.writer{
  float: left;
  span{
    margin-left: 10px;
  }
}

.pagination {
  text-align: center;
  margin-top: 30px;
}

</style>
