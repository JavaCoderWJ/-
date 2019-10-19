<template>
  <div>
    <h3 class="set">
  		<span>设置</span>
  	</h3>
    <el-tabs type="card" v-model="activeName" class="setting-wrap">
      <el-tab-pane label="个人信息" name="first">
        <div class="info">
          <div class="myself">
            <span class="info-title">用户名：</span>
            <input class="detail" v-model="userInfo.user_name">
            <!-- 						<a href="javascript:;" class="modefied-btn">修改</a> -->
          </div>
          <div class="myself">
            <span class="info-title">个人简介：</span>
            <el-input type="textarea" rows="5" class="detail" resize="none" style="width:370px" :value="getUserData.user_intro" v-model="userInfo.user_intro"></el-input>
          </div>
          <el-button type="danger" class="submit-btn" @click="submitInfo">提交</el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="登陆密码" name="second">
        <el-form :model="password" class="form-info" ref="form">
          <el-form-item label="当前密码：">
            <el-input v-model="password.user_password" placeholder="请输入当前密码" type="password"></el-input>
          </el-form-item>
          <el-form-item label="新密码：">
            <el-input v-model="password.new_password" placeholder="请输入新密码" type="password"></el-input>
          </el-form-item>
          <el-form-item label="确认新密码：">
            <el-input v-model="password.sure_new_password" placeholder="请再输入一次新密码" type="password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="danger" @click="modefiedPsd">确认修改</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import axios from 'axios'
import { createFormData } from '../utils/formData.js'
export default {
  data() {
    return {
      activeName: 'first',
      password: {
        user_password: '',
        new_password: '',
        sure_new_password: ''
      },
      userInfo: {
        user_name: '',
        user_intro: ''
      },
      modifiable: false
    }
  },
  computed: {
    getUserData() {
      return this.$store.state.userData
    }
  },
  methods: {
    modefiedPsd() {
      let data = createFormData(this.password)
      axios.post('http://203.195.151.80:8080/Items_share/PersonalCenter/updatePassword', data).then(response => {
        if (response.data.statue == 1) {
          this.$alert('修改密码成功', {
            confirmButtonText: '确定',
            callback: (action) => {
              this.$refs['form'].resetFields()
            }
          })
        } else {
          this.$message.error(response.data.message)
        }
      })
    },
    submitInfo() {
      axios.defaults.withCredentials = true
      let data = createFormData(this.userInfo)
      axios.post('http://203.195.151.80:8080/Items_share/PersonalCenter/updatePersonalDetails', data).then(response => {
        if (response.data.statue == 1) {
          this.$message({
            type: 'success',
            message: '信息修改成功'
          })
        } else {
          this.$message.error(response.data.message)
        }
      })
    }
  }
}

</script>
<style scoped lang="scss">
@import '../common/mixin.scss';
.set {
  @include titleNormal('h3');
  margin-top: 30px;
  border-bottom: 1px solid #eee;
  padding-bottom: 5px;
  span {
    border-bottom: 2px solid #d74140;
    padding-bottom: 5px;
  }
}

.setting-wrap {
  margin-top: 10px;
}

.info {
  margin-top: 15px;
}

.myself {
  font-size: 14px;
  margin-bottom: 30px;
  @extend .clearfix;
}

.info-title {
  float: left;
  width: 100px;
  color: #999;
  text-align: right;
  letter-spacing: 6px;
}

.detail {
  color: #666;
  float: left;
  width: 170px;
  display: block;
  border: none;
  outline: none;
}

.modefied-btn {
  float: left;
  margin-left: 60px;
  font-size: 12px;
  color: #333;
  text-decoration: none;
}

.submit-btn {
  margin-left: 100px;
  width: 120px;
}

.form-info {
  margin-top: 15px;
  width: 470px;
}

</style>
