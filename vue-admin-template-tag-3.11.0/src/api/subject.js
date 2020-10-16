import request from '@/utils/request'//模板提供的组建

export default{
    //课程分类列表
    getAllSubject(){
        return request({
            //后端controller里面的路径
            url: '/eduservice/subject/getAllSubject',
            //提交方式
            method: 'get'
          })
    }

}
