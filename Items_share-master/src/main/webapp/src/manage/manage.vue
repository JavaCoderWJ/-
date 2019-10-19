<template>
  <div>
  	<h1 class="title1">全麦网后台管理系统</h1>
    <!-- 审核文章表 -->
    <h2 class="title2">文章审核</h2>
    <el-table :data="allArticle" max-height="500" class="table1" border>
      <el-table-column prop="artical_name" label="文章标题"></el-table-column>
			<el-table-column prop="user_name" label="作者"></el-table-column>
			<el-table-column prop="goods_type" label="类别"></el-table-column>
			<el-table-column prop="check_state_intro" label="状态"></el-table-column>
			<el-table-column fixed="right" label="操作">
				<template slot-scope="scope">
					<el-button type="text" @click="handleClick(scope.row)">查看</el-button>
				</template>
			</el-table-column>
    </el-table>
    <h2 class="title2">用户反馈</h2>
    <el-table :data="feedback" max-height="300" class="table2" border>
      <el-table-column prop="user_id" label="用户ID"></el-table-column>
			<el-table-column prop="feedback_time" label="反馈时间"></el-table-column>
			<el-table-column fixed="right" label="操作">
				<template slot-scope="scope">
					<el-button type="text" @click="handleFeedback(scope.row)">查看</el-button>
				</template>
			</el-table-column>
    </el-table>
    <el-dialog :visible.sync="dialogVisible" width="600" title="文章详情">
    	<h1>{{item.artical_name}}</h1>
    	<vue-simple-markdown :source="item.article"></vue-simple-markdown>
    	<div slot="footer">
	      <el-button @click="dialogVisible = false">关 闭</el-button>
	      <el-button type="primary" @click="checkPass" v-show="!item.check_state">审核通过</el-button>
	      <el-button type="primary" @click="checkNotPass" v-show="!item.check_state">不通过</el-button>
	    </div>
    </el-dialog>
    <el-dialog :visible.sync="feedbackShow" width="600" title="反馈详情">
    	<p>{{item.feedback}}</p>
    	<div slot="footer">
	      <el-button @click="feedbackShow = false">关 闭</el-button>
	    </div>
    </el-dialog>
  </div>
</template>
<script>
import Vue from 'vue'
import VueSimpleMarkdown from 'vue-simple-markdown'
import 'vue-simple-markdown/dist/vue-simple-markdown.css'
import axios from 'axios'
Vue.use(VueSimpleMarkdown)
export default {
  created() {
  	this.getData('http://203.195.151.80:8080/Items_share/Admin/getAllItems','allArticle')
  	this.getData('http://203.195.151.80:8080/Items_share/Admin/getAllFeedback','feedback')
  },
  data() {
    return {
    	dialogVisible: false,
    	feedbackShow: false,
    	item: {},
    	source: ``,
      allArticle: [],
      feedback: [],
      notPass: true
    }
  },
  methods: {
    getData(url, variable) {
      axios.get(url).then((response) => {
        if (response.data.statue == 1) {
          this[variable] = response.data.data
        } else {
          this.$message.error('获取数据失败')
        }
      })
    },
    handleClick(row){
    	this.dialogVisible = true
    	this.item = row
    	this.source = row.article
    },
    handleFeedback(row){
    	this.feedbackShow = true
    	this.item = row
    },
    checkPass(){
    	axios.get(`http://203.195.151.80:8080/Items_share/Admin/insertIntoItems?check_id=${this.item.check_id}`).then(res=>{
    		if(res.data.statue == 1){
    			this.$message({
    				type: 'success',
    				message: '审核成功'
    			})
    			this.item.check_state_intro="通过"
    			this.item.check_state = '1'
    		}else{
    			this.$message.error(res.data.message)
    		}
    	})
    },
    checkNotPass(){
    	axios.get(`http://203.195.151.80:8080/Items_share/Admin/itemsNotPass?check_id=${this.item.check_id}`).then(res=>{
    		if(res.data.statue == 1){
    			this.$message({
    				type: 'success',
    				message: '审核成功'
    			})
    		}else{
    			this.$message.error(res.data.message)
    		}
    	})
    }
  }
}

</script>
<style scoped lang="scss">
@import '../common/mixin.scss';
.title1{
	text-align: center;
	@include titleNormal('h1');
	margin: 30px;
}
.title2{
	text-align: center;
	@include titleNormal('h2');
	margin: 30px;
}
.table1{
	width: 1200px;
	margin: 0 auto;
}
.table2{
	width: 800px;
	margin: 0 auto;
}
</style>
