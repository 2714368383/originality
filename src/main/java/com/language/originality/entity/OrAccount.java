package com.language.originality.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * <p>
 *
 * </p>
 *
 * @author ZHAIKAIXUAN
 * @since 2020-01-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ac_id", type = IdType.ASSIGN_UUID)
    private String acId;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    @NotBlank(message = "姓名不能为空串")
    private String acName;

    /**
     * 电话号码
     */
    @NotNull(message = "电话不能为空")
    @NotBlank(message = "电话不能为空串")
    private String acPhone;

    /**
     * 邮箱
     */
    private String acEmail;

    /**
     * 地址
     */
    private String acAddress;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
