<template>
  <div class="container">
    <div class="cont-left">
      <div class="card-info">
        <div class="avatar">
          <img :src="getUserInfo.user_head">
          <el-upload class="avatar-uploader" action="http://203.195.151.80:8080/Items_share/PersonalCenter/updatePersonalHead" :show-file-list="false" name="user_head" :with-credentials="true" :on-success="handleSuccess">
            <i class="el-icon-plus avatar-uploader-icon"></i>更改头像
          </el-upload>
        </div>
        <div class="detail-info">
          <div class="user-name">
            <span @click="settingShow = false">{{getUserInfo.user_name}}</span>
            <span @click="settingShow = true"><i class="el-icon-setting"></i>账户设置</span>
          </div>
          <div class="score">
            <ul>
              <li>
                <span>{{getUserInfo.user_integal}}</span>
                <br>
                <span>积分</span>
              </li>
              <li>
                <span>{{getUserInfo.user_expericence}}</span>
                <br>
                <span>经验</span>
              </li>
            </ul>
          </div>
          <p class="profile" v-if="getUserInfo.user_intro">{{getUserInfo.user_intro}}</p>
          <p class="profile" v-else>您还没有设置个人简介，去设置吧。</p>
        </div>
      </div>
      <el-tabs v-model="activeName" class="tabs" type="card" v-show="!settingShow">
        <el-tab-pane label="首页" name="first">
          <div class="cont-wrap">
            <div class="tab-panel">
              <h3>
                <span>原创</span>
              </h3>
              <div class="none" v-if="originData.length">您有{{originData.length}}篇原创文章</div>
              <div class="none" v-else>还没有任何原创</div>
            </div>
            <div class="tab-panel">
              <h3>
                <span>我的赞</span>
              </h3>
              <div class="none" v-if="priseArticle.length">您被赞了{{priseArticle.length}}篇原创文章</div>
              <div class="none" v-else>还没有被赞过什么文章</div>
            </div>
            <div class="tab-panel">
              <h3>
                <span>对我有用</span>
              </h3>
              <div class="none" v-if="usefulArticle.length">您收藏过{{usefulArticle.length}}篇文章</div>
              <div class="none" v-else>还没有收藏任何文章</div>
            </div>
            <div class="tab-panel">
              <h3>
                <span>评论</span>
              </h3>
              <div class="none" v-if="comments.length">您评论过{{usefulArticle.length}}篇文章</div>
              <div class="none" v-else>还没有任何评论</div>
            </div>
            <div class="tab-panel">
              <h3>
                <span>消息</span>
              </h3>
              <div class="none" v-if="systemMessage.length">您收到了{{systemMessage.length}}条消息</div>
              <div class="none" v-else>还没有任何消息</div>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane name="second" class="single-tab">
          <span slot="label">原创&nbsp;<span>{{originData.length}}</span></span>
          <div class="top-line">
            <el-button type="text" class="top-line-button">
              <router-link to="/write" tag="span">我要投稿</router-link>
            </el-button>
          </div>
          <div class="origin" v-if="originData.length">
            <div class="article" v-for="item in originData">
              <div class="article-img">
                <img :src="item.picture_url">
              </div>
              <div class="operation">
                <h6>{{item.artical_name}}</h6> 
              </div>
              <div class="state">
                <span>{{item.publis_date}}</span>
                <span>{{item.check_state == 1?'通过':'未通过'}}</span>
              </div>
            </div>
          </div>
          <div class="none" v-else>还没有任何原创</div>
        </el-tab-pane>
        <el-tab-pane name="third" class="single-tab">
          <span slot="label">我的赞&nbsp;<span>{{priseArticle.length}}</span></span>
          <ul v-if="priseArticle.length">
            <li v-for="item in priseArticle" class="useful">
              <a href="javascript:;">{{item.artical_name}}</a>
              <span>{{item.publis_date}}</span>
            </li>
          </ul>
          <div class="none" v-else>还未赞过什么文章</div>
        </el-tab-pane>
        <el-tab-pane name="fourth" class="single-tab">
          <span slot="label">对我有用&nbsp;<span>{{usefulArticle.length}}</span></span>
          <ul v-if="usefulArticle.length">
            <li v-for="item in usefulArticle" class="useful">
              <a href="javascript:;">{{item.itemsPojo.artical_name}}</a>
              <span>{{item.collect_time}}</span>
            </li>
          </ul>
          <div class="none" v-else>还没有收藏任何文章</div>
        </el-tab-pane>
        <el-tab-pane name="fifth" class="single-tab">
          <span slot="label">评论&nbsp;</span>
          <div class="top-line">
            <el-button type="text" class="top-line-button" :class="{'active': isActive}" @click="changeComment">我收到的评论 <span>({{recieveCom.length}})</span></el-button>
            <el-button type="text" class="top-line-button" :class="{'active': !isActive}" @click="changeComment">我发出的评论 <span>({{comments.length}})</span></el-button>
          </div>
          <div v-if="comments.length">
            <div class="comments" v-show="comments.length && isActive" v-for="(item,index) in comments" :key="index">
              <div class="comment-by">
                <img :src="item.user_head">
              </div>
              <div class="comment">
                <p>{{item.user_comment}}</p>
                <div class="comment-cont">
                  <img :src="item.picture_url">
                  <h3 class="obj-title">{{item.artical_name}}</h3>
                </div>
              </div>
              <div class="comment-time">{{item.comment_date}}</div>
            </div>
            <div v-show="recieveCom.length && !isActive" v-for="(item,index) in recieveCom" class="comments">
              <div class="comment-by">
                <img :src="item.user_head">
              </div>
              <div class="comment">
                <p>{{item.user_comment}}</p>
                <div class="comment-cont">
                  <img :src="item.picture_url">
                  <h3 class="obj-title">{{item.artical_name}}</h3>
                </div>
              </div>
              <div class="comment-time">{{item.comment_date}}</div>
            </div>
          </div>
          <div class="none" v-else>还没有任何评论</div>
        </el-tab-pane>
        <el-tab-pane name="sixth" class="single-tab">
          <span slot="label">消息&nbsp;</span>
          <div class="top-line">
            <el-button type="text" class="top-line-button">系统消息 <span>({{systemMessage.length}})</span></el-button>
          </div>
          <div class="msg-wrap" v-if="systemMessage.length">
            <div class="system-msg" v-for="(item,index) in systemMessage" :key="index">
              <p>{{item.message}}</p>
              <span>{{item.date}}</span>
            </div>
          </div>
          <div class="none" v-else>还没有任何消息</div>
        </el-tab-pane>
      </el-tabs>
      <setting v-show="settingShow"></setting>
    </div>
    <div class="user-info">
      <div class="logo">
        <img src="../assets/items_share.png">
      </div>
      <div>
        <div class="user-focus">
          <span>{{getUserInfo.focus_numbers}}</span>
          <span>关注</span>
        </div>
        <div class="user-fans">
          <span>{{getUserInfo.fans}}</span>
          <span>粉丝</span>
        </div>
        <div class="user-prise">
          <span>{{getUserInfo.won_praise_for}}</span>
          <span>获赞</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Setting from './setting.vue'
import axios from 'axios'
import { createFormData } from '../utils/formData.js'
import { cookie } from '../utils/CookieUtil.js'
export default {
  created() {
    axios.defaults.withCredentials = true
    if (cookie.get('loginState') == 'yes') {
      this.getUserData()
    } else {
      return
    }
    this.getData('http://203.195.151.80:8080/Items_share/PersonalCenter/getOriginal', 'originData')
    this.getData('http://203.195.151.80:8080/Items_share/PersonalCenter/getUserPraise', 'priseArticle')
    this.getData('http://203.195.151.80:8080/Items_share/PersonalCenter/getUserCollect', 'usefulArticle')
    this.getData('http://203.195.151.80:8080/Items_share/PersonalCenter/getUserComent', 'comments')
    this.getData('http://203.195.151.80:8080/Items_share/PersonalCenter/getOtherUserComment', 'recieveCom')
    this.getData('http://203.195.151.80:8080/Items_share/PersonalCenter/getSystemMessage', 'systemMessage')
  },
  data() {
    return {
      activeName: 'first',
      originData: [], //原创
      priseArticle: [], // 赞过的文章
      usefulArticle: [], // 有用的文章
      comments: [], // 发出的评论
      recieveCom: [], // 收到的评论
      systemMessage: [], // 系统消息
      myMessage: [], // 我的消息
      avatarUrl: '',
      settingShow: false,
      isActive: true
    }
  },
  components: {
    Setting
  },
  computed: {
    getUserInfo() {
      return this.$store.state.userData;
    }
  },
  methods: {
    getUserData() {
      axios.defaults.withCredentials = true
      axios.get('http://203.195.151.80:8080/Items_share/PersonalCenter/getPersonalDetails').then((response) => {
        if (response.data.statue == 1) {
          this.$store.commit('setUserData',response.data.data)
        }
      })
    },
    getData(url, variable) {
      axios.get(url).then(response => {
        if (response.data.statue == 1) {
          this[variable] = response.data.data
        } else {
          this.$message.error('请刷新试试')
        }
      })
    },
    changeComment() {
      if (!this.isActive) {
        this.isActive = true

      } else {
        this.isActive = false
      }
    },
    handleSuccess(response, file, fileList) {
      if(response.statue == 1){
        this.$store.commit('setUserData',response.data)
        this.$message({
          type: 'success',
          message: '头像上传成功'
        })
      }else{
        this.$message.error('头像上传失败：(')
      }
    }
  }
}

</script>
<style scoped lang="scss">
@import '../common/mixin.scss';
.active {
  font-weight: bold;
}

.container {
  width: 1050px;
  margin: 30px auto 0;
  @extend .clearfix;
}

.cont-left {
  width: 730px;
  float: left;
}

.card-info {
  width: 730px;
  height: 160px;
  border: 1px solid #eee;
}

.avatar {
  width: 140px;
  height: 160px;
  float: left;
  border-right: 1px solid #eee;
  img {
    border: 1px solid #eee;
    width: 100px;
    height: 100px;
    margin: 14px 20px 0;
    border-radius: 100%;
  }
}

.detail-info {
  float: left;
  padding: 20px;
}

.user-name {
  cursor: pointer;
  span:nth-child(1) {
    color: #555;
    font-size: 18px;
    font-weight: 600;
  }
  span:nth-child(2) {
    margin-left: 30px;
  }
}

.score {
  margin: 20px 0;
  ul {
    overflow: hidden;
    li {
      float: left;
      margin-right: 26px;
      span {
        &:nth-child(1) {
          color: #d62222;
          font-size: 20px;
          font-weight: 600;
        }
        &:nth-child(2) {
          font-size: 14px;
          color: #666;
        }
      }
    }
  }
}

.profile {
  font-size: 12px;
  color: #333;
}

.tabs {
  margin-top: 50px;
}

.cont-wrap {
  margin-top: 30px;
}

.tab-panel {
  margin-top: 30px;
  border-bottom: 1px solid #e6e6e6;
  padding-bottom: 30px;
  h3 {
    @include titleNormal('h3');
    border-bottom: 1px solid #eee;
    padding-bottom: 5px;
    span {
      border-bottom: 2px solid #d74140;
      padding-bottom: 5px;
    }
  }
}

.single-tab {
  min-height: 400px;
}

.none {
  height: 36px;
  line-height: 36px;
  background-color: #fafafa;
  border: 1px solid #e6e6e6;
  text-align: center;
  font-size: 12px;
  margin-top: 25px;
}

.top-line {
  border-bottom: 1px solid #eee;
  @extend .clearfix;
}

.top-line-button {
  font-size: 14px;
  color: #666;
}

.contribute {
  float: right;
}

.useful {
  border-bottom: 1px dashed #eee;
  padding-bottom: 5px;
  font-size: 14px;
  margin-top: 30px;
  a {
    color: #7B8DB8;
    text-decoration: none;
  }
  span {
    float: right;
    color: #817D7D;
  }
}

.user-info {
  width: 300px;
  float: left;
  margin-left: 18px;
  border: 1px solid #eee;
}

.logo {
  border-bottom: 1px solid #eee;
  img {
    width: 300px;
    height: 156px;
  }
}

.user-focus,
.user-fans,
.user-prise {
  float: left;
  width: 99px;
  height: 70px;
  border-right: 1px solid #eee;
  box-sizing: border-box;
  padding: 10px;
  span {
    display: block;
    text-align: center;
    &:nth-child(1) {
      color: #f04848;
      font-size: 23px;
    }
    &:nth-child(2) {
      color: #666;
      font-size: 14px;
      line-height: 14px;
      margin-top: 14px;
    }
  }
}

.user-prise {
  border: none;
}

.article {
  box-sizing: border-box;
  padding: 25px 0 20px;
  border-top: 1px solid #eee;
  overflow: hidden;
}

.article-img {
  float: left;
  img {
    width: 60px;
    height: 60px;
  }
}

.operation {
  float: left;
  margin-left: 10px;
  h6 {
    @include titleNormal('h6');
    font-size: 16px;
    margin-bottom: 15px;
  }
  div {
    color: #888;
    font-size: 12px;
    line-height: 1.6em;
  }
}

.state {
  float: right;
  font-size: 12px;
  span {
    display: block;
    &:first-child {
      color: #999;
      height: 26px;
      line-height: 26px;
    }
    &:last-child {
      color: #f04848;
    }
  }
}

.comments {
  margin-top: 10px;
  padding-top: 20px;
  border-top: 1px solid #eee;
  @extend .clearfix;
}

.comment-by {
  float: left;
  img {
    width: 36px;
    height: 36px;
    border-radius: 50%;
    border: 1px solid #eee;
  }
}

.comment {
  float: left;
  margin-left: 10px;
  p {
    word-wrap: break-word;
    font-size: 14px;
    color: #555;
  }
}

.comment-cont {
  margin-top: 10px;
  padding: 10px;
  border: 1px solid #e6e6e6;
  width: 530px;
  height: 56px;
  img {
    width: 50px;
    height: 50px;
    border: 1px solid #eee;
    vertical-align: top;
  }
  h3 {
    @include titleNormal('h3');
    font-size: 14px;
    color: #333;
    display: inline-block;
    margin-left: 10px;
  }
}

.comment-time {
  float: right;
  margin-bottom: 5px;
  color: #999;
  font-size: 12px;
  height: 26px;
  line-height: 26px;
}

.msg-wrap {
  margin-top: 10px;
}

.system-msg {
  @extend .clearfix;
  border-bottom: 1px dashed #eee;
  padding: 10px 0 20px;
  p {
    width: 600px;
    font-size: 12px;
    color: #888;
    line-height: 1.6em;
    word-wrap: break-word;
    word-break: break-all;
    float: left;
  }
  span {
    float: right;
    color: #999;
    font-size: 12px;
    height: 26px;
    line-height: 26px;
  }
}

.avatar-uploader {
  text-align: center;
  margin-top: 5px;
}

</style>
