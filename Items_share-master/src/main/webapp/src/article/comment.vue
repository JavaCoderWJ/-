<template>
  <div class="comment-wrap">
    <h3 class="top">评论({{otherComments.length}})</h3>
    <div class="my-comment">
      <div class="portrait">
        <img :src="userData.avatar" height="50" width="50" alt="加载失败">
      </div>
      <el-form class="comment-area">
        <el-form-item>
          <el-input type="textarea" class="textarea" rows="5" v-model="myComment" resize="none" v-if="loginState"></el-input>
          <el-input type="textarea" class="textarea" rows="5" v-model="myComment" resize="none" v-else placeholder="您还未登录，请先登录" disabled></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="submit-comment" @click="submitCom" v-if="loginState">提交</el-button>
          <el-button type="primary" class="submit-comment" v-else disabled>提交</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="other-comment">
      <ul v-if="otherComments.length">
        <li v-for="item in otherComments" class="other-comment-wrap">
          <img :src="item.userPojo.user_head" class="avatar">
          <div class="comment-info">
            <span class="user-name">{{item.userPojo.user_name}}</span>
            <span class="publish-time">{{item.UserCommentPojo.comment_date}}</span>
            <p>{{item.UserCommentPojo.user_comment}}</p>
            <span class="prise" @click="commentPrise(item)">{{item.UserCommentPojo.won_praise_for}}</span>
          </div>
        </li>
      </ul>
      <div v-else class="none-comm">暂无评论</div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
import {cookie} from '../utils/CookieUtil.js'
import {createFormData} from '../utils/formData.js'
export default {
	created(){
		this.getComment()
	},
  data() {
    return {
      myComment: '',
      otherComments: [],
      userData: {
      	avatar: cookie.get('head'),
      	name: cookie.get('name')
      }
    }
  },
  computed: {
    loginState() {
      return this.$store.state.ifLogin
    }
  },
  methods: {
  	getComment(){
  		axios.get(`http://203.195.151.80:8080/Items_share/ItemsPage/returnUserComment?goods_id=${this.$route.params.id}`).then(res=>{
  			if(res.data.statue == 1){
  				this.otherComments = res.data.data
  			}else{
  				this.$message.error('获取评论失败')
  			}
  		})
  	},
  	submitCom(){
  		let data = {
  			goods_id: this.$route.params.id,
  			user_comment: this.myComment
  		}
  		data = createFormData(data)
  		axios.defaults.withCredentials = true
  		axios.post('http://203.195.151.80:8080/Items_share/ItemsPage/giveComment',data).then(res=>{
  			if(res.data.statue == 1){
  				this.$message({
  					type: 'success',
  					message: '评论成功'
  				})
  			}
  		})
  	},
  	commentPrise(item){
      axios.defaults.withCredentials = true
  		axios.get(`http://203.195.151.80:8080/Items_share/ItemsPage/giveCommentPraise?comment_id=${item.UserCommentPojo.comment_id}`).then(res=>{
        console.log(res)
  			if(res.data.statue == 1){
  				this.$message({
            type: 'success',
            message: '点赞成功'
          })
          item.item.UserCommentPojo.won_praise_for = res.data.data
  			}else{
  				this.$message.error(res.data.message)
  			}
  		})
  	}
  },
  watch:{
  	'$route'(to, from){
  		this.getComment()
  		this.myComment = ''
  	}
  }
}

</script>
<style scoped lang="scss">
.comment-wrap {
  margin-top: 50px;
}

.top {
  font-size: 16px;
  border-bottom: 1px solid #1D1B1B;
  padding-bottom: 10px;
}

.my-comment {
  margin-top: 25px;
  overflow: hidden;
}

.portrait {
  float: left;
}

.comment-area {
  float: left;
  margin-left: 20px;
}

.textarea {
  width: 420px;
  textarea {
    resize: none;
  }
}

.submit-comment {
  float: right;
}

.other-comment-wrap {
  overflow: hidden;
}

.avatar {
  width: 42px;
  height: 42px;
  border-radius: 50%;
  float: left;
}

.comment-info {
  float: left;
  margin-left: 25px;
  width: 425px;
  span {
    display: block;
  }
  p {
    color: #555;
    margin-bottom: 25px;
    word-wrap: break-word;
    line-height: 22.5px;
  }
}

.user-name {
  font-size: 16px;
  margin-bottom: 10px;
}

.publish-time {
  font-size: 12px;
  color: #999;
  margin-bottom: 20px;
}

.prise {
  float: right;
  cursor: pointer;
  &:before {
    content: "";
    display: inline-block;
    width: 23px;
    height: 23px;
    background: url(../assets/icon.png) no-repeat;
    background-size: 100% 100%;
    vertical-align: middle;
  }
}
.none-comm{
	height: 30px;
	border: 1px solid #eee;
	background-color: #CFCECE;
	text-align: center;
	line-height: 30px;
	font-size: 12px;
	color: #585555;
}
</style>
