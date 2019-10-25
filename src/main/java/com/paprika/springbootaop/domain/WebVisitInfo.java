package com.paprika.springbootaop.domain;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author adam
 * @date 2019/10/25
 */
@Data
@Entity
@Table(name = "web_visit_log")
public class WebVisitInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "bigint")
    private Integer id;

    @Column(name = "operationName", columnDefinition = "varchar(255) COMMENT '被访问接口的功能'")
    private String operationName;

    @Column(name = "request",columnDefinition="text COMMENT '访问接口的请求内容'")
    private String request;

    @Column(name = "response",columnDefinition="text COMMENT '接口的响应内容'")
    private String response;

    @Column(name = "error",columnDefinition="tinyint COMMENT '访问接口是否出错'")
    private Boolean error;

    @Column(name = "stack",columnDefinition="text COMMENT '异常调用栈信息'")
    private String stack;

    @Column(name = "takeTime", columnDefinition = "bigint COMMENT '访问消耗时间'")
    private Long takeTime;

    @Column(name = "visitTime", columnDefinition = "datetime COMMENT '访问时间'")
    private LocalDateTime visitTime;

}
