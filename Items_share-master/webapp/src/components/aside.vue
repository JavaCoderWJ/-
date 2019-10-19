<template>
  <div class="wrap">
  		<div class="feedback" @click="dialogShow = true"><i class="el-icon-edit-outline"></i></div>
    	<div class="backtop" v-show="ifShow" @click="backtop"><i class="el-icon-arrow-up"></i></div>
    	<el-dialog center title="提交反馈" :visible.sync="dialogShow" width="40%" class="dialog">
    		<el-form :model="feedbackObj" enctype="multipart/form-data">
    			<el-form-item>
    				<el-input v-model="feedbackObj.feedback" placeholder="告诉我们你的建议或你遇到的问题" type="textarea" rows="8" resize="none"></el-input>
    			</el-form-item>
    		</el-form>
    		<el-button type="primary" slot="footer" @click="submitFeedback">提交</el-button>
    	</el-dialog>
  </div>
</template>
<script>
import axios from 'axios'
import {createFormData} from '../utils/formData.js'
import {throttle} from '../utils/throttle.js'
export default {
  data() {
    return {
      ifShow: false,
      dialogShow: false,
     	feedbackObj: {
        feedback: ''
      },
      timer: null
    }
  },
  created() {
    const vm = this
    window.addEventListener('scroll', function() {
      throttle(vm.changeShow)
    }, false)
  },
  methods: {
    changeShow() {
      clearInterval(this.timer)
      let scrollTop = document.body.scrollTop || document.documentElement.scrollTop
      if (scrollTop == 0) {
        this.ifShow = false
      } else {
        this.ifShow = true
      }
    },
    backtop() {
      let scrollTop, speed
      this.timer = setInterval(function() {
        scrollTop = document.body.scrollTop || document.documentElement.scrollTop
        if (scrollTop == 0) {
          clearInterval(this.timer)
        } else {
          speed = Math.floor(-scrollTop / 5)
          document.body.scrollTop = document.documentElement.scrollTop = scrollTop + speed
        }
      }, 40)
    },
    submitFeedback(){
      if(this.$store.state.ifLogin){
        let data = createFormData(this.feedbackObj)
        axios.post('http://203.195.151.80:8080/Items_share/HomePage/insertUserFeedback',data).then((response)=>{
          if(response.data.statue == 1){
            this.dialogShow = false;
            this.$message({
              message: '提交成功,谢谢您的反馈',
              type:'success'
            })
          }else{
            console.log(response)
            this.$message.error(response.data.message)
          }
        }).catch((err)=>{
          console.log(err)
        })
      }else{
        this.dialogShow = false
        this.$message.error('你还没登陆哟')
      }
    }
  }
}

</script>
<style scoped lang="scss">
.wrap {
  position: fixed;
  right: 3%;
  bottom: 5%;
  z-index: 5000;
}

.feedback {
  width: 35px;
  height: 35px;
  font-size: 16px;
  line-height: 35px;
  text-align: center;
  cursor: pointer;
  color: #988E8E;
  i {
    font-size: 30px;
  }
}

.backtop {
  @extend .feedback;
}

.submit-btn{
	width: 60%;
}
</style>
