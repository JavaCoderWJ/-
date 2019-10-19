<template>
  <div class="wrap">
    <div class="left-wrap">
      <el-upload action="http://203.195.151.80:8080/Items_share/WritePage/uploadPicture" :show-file-list="false" :on-success="handleImgSuccess" class="img-upload" name="picture" :with-credentials="true" :on-progress="uploadProgress">
        <i class="el-icon-plus upload-icon">上传文章头图</i>
      </el-upload>
      <div id="prevImg" ref="prevImg"></div>
      <el-select placeholder="请选择文章类别" v-model="currentItem" class="article-cate" id="select">
        <el-option v-for="(item,index) in categroy" :label="item.goods_type" :value="item.goods_type" :key="index"></el-option>
      </el-select>
      <div class="article-header">
        <input type="text" class="edit-header" placeholder="我的文章标题" v-model="articleHeader">
      </div>
      <ul class="tool-bar" ref="toolBar" :class="{'fixed': ifFixed}">
        <li @click="header(2)">H2</li>
        <li @click="header(3)">H3</li>
        <li @click="bold">B</li>
        <li @click="blockquote">引用</li>
        <li @click="a">链接</li>
        <li>
          <el-upload action="http://203.195.151.80:8080/Items_share/ArticlePicture/addPicture" :show-file-list="false" :on-success="img" name="picture" :with-credentials="true" :on-progress="uploadProgress">
            <i class="el-icon-plus el-icon-picture"></i>
          </el-upload>
        </li>
      </ul>
      <div class="edit-article">
        <el-input type="textarea" :autosize="{ minRows: 15}" placeholder="请使用markdown语法撰写文章" v-model="article" id="edit" class="edit-area" resize="none">
        </el-input>
      </div>
      <div class="btn-group">
        <el-button type="danger" @click="submitArticle">提交</el-button>
        是否允许转载<el-switch v-model="ifReproduced"></el-switch>
      </div>
    </div>
    <div class="right-part">
      <el-alert title="温馨提示：" description="展示消费主张，分享实用好物，传递亲身体验，获取经验知识。请了解并熟悉投稿流程和审核要求，全麦欢迎您的原创文章。"></el-alert class="tips">
    </div>
  </div>
</template>
<script>
import axios from 'axios'
import { throttle } from '../utils/throttle.js'
import { createFormData } from '../utils/formData.js'
export default {
  created() {
    this.getData('http://203.195.151.80:8080/Items_share/HomePage/getDifferentGoods_type', 'categroy')
    const vm = this
    window.addEventListener('scroll', function() {
      throttle(vm.fixToolBar, undefined, 30)
    }, false)
  },
  data() {
    return {
      categroy: [],
      currentItem: '',
      articleHeader: '',
      article: '',
      headerImgUrl: '', // 文章头图图片
      ifFixed: false,
      ifReproduced: false,
      imgNum: 0
    }
  },
  methods: {
    getData(url, variable) {
      axios.get(url).then(response => {
        if (response.data.statue == 1) {
          this[variable] = response.data.data
        } else {
          this.$message.error('请刷新试试')
        }
      })
    },
    header(level) {
      let textbox = document.getElementById('edit')
      let cursorPos = textbox.selectionStart
      let cursorPretext = this.article.slice(0, cursorPos)
      let cursorAfterText = this.article.slice(cursorPos)
      this.article = `${cursorPretext}\n${'#'.repeat(level)} 标题\n${cursorAfterText}`
      textbox.focus()
    },
    bold() {
      let textbox = document.getElementById('edit')
      let cursorPos = textbox.selectionStart
      let cursorPretext = this.article.slice(0, cursorPos)
      let cursorAfterText = this.article.slice(cursorPos)
      this.article = `${cursorPretext} **粗体文本** ${cursorAfterText}`
      textbox.setSelectionRange(cursorPos + 3, cursorPos + 7)
      textbox.focus()
    },
    blockquote() {
      let textbox = document.getElementById('edit')
      let cursorPos = textbox.selectionStart
      let cursorPretext = this.article.slice(0, cursorPos)
      let cursorAfterText = this.article.slice(cursorPos)
      this.article = `${cursorPretext}\n>引用文本\n${cursorAfterText}`
      textbox.focus()
    },
    a() {
      let textbox = document.getElementById('edit')
      let cursorPos = textbox.selectionStart
      let cursorPretext = this.article.slice(0, cursorPos)
      let cursorAfterText = this.article.slice(cursorPos)
      this.$prompt('请输入链接地址','插入链接',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({value}) => {
        this.$message({
          type: 'success',
          message: '插入成功'
        })
        this.article = `${cursorPretext}\n[链接描述](${value})`
      })
      
      textbox.focus()
    },
    img(res,file) {
      if(res.statue == 1){
        let textbox = document.getElementById('edit')
        let cursorPos = textbox.selectionStart
        let cursorPretext = this.article.slice(0, cursorPos)
        let cursorAfterText = this.article.slice(cursorPos)
        this.article = `${cursorPretext}![图片描述](${res.data.picture_url})${cursorAfterText}`
        textbox.focus()
      }else{
        this.$message.error('出错了!')
      }
    },
    uploadProgress(event, file, fileList){
      this.$message('上传中')
    },
    handleImgSuccess(res, file) {
      if(res.statue == 1){
        this.$message({
          message: '上传成功',
          type: 'success'
        })
        let reader = new FileReader()
        reader.readAsDataURL(file.raw)
        reader.onload = function(e){
          let img = document.createElement('img')
          img.src=e.currentTarget.result
          img.style.width=680+'px'
          img.style.height = 500 + 'px'
          let prevBox = document.getElementById('prevImg')
          prevBox.appendChild(img)
        }
      }
    },
    fixToolBar() {
      let scrollTop = document.body.scrollTop || document.documentElement.scrollTop
      let baseDis = 270
      if(this.$refs.prevImg.childNodes.length !=0){
        baseDis += this.$refs.prevImg.offsetHeight
      }
      if (scrollTop >= baseDis) {

        this.ifFixed = true
      } else {
        this.ifFixed = false
      }
    },
    submitArticle() {
      axios.defaults.withCredentials = true
      let data = {
        goods_type: this.currentItem,
        artical_name: this.articleHeader,
        article: `${this.article}`,
        is_reprint: this.ifReproduced
      }
      data = createFormData(data);
      axios.post('http://203.195.151.80:8080/Items_share/WritePage/writeItems_Share', data).then(response => {
        if (response.data.statue == 1) {
          this.$message({
            type: 'success',
            message: '提交成功，请等待审核'
          })
        } else {
          this.$message.error('提交失败')
        }
      })
    }
  }
}

</script>
<style scoped lang="scss">
@import '../common/mixin.scss';
.wrap {
  width: 1080px;
  margin: 0 auto;
  @extend .clearfix;
}

.left-wrap {
  float: left;
  width: 710px;
}

.article-cate {
  margin-top: 20px;
}

.article-header {
  input {
    width: 710px;
    height: 36px;
    border: none;
    color: rgb(51, 51, 51);
    outline: none;
    font-size: 24px;
    font-weight: 400;
  }
  margin: 10px 0;
}

.tool-bar {
  @extend .clearfix;
  border-top: 1px solid #ccc;
  background-color: #f4f4f4;
  height: 39px;
  width: 710px;
  li {
    float: left;
    padding: 10px 8px;
    color: #666;
    font-size: 18px;
    speak: none;
    font-weight: 400;
    font-variant: normal;
    text-transform: none;
    line-height: 1;
    -webkit-font-smoothing: antialiased;
    cursor: default;
    &:hover {
      background-color: #D3D2D2;
    }
  }
}

.edit-article {
  margin-top: 10px;
}

.edit-area {
  font-size: 16px;
  line-height: 26px;
  width: 710px;
  font-weight: 500;
  textarea {
    overflow: hidden;
  }
}

.img-upload {
  width: 710px;
  height: 50px;
  background-color: rgb(240, 240, 240);
  padding: 13px;
  box-sizing: border-box;
  margin-top: 20px;
  color: #999;
}

.upload-icon {
  font-size: 20px;
}

.btn-group {
  margin-top: 20px;
}

.fixed {
  position: fixed;
  top: 61px;
}

.right-part {
  float: left;
  width: 300px;
  margin-left: 30px;
  margin-top: 20px;
}

.tips {
  font-size: 16px;
}
</style>
