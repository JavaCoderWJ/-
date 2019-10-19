<!-- 共用头部 -->
<template>
  <keep-alive>
    <el-container class="header-wrap">
      <el-menu :default-active="activeIndex" mode="horizontal" class="header" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" @select="selectItem">
        <el-menu-item index="1">
          <router-link to="/" tag="li">首页</router-link>
        </el-menu-item>
        <el-menu-item index="2">
          <router-link to="/newest" tag="li">最新</router-link>
        </el-menu-item>
        <el-menu-item index="3">
          <router-link to="/recommend" tag="li">精选推送</router-link>
        </el-menu-item>
        <el-menu-item index="4">
          <router-link to="/user" tag="li">个人中心</router-link>
        </el-menu-item>
        <div v-if="!loginState">
          <el-button @click="openDialog" class="log-reg">登陆/注册</el-button>
        </div>
        <div v-else class="user-info">
          <el-dropdown @command="quitLogin">
            <span class="el-dropdown-link">
              <img :src="userInfo.headPortrait" class="user-portrait">
              <i class="el-icon-arrow-down el-icon--right" style="color:#fff"></i>
            </span>
            <el-dropdown-menu slot="dropdown" show-timeout="0">
              <el-dropdown-item>
                <router-link to="/user" tag="li">个人中心</router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <router-link to="/user" tag="li">我的消息</router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                <router-link to="/user" tag="li">设置</router-link>
              </el-dropdown-item>
              <el-dropdown-item command="quit">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span class="user-name">{{userData.user_name}}</span>
        </div>
      </el-menu>
      <el-dialog title="" :visible.sync="loginVisible" width="30%" center :open="openDialog">
        <div class="login-header">
          <el-tabs v-model="activeTab">
            <el-tab-pane label="登陆" name="login">
              <el-form :rules="loginRules" ref="loginForm" :model="loginInfo">
                <el-form-item prop="user_name">
                  <el-input placeholder="用户名" v-model="loginInfo.user_name"></el-input>
                </el-form-item>
                <el-form-item prop="user_passward">
                  <el-input placeholder="密码" v-model="loginInfo.user_password" type="password"></el-input>
                </el-form-item>
                <el-form-item label="记住我">
                  <el-switch v-model="rememberMe"></el-switch> |
                  <el-button type="text" class="forget-psd" @click="activeTab = 'forget'">忘记密码</el-button>
                </el-form-item>
                <el-form-item>
                  <el-button plain class="login-btn" type="primary" @click="login('loginForm')" :loading="loginLoading">登陆</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
            <el-tab-pane label="注册" name="register">
              <el-form :rules="registerRules" ref="registerForm" :model="registerInfo">
                <el-form-item prop="user_name">
                  <el-input placeholder="用户名" v-model="registerInfo.user_name"></el-input>
                </el-form-item>
                <el-form-item prop="user_passward">
                  <el-input placeholder="密码" v-model="registerInfo.user_password" type="password"></el-input>
                </el-form-item>
                <el-form-item label="密保1" prop="mother_name">
                  <p>你母亲的姓名</p>
                  <el-input v-model="registerInfo.father_name"></el-input>
                </el-form-item>
                <el-form-item label="密保2" prop="father_name">
                  <p>你父亲的姓名</p>
                  <el-input v-model="registerInfo.mother_name"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button plain class="login-btn" type="primary" @click="register('registerForm')">注册</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
            <el-tab-pane label="忘记密码" name="forget">
              <el-form :model="findPassword">
                <el-form-item label="要找回密码的用户名">
                  <el-input v-model="findPassword.user_name"></el-input>
                </el-form-item>
                <el-form-item label="您父亲的名字">
                  <el-input v-model="findPassword.father_name"></el-input>
                </el-form-item>
                <el-form-item label="您母亲的名字">
                  <el-input v-model="findPassword.mother_name"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button @click="submitRue" plain class="login-btn" type="primary">提交</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-dialog>
    </el-container>
  </keep-alive>
</template>
<script>
import axios from 'axios'
import {createFormData} from '../utils/formData.js'
import {cookie} from '../utils/CookieUtil.js'
export default {
  created(){
    this.getCurrentURL()
    let href = window.location.href
    let paramIndex = href.lastIndexOf('/')
    let route = href.slice(paramIndex+1)
    if(cookie.get('loginState') == 'yes'){
      this.$store.commit('loginStateChange',true)
      this.userInfo.headPortrait = cookie.get('head')
      this.userInfo.userName = cookie.get('name')
    }else if(route == 'user'){
      this.$alert('您还未登录，请先登录', {
        confirmButtonText: '确定'
      });
      this.$router.push('/')
    }
  },
  data() {
    return {
      activeIndex: '1',
      loginVisible: false,
      innerVisible: false,
      activeTab: 'login',
      rememberMe: true, // 记住我
      loginLoading: false,
      // 用户信息
      userInfo: {
        headPortrait: '',
        userName: ''
      },
      // 注册信息
      registerInfo: {
        user_name: '',
        user_password: '',
        father_name: '',
        mother_name: ''
      },
      // 登陆信息
      loginInfo: {
        user_name: '',
        user_password: ''
      },
      findPassword: {
        user_name: '',
        father_name: '',
        mother_name: ''
      },
      // 验证规则
      loginRules: {
        // user_name: [{ pattern: /\w/g, required: true, message: '请输入用户名,只能包含字母数字下划线', trigger: 'blur' },
        //   { min: 6, max: 15, message: '长度在 6到 15 个字符', trigger: 'blur' }
        // ],
        // user_passward: [{ pattern: /\w/g, required: true, message: '请输入密码,只能包含字母数字下划线', trigger: 'blur' },
        //   { min: 6, max: 15, message: '长度在 6到 15 个字符', trigger: 'blur' }
        // ]
      },
      registerRules: {
        // user_name: [{ pattern: /\w/g, required: true, message: '请输入用户名,只能包含字母数字下划线', trigger: 'blur' },
        //   { min: 6, max: 15, message: '长度在 6到 15 个字符', trigger: 'blur' }
        // ],
        // user_passward: [{ pattern: /\w/g, required: true, message: '请输入密码,只能包含字母数字下划线', trigger: 'blur' },
        //   { min: 6, max: 15, message: '长度在 6到 15 个字符', trigger: 'blur' }
        // ],
        // father_name: [{ required: true, message: '请输入答案', trigger: 'blur' }],
        // mother_name: [{ required: true, message: '请输入答案', trigger: 'blur' }]
      }
    }
  },
  methods: {
    // 登陆接口
    login(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(this.rememberMe){
            cookie.set('userName',this.loginInfo.user_name)
            cookie.set('userPassword',this.loginInfo.user_password)
          }else{
            cookie.unset('userName')
            cookie.unset('userPassword')
          }
          this.loginLoading = true
          let data = createFormData(this.loginInfo)
          axios.defaults.withCredentials = true
          axios.post(`http://203.195.151.80:8080/Items_share/HomePage/getUserPojo`, data).then((response) => {
            if (response.data.statue == 1) {
              this.loginLoading = false
              this.$store.commit('loginStateChange', true);
              this.userInfo.headPortrait = response.data.data.user_head
              this.userInfo.userName = response.data.data.user_name
              this.$store.commit('setUserData', response.data.data)
              this.loginVisible = false;
              cookie.set('loginState','yes')
              cookie.set('head',this.userInfo.headPortrait)
              cookie.set('name',this.userInfo.userName)
              cookie.set('loginState','yes')
              this.$message({
                message: '登陆成功',
                type: 'success'
              })
            }else if(response.data.statue == 2){
              this.$router.push('/manage')
            }else {
              this.$message.error(response.data.message)
              this.loginLoading = false
            }
          }).catch((err) => {
            console.log(err)
          })
        }
      })
    },
    // 注册接口
    register(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // const vm = this
          let data = createFormData(this.registerInfo)
          axios.post('http://203.195.151.80:8080/Items_share/HomePage/userRegist', data).then((response) => {
            if (response.data.statue == 1) {
              this.$message('注册成功')
            } else {
              this.$message(response.message)
            }
          }).catch((err) => {
            console.log(err)
          })
        }
      })

    },
    // 找回密码
    submitRue(){
      let data = createFormData(this.findPassword)
      axios.post('http://203.195.151.80:8080/Items_share/HomePage/findPassword',data).then((response)=>{
        if(response.data.statue == 1){
          this.$alert(`你的密码为${response.data.data.user_password}`)
        }else{
          this.$alert(response.data.message)
        }
      })
    },
    // 退出登录
    quitLogin(command){
      if(command == 'quit'){
        this.$store.commit('loginStateChange',false)
        cookie.unset('loginState')
        this.$router.push('/')
      }
    },
    openDialog(){
      this.loginVisible = true;
      if(cookie.get('userName')){
        this.loginInfo.user_name = cookie.get('userName')
        this.loginInfo.user_password = cookie.get('userPassword')
      }
    },
    selectItem(index,indexPath){
      this.activeIndex = index
    },
    getCurrentURL(){
      let href = window.location.href
      let paramIndex = href.lastIndexOf('/')
      let route = href.slice(paramIndex+1)
      switch(route){
        case '': this.activeIndex = '1'; break;
        case 'newest': this.activeIndex = '2'; break;
        case 'recommend': this.activeIndex = '3'; break;
        case 'user': this.activeIndex = '4'; break;
        case 'write': this.activeIndex = '4'; break;
      }
    }
  },
  computed: {
    loginState() {
      return this.$store.state.ifLogin
    },
    userData() {
      return this.$store.state.userData
    }
  },
  watch: {
    '$route' (to, from) {
        this.getCurrentURL()
    }
  }
}

</script>
<style lang="scss" scoped>
@import '../common/mixin.scss';
.header-wrap {
  height: 61px;
}

.header {
  width: 100%;
  box-sizing: border-box;
  min-width: 1080px;
  padding: 0 60px;
  position: fixed;
  z-index: 10;
  top: 0;
}

.log-reg {
  float: right;
  margin-top: 10px;
}

.login-btn {
  width: 100%;
}

.user-info {
  float: right;
  margin-top: 15px;
}

.user-portrait {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  vertical-align: middle;
}

.user-name {
  margin-left: 10px;
}

</style>
