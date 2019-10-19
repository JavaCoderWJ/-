<template>
  <div>
    <v-header></v-header>
    <ul class="item-wrap">
      <li v-for="(item,index) in newest" :key="index" class="item">
        <router-link :to="`/article/${item.goods_id}`">
          <img :src="item.picture_url" height="200" width="300">
        </router-link>
        <h3 :title="item.artical_name" class="item-title">{{item.artical_name}}</h3>
        <div>
          <div class="publisher-info">
            <img :src="item.userPojo.user_head">
            <span>{{item.userPojo.user_name}}</span>
          </div>
          <div class="praise">
            <span @click="like(item.goods_id,item)"><i></i>{{item.won_praise_for}}</span>
          </div>
        </div>
      </li>
    </ul>
    <v-footer></v-footer>
    <v-aside></v-aside>
  </div>
</template>
<script>
import axios from 'axios'
import VHeader from './../components/header'
import VFooter from './../components/footer'
import VAside from './../components/aside'
export default {
  created() {
  	this.request()
  },
  data() {
    return {
      newest: []
    }
  },
  components: {
    VHeader,
    VFooter,
    VAside
  },
  methods: {
  	request(){
  		let href = window.location.href
	  	let paramIndex = href.lastIndexOf('/')
	  	let param = href.slice(paramIndex+1)
	  	let url = param == 'newest'? 'http://203.195.151.80:8080/Items_share/HomePage/getNewItems':'http://203.195.151.80:8080/Items_share/HomePage/getRecommend'
			axios.get(url).then((response) => {
	      if (response.data.statue == 1) {
	        this.newest = response.data.data
	      } else {
	        this.$message('请刷新试试')
	      }
	    })
  	},
    like(goods_id,item){
      axios.get(`http://203.195.151.80:8080/Items_share/ItemsPage/addWonPraise?goods_id=${goods_id}`).then((response) => {
        if(response.data.statue == 1){
          item.won_praise_for ++;
        }else{
          this.$message(response.data.message)
        }
      })
    }
  },
  watch: {
    '$route' (to, from) {
      this.request()
    }
  }
}

</script>
<style scoped lang="scss">
@import '../common/mixin.scss';
.item-wrap {
  width: 1080px;
  overflow: hidden;
  margin: 30px auto;
}

.item {
  float: left;
  width: 300px;
  margin: 0 30px 40px;
}

.publisher-info {
  margin-top: 10px;
  float: left;
  img {
    width: 28px;
    height: 28px;
    border-radius: 50%;
    vertical-align: middle;
  }
  span {
    margin-left: 10px;
    font-size: 14px;
    color: #555;
  }
}

.praise {
  float: right;
  margin-top: 10px;
  cursor: pointer;
  i {
    width: 23px;
    height: 23px;
    display: inline-block;
    background: url(../assets/icon.png) no-repeat;
    background-size: 100% 100%;
    vertical-align: middle;
  }
  span {
    font-size: 16px;
    color: #555;
    line-height: 25px;
    margin-left: 5px;
  }
}

.item-title{
  @include lineNoWrap();
  font-size: 16px;
  font-weight: 500;
  margin-top: 10px;
}
</style>
