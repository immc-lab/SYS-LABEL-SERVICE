package com.label.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户账户
 * </p>
 *
 * @author 豆豆
 * @since 2022-04-23
 */
@Data
//@EqualsAndHashCode(callSuper = false)
//@ApiModel(value="UserAccount对象", description="用户账户")
public class UserAccount implements Serializable {

    private String userId;

    private String userPassword;

    private String name;

    private String userAccount;
}
