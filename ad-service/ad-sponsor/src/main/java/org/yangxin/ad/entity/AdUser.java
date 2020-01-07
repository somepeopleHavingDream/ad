package org.yangxin.ad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yangxin.ad.constant.CommonStatusEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户
 *
 * @author yangxin
 * 2020/01/07 17:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_user")
public class AdUser {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 用户名
     */
    @Basic
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * token
     */
    @Basic
    @Column(name = "token", nullable = false)
    private String token;

    /**
     * 用户状态
     */
    @Basic
    @Column(name = "user_status", nullable = false)
    private Integer userStatus;

    /**
     * 创建时间
     */
    @Basic
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 更新时间
     */
    @Basic
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    public AdUser(String username, String token) {
        this.username = username;
        this.token = token;
        this.userStatus = CommonStatusEnum.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}
