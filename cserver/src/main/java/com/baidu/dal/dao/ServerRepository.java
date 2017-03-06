package com.baidu.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.dal.model.Server;

/**
 * @author mayongbin01
 *         Created by mayongbin01 on 2017/1/17.
 */
@Repository
public interface ServerRepository extends JpaRepository<Server, Integer>,
        JpaSpecificationExecutor<Server> {

    /**
     * 通过类型查询服务器
     * jpql:　select s from Server s where s.destType = ?1
     *
     * @param destType
     *
     * @return
     */
    List<Server> findByDestType(String destType);

    /**
     * find server by destIp
     *
     * @param destIp
     *
     * @return
     */
    @Query("select s from Server s where s.destIp= :destIp")
    Server findByDestIp(@Param("destIp") String destIp);

    /**
     * error
     *
     * @param destType
     * @param id
     *
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Server s set s.destType=?1 where s.id=?2")
    int updateDestType(String destType, int id);
}
