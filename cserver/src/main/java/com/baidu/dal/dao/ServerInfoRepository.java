package com.baidu.dal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baidu.dal.model.ServerInfo;

/**
 *  <p>Created by mayongbin01 on 2017/1/17.</p>
 */
@Repository
public interface ServerInfoRepository extends JpaRepository<ServerInfo, Integer> {

}