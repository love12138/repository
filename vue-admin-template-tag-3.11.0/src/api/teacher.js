import request from '@/utils/request'//模板提供的组建

export default{
    getTeacherPageList(page,limit,searchObj){
        return request({
            //后端controller里面的路径
            url: '/eduservice/teacher/moreCondtionPageList/'+page+'/'+limit,
            //提交方式
            method: 'post',
            //传递条件对象,如果传递json数据，使用data。如果不是json，使用params
            data: searchObj
          })
    },removeById(id) {
        return request({
            url: `/eduservice/teacher/${id}`,
            method: 'delete'
        })
    },saveTeacher(teacher) {
        return request({
            url: '/eduservice/teacher/addTeacher',
            method: 'post',
            data: teacher
        })
    },getTeacherId(id) {
        return request({
            url: '/eduservice/teacher/getTeacherInfo/'+id,
            method: 'get'
        })
    },updateTeacherId(id,teacher) {
        return request({
            url: '/eduservice/teacher/updateTeacher/'+id,
            method: 'post',
            data: teacher
        })
    }

}
