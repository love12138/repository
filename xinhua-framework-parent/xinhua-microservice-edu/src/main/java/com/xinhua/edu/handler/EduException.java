package com.xinhua.edu.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor//无参构造
@AllArgsConstructor//有参构造
public class EduException extends RuntimeException{

    private Integer code;//状态码
    private String message;//异常信息

    @Override
public String toString() {
    return "EduException{" +
        "message=" + this.getMessage() +
        ", code=" + code +
        '}';
}

}
