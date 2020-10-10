<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" controls-position="right" min="0"/>
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
          -->
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career"/>
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
      </el-form-item>

      <!-- 讲师头像：TODO -->

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import teacher from '@/api/teacher'

const defaultForm = {
  name: '',
  sort: 0,
  level: '',
  career: '',
  intro: '',
  avatar: ''
}

export default {
    data() {
        return {//设置teacher对象初始值
            teacher: defaultForm
        }
    },
    watch: {
        $route(to, from) {
            console.log('watch $route')
            this.init()
        }
    },
    created () {
        this.init()
    },
    methods: {
        init() {
            //在页面加载之前，判断路由里面是否有id值
            //如果有id值，调用方法根据id查询
            //从路由里面获取值
            if(this.$route.params && this.$route.params.id) {//修改数据回显
                const id = this.$route.params.id
                //调用方法根据id查询
                this.getTeacherById(id)
            } else {//添加
                //表单数据清空
                this.teacher = { ...defaultForm }
            }
        },
        //实现添加和修改使用同一个表单
        //预留做修改
        saveOrUpdate() {
            //判断点击保存，执行添加操作 还是修改操作
            if(!this.teacher.id) {
                //调用添加的方法
                this.saveTeacher()
            } else {
                this.updateTeacher()
            }
        },

        //根据id查询
        getTeacherById(id) {
            teacher.getTeacherId(id)
                .then(response => {
                    this.teacher = response.data.byId
                })
        },
       //添加讲师的方法
        saveTeacher() {
            //调用后台接口的方法实现添加
            teacher.saveTeacher(this.teacher)
                .then(() => {
                    //请求之后，添加之后，提示用户,
                    return this.$message({
                        type: 'success',
                        message: '添加成功!'
                    })
                }).then(() => {
                    //回到列表页面中，通过路由跳转回到列表页面中
                    this.$router.push({ path: '/teacher/list'})
                })
                .catch(() => {
                    this.$message({
                        type: 'success',
                        message: '添加失败!'
                    })
                })
        },
        //修改讲师的方法
        updateTeacher() {
            teacher.updateTeacherId(this.teacher.id,this.teacher)
                .then(() => {
                    //请求之后，添加之后，提示用户,
                    return this.$message({
                        type: 'success',
                        message: '修改成功!'
                    })
                }).then(() => {
                    //路由跳转
                    this.$router.push({path: '/teacher/list'})
                }).catch(() => {
                    this.$message({
                        type: 'error',
                        message: '修改失败!'
                    })
                })
        }
    }
}

</script>

