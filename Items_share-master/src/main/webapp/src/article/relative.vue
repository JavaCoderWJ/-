<template>
  <div class="wrap" ref="wrap" :class="{'fixed':ifFixed}">
    <h3>相关笔记</h3>
    <router-link :to="`/article/${item.goods_id}`" tag="div" class="link-article" v-for="(item,index) in relArt" :key="index">
      <div class="article-img">
        <img :src="item.picture_url">
      </div>
      <div class="right-part">
        <div class="title">
          <p>{{item.artical_name}}</p>
        </div>
        <div class="useful">
          <i></i>
          <span>{{item.userful}}</span>
        </div>
      </div>
    </router-link>
  </div>
</template>
<script>
import {throttle} from '../utils/throttle.js'
import axios from 'axios'
export default {
  props: ['writerId'],
  data() {
    return {
      ifFixed: false,
      relArt: [],
      userId: this.writerId
    }
  },
  created() {
    const vm = this
    window.addEventListener('scroll', function() {
      throttle(vm.toFixed)
    }, false)
    this.getRelArt()
  },
  methods: {
    toFixed() {
      let scrollTop = document.documentElement.scrollTop || document.body.scrollTop
      if (scrollTop >= 200) {
        this.ifFixed = true
      } else {
        this.ifFixed = false
      }
    },
    getRelArt(){
      axios.get(`http://203.195.151.80:8080/Items_share/ItemsPage/getItemsByAuthor?user_id=${this.userId}`).then(res=>{
        if(res.data.statue == 1){
          this.relArt = res.data.data
        }else{
          this.$message.error(res.data.message)
        }
      })
    }
  },
  watch: {
    '$route'(to ,from){
      this.getRelArt()
    }
  }
}

</script>
<style scoped lang="scss">
@import '../common/mixin.scss';
.wrap {
  width: 318px;
  margin-top: 25px;
  border: 1px solid #eee;
  border-radius: 5px;
  background-color: #fff;
  h3 {
    padding: 10px 20px;
    @include titleNormal('h3');
    border-bottom: 1px solid #eee;
  }
}

.link-article {
  display: block;
  width: 280px;
  height: 80px;
  margin: 10px 20px;
  cursor: pointer;
}

.article-img {
  width: 80px;
  height: 80px;
  border-radius: 5px;
  float: left;
  margin-right: 10px;
  img {
    width: 100%;
    height: 100%;
  }
}

.right-part {
  float: left;
}

.title {
  p {
    color: #555;
    width: 190px;
    font-size: 14px;
    @include noWrap(2);
    line-height: 20px;
  }
}

.useful {
  margin-top: 10px;
  i {
    display: inline-block;
    width: 25px;
    height: 25px;
    background: url(../assets/icon.png) no-repeat 100% 100%;
    vertical-align: middle;
  }
  span {
    color: #555;
    display: inline-block;
    height: 25px;
    line-height: 25px;
  }
}

.fixed {
  position: fixed;
  top: 80px;
}

</style>
