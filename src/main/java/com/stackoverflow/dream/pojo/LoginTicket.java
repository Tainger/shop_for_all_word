package com.stackoverflow.dream.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author rocky
 * @ClassName LoginTicket
 * @Description
 * @Create by rocky
 * @Date 2020/10/17 上午10:09
 */
public class LoginTicket implements Serializable {


    /**
     * 主键id
     */
    private int id;

    /**
     * 用户id
     */
    private int userId;
    /**
     * token
     */
    private String ticket;
    /**
     * ticket 的状态
     */
    private int status;
    /**
     * 生成过期时间
     */
    private Date expired;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "LoginTicket{" +
                "id=" + id +
                ", userId=" + userId +
                ", ticket='" + ticket + '\'' +
                ", status=" + status +
                ", expired=" + expired +
                '}';
    }
}
