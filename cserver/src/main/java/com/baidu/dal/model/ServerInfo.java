package com.baidu.dal.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author mayongbin01
 *         Created by mayongbin01 on 2017/1/17.
 */
@Entity
public class ServerInfo implements Serializable, Comparable<ServerInfo> {

    /**
     * id
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * service name
     */
    private String serviceName;

    /**
     * service content
     */
    private String content;

    public ServerInfo() {
    }

    public ServerInfo(String serviceName, String content) {
        this.serviceName = serviceName;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ServerInfo [" + "id: " + id + ", serviceName: "
                + serviceName + ", content " + content + "]";
    }

    @Override
    public int compareTo(ServerInfo o) {

        return this.getServiceName().compareTo(o.getServiceName());
    }
}
