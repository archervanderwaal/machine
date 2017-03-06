package com.baidu.dal.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baidu.dal.model.ServerInfo;

/**
 * @author mayongbin01
 *         Created by mayongbin01 on 2017/1/17.
 */
@Repository
public interface ServerInfoRepository extends JpaRepository<ServerInfo, Integer> {

//    @Modifying
//    @Transactional
//    @Query(name = "delete from ServerInfo s_i where s_i.server_id = ?1")
//    int deleteByServerId(int serverId);
}