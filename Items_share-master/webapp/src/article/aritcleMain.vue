<template>
  <div class="content-wrap" v-if="this.ifRender">
    <div class="content">
      <img :src="articleCont.itemsPojo.picture_url" height="500" width="500">
      <p class="publish">
        发布于{{articleCont.itemsPojo.publis_date}}<span>类别：{{articleCont.itemsPojo.goods_type}}</span>
      </p>
      <h1 class="title">{{articleCont.itemsPojo.artical_name}}</h1>
      <div id="markdownArea">
        <vue-markdown class="markdown" id="markdown" :rendered="render">{{this.source}}</vue-markdown>
      </div>
      <div class="tags">
        <span @click="like">
          <i class="el-icon-star-on"></i>
          <span>{{articleCont.itemsPojo.won_praise_for}}</span>
        </span>
        <span class="tags-item" @click="thinkUseful">有用<span>{{articleCont.itemsPojo.userful}}</span></span>
        <span class="tags-item" @click="thinkUseless">无用<span>{{articleCont.itemsPojo.unuserful}}</span></span>
        <span class="tags-item" @click="share">分享</span>
      </div>
      <comment></comment>
    </div>
    <div class="right-part">
      <card :writer-info="articleCont"></card>
      <relative :writer-id="articleCont.itemsPojo.userPojo.user_id"></relative>
    </div>
  </div>
</template>
<script>
import Comment from './comment.vue'
import Card from './personalCard.vue'
import Relative from '../article/relative.vue'
// Vue.use(VueSimpleMarkdown)
import axios from 'axios'
import VueMarkdown from 'vue-markdown'
import { createFormData } from '../utils/formData.js'
// import '../../node_modules/prismjs/themes/prism.css'
export default {
  created() {
    let articleId = this.$route.params.id
    this.getArticle(articleId)
    axios.defaults.withCredentials = true
  },
  mounted(){
    window.onload=function(){
      let allImgs = document.getElementById('markdownArea').getElementsByTagName('img')
      for(let i = 0;i<allImgs.length;i++){
        if(allImgs[i].offsetWidth>500){
          allImgs[i].style.maxWidth = 500 + 'px';
        }
      }
    }
  },
  data() {
    return {
      source: ``,
      articleCont: {}, // 文章内容
      ifRender: false
    }
  },
  components: {
    Comment,
    Card,
    Relative,
    VueMarkdown
  },
  methods: {
    getArticle(goods_id) {
      let data = {
        goods_id: goods_id
      }
      data = createFormData(data)
      axios.post('http://203.195.151.80:8080/Items_share/HomePage/getItemsById', data).then(response => {
        if (response.data.statue == 1) {
          this.articleCont = response.data.data
          this.source = response.data.data.itemsPojo.article
          this.ifRender = true
        } else {
          this.$message.error('请刷新试试')
        }
      })
    },
    share() {
      axios.get(`http://203.195.151.80:8080/Items_share/ItemsPage/shareItems?goods_id=${this.$route.params.id}`).then(res => {
        if (res.data.statue == 1) {
          this.$message({
            type: 'success',
            message: '分享成功'
          })
        } else {
          this.$message.error('分享失败')
        }
      })
    },
    thinkUseful(){
      axios.get(`http://203.195.151.80:8080/Items_share/ItemsPage/addUserful?goods_id=${this.$route.params.id}`).then(res=>{
        if (res.data.statue == 1) {
          this.$message({
            type: 'success',
            message: '收藏成功'
          })
          this.articleCont.itemsPojo.userful++;
        } else {
          this.$message.error('收藏失败')
        }
      })
    },
    thinkUseless(){
      axios.get(`http://203.195.151.80:8080/Items_share/ItemsPage/addUnUserful?goods_id=${this.$route.params.id}`).then(res=>{
        if (res.data.statue == 1) {
          this.$message({
            type: 'success',
            message: '点击成功'
          })
          this.articleCont.itemsPojo.unuserful++;
        } else {
          this.$message.error('点击失败')
        }
      })
    },
    like(){
      axios.get(`http://203.195.151.80:8080/Items_share/ItemsPage/addWonPraise?goods_id=${this.$route.params.id}`).then(res=>{
        if (res.data.statue == 1) {
          this.$message({
            type: 'success',
            message: '点赞成功'
          })
        } else {
          this.$message.error('点赞失败')
        }
      })
    },
    render(){
      console.log(1)
    }
  },
  watch: {
    '$route' (to, from) {
      this.getArticle(this.$route.params.id)
    }
  }
}

</script>
<style lang="scss" scoped>
@import '../common/mixin.scss';
// @import '../../node_modules/prismjs/themes/prism.css';
.content-wrap {
  width: 900px;
  margin: 30px auto 0;
  overflow: hidden;
}

.content {
  width: 500px;
  float: left;
}

.publish {
  margin-top: 20px;
  span {
    float: right;
  }
}

.title {
  margin: 19px 0 17px;
  font-size: 24px;
  font-weight: 500;
}

.item {
  width: 500px;
  height: 100px;
  border: 1px solid #999090;
  border-radius: 5px;
  margin-top: 30px;
  box-sizing: border-box;
  padding: 10px;
  a {
    text-decoration: none;
    color: #666464;
  }
  img {
    float: left;
    vertical-align: top;
  }
  .intro {
    margin-left: 95px;
    font-size: 15px;
    word-wrap: break-word;
    text-decoration: none;
    width: 380px;
    @include noWrap(2);
    line-height: 16px;
  }
  p {
    margin-top: 25px;
    text-indent: 15px;
    span {
      float: right;
      margin-right: 10px;
    }
  }
}

.tags {
  margin-top: 20px;
  span {
    cursor: pointer;
  }
}

.tags-item {
  margin-left: 10px;
}

.right-part {
  float: left;
  margin-left: 50px;
}
.markdown{
  width: 500px;
  img{
    max-width: 500px;
  }
}
</style>
