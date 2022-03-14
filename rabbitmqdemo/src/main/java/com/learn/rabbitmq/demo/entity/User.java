package com.learn.rabbitmq.demo.entity;

import lombok.Data;

/**
 * @author guofei.wu
 * @version v1.0
 * @date 2022/3/12 11:30
 * @since v1.0
 */
@Data
public class User {
    private Long id;
    private String userName;
    private String email;
}
