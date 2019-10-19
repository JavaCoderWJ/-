<template>
  <div>
    <div class="main-wrap">
      <div class="main-left">
        <el-carousel trigger="click" height="600px" width="500px" indicator-position="outside">
          <el-carousel-item v-for="(item,index) in carouselData" :key="index">
            <router-link :to="`/article/${item.goods_id}`"><img :src="item.picture_url" width="100%" height="80%"></router-link>
            <div class="info-wrap">
              <h3 class="carousel-title" :title="item.artical_name">{{ item.artical_name }}</h3>
              <p class="carousel-intro">{{item.article}}</p>
            </div>
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="main-right">
        <el-form :inline="true">
          <el-form-item>
            <el-autocomplete
              v-model="searchStr"
              :fetch-suggestions="querySearch"
              placeholder="搜索想要的物品类别"
              @select="handleSelect"
              prefix-icon="el-icon-search"
            ></el-autocomplete>
          </el-form-item>
        </el-form>
        <div class="more">
          <h2>更多精彩</h2>
          <ul>
            <li class="more-item" v-for="(item,index) in modulesData" :key="index">
              <h3>#{{item.goods_type}}#</h3>
              <img :src="item.goods_type_picture">
            </li>
          </ul>
        </div>
      </div>
    </div>
    <More></More>
  </div>
</template>
<script>
import More from './more.vue'
import axios from 'axios'
export default {
  data() {
    return {
      seeMoreBtnShow: true,
      carouselData: [], // 轮播数据
      modulesData: [], // 更多精彩
      searchStr: ''
    }
  },
  created() {
    this.getCarousel()
    this.getMore()
  },
  components: {
    More
  },
  methods: {
    seeMore() {
      this.ifShow = true;
      this.seeMoreBtnShow = false;
    },
    // 获取轮播图
    getCarousel() {
      axios.get('http://203.195.151.80:8080/Items_share/HomePage/getShufflingItems').then((response) => {
        if (response.data.statue == 1) {
          this.carouselData = response.data.data
        } else {
          this.$message('获取数据失败，请刷新试试')
        }
      }).catch((err) => {
        console.log(err)
      })
    },
    // 搜索
    querySearch(str,callback){
      axios.get(`http://203.195.151.80:8080/Items_share/HomePage/getItemsByUser?goods_type=${this.searchStr}`).then(res=>{
        if(res.data.statue == 1){
          let data = res.data.data
          for(let i of data){
            i.value = i.artical_name
          }
          callback(data)
        }
      })
    },
    handleSelect(item){
      this.$router.push(`/article/${item.goods_id}`)
    },
    // 更多精彩
    getMore() {
      axios.get('http://203.195.151.80:8080/Items_share/HomePage/getGoods_type_picture').then((response) => {
        if (response.data.statue == 1) {
          this.modulesData = response.data.data
        } else {
          this.$message('获取数据失败，请刷新试试')
        }
      }).catch((err) => {
        console.log(err)
      })
    }
  }
}

</script>
<style scoped lang="scss">
@import '../common/mixin.scss';
.main-wrap {
  width: 1000px;
  overflow: hidden;
  margin: 60px auto 0;
}

.main-left {
  float: left;
  width: 600px;
}

.main-right {
  float: left;
  width: 350px;
  margin-left: 50px;
}

.info-wrap {
  padding: 15px;
  box-sizing: border-box;
}

.carousel-title {
  font-size: 24px;
  color: #403D3D;
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}

.carousel-intro {
  font-size: 16px;
  width: 100%;
  height: 60px;
  color: #6D6D6D;
  @include noWrap(3);
  margin-top: 10px;
  line-height: 20px;
}

.more {
  margin-top: 30px;
  ul {
    overflow: hidden;
  }
  h2 {
    @include titleNormal('h2');
    color: #EF6FF6;
    margin-bottom: 20px;
  }
}

.more-item {
  float: left;
  width: 150px;
  height: 180px;
  position: relative;
  &:nth-child(2n) {
    margin: {
      left: 10px
    }
  }
  &:nth-child(1) {
    margin-bottom: 10px;
  }
  &:nth-child(2) {
    margin-bottom: 10px;
  }
  h3 {
    @include titleNormal('h3');
    text-align: center;
    position: absolute;
    z-index: 2;
  }
  img {
    width: 100%;
    height: 100%;
    position: absolute;
    left: 0;
    top: 0;
  }
}

.see-more {
  margin: {
    top: 50px;
    left: 500px - 86;
  }
}

</style>
