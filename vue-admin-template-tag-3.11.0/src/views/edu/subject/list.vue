<template>
  <div class="app-container">
      <!-- 检索输入项 -->
    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;" />
     <!-- ref:唯一标识
     data:要显示的数据
     props:获取节点和子节点的名称
     filter-node-method: 检索的方法-->
    <el-tree
      ref="tree2"
      :data="dataSubject"
      :props="defaultProps"
      :filter-node-method="filterNode"
      class="filter-tree"
      default-expand-all
    />

  </div>
</template>

<script>
import subject from "@/api/subject.js"
export default {
  data() {
    return {
      filterText: '',
      dataSubject: [],//返回所有分类的数据
      defaultProps: {
        children: 'children',
        label: 'title'
      }
    }
  },
  created(){
    this.getList()
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val)
    }
  },

  methods: {
    getList(){
      subject.getAllSubject()
      .then(response => {
        this.dataSubject = response.data.list
      })
    },
    filterNode(value, data) {
      if (!value) return true
      return data.title.toLowerCase().indexOf(value.toLowerCase()) !== -1
    }
  }
}
</script>



