<template>
  <div>
    <user-header></user-header>
    <user-main></user-main>
  </div>
</template>
<script>
import UserMain from './userMain.vue'
import UserHeader from './../components/userHeader.vue'
export default {
  components: {
    UserMain,
    UserHeader
  }
}

</script>
<style scoped lang="scss">
@import '../common/mixin.scss';
.deputy-header {
  width: 100%;
  min-width: 1080px;
  height: 80px;
  background-color: #f0f0f0;
  border-bottom: 1px solid #dcdcdc;
}

.header-wrap {
  width: 1080px;
  height: 80px;
  margin: 0 auto;
  h1 {
    float: left;
    @include titleNormal('h1');
    margin-top: (80px - 28) / 2;
    color: #F36363;
    border-right: 1px solid #dcdcdc;
    padding-right: 15px;
  }
  div {
    color: #333;
    line-height: 40px;
    font-size: 20px;
    font-weight: normal;
    width: 148px;
    float: left;
    margin: {
      top: 20px;
      left: 20px;
    }
  }
  .search-box {
    float: right;
    width: 260px;
    height: 34px;
    font-size: 14px;
  }
}

</style>
