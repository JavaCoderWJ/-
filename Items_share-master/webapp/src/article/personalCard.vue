<template>
	<div class="card">
		<h3>笔记作者</h3>
		<div class="writer-info">
			<div class="avatar">
				<img :src="writerInfo.itemsPojo.userPojo.user_head">
			</div>
			<div class="writer-name">
				<h6>{{writerInfo.itemsPojo.userPojo.user_name}}</h6>
				<p class="brief">{{writerInfo.itemsPojo.userPojo.user_intro}}</p>
			</div>
		</div>
		<div class="card-info">
			<div class="inner">
				<span>原创</span><br>
				<span>{{writerInfo.AllOriginal}}</span>
			</div>
			<div class="inner">
				<span>粉丝</span><br>
				<span>{{writerInfo.itemsPojo.userPojo.fans}}</span>
			</div>
			<div class="inner">
				<span>获赞</span><br>
				<span>{{writerInfo.itemsPojo.userPojo.won_praise_for}}</span>
			</div>
			<div class="inner">
				<span>有用</span><br>
				<span>{{writerInfo.Alluserful}}</span>
			</div>
		</div>
	</div>
</template>
<script>
	export default{
		props:['writerInfo']
	}
</script>
<style scoped lang="scss">
@import '../common/mixin.scss';
	.card{
		width: 318px;
		height: 180px;
		border-radius: 3px;
		border: 1px solid #eee;
		box-sizing: border-box;
	}
	.writer-info{
		margin: 10px 20px 0;
		padding-bottom: 10px;
		border-bottom: 1px solid #eee;
		overflow: hidden;
	}
	h3{
		@include titleNormal('h3');
		padding: 10px;
		border-bottom: 1px solid #eee;
		font-weight: 500;
	}
	.avatar{
		float: left;
		img{
			width: 50px;
			height: 50px;
			border-radius: 50%;
		}
	}
	.writer-name{
		float: left;
		margin-left: 15px;
		h6{
			@include titleNormal('h6');
			margin-bottom: 4px;
		}
	}
	.brief{
		@include wordBreak();
	}
	.card-info{
		overflow: hidden;
	}
	.inner{
		float: left;
		width: (316px / 4);
		box-sizing: border-box;
		padding: 15px;
		span{
			font-size: 14px;
			&:last-child{
				display: block;
				margin-top: 5px;
				color: #4f4f4f;
				text-indent: 5px;
			}
			&:first-child{
				color: #999;
			}
		}
	}
</style>