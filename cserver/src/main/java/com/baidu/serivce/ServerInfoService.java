package com.baidu.serivce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dal.dao.ServerInfoRepository;
import com.baidu.dal.model.ServerInfo;

/**
 *         <p>
 *         create by mayongbin01 2017/01/22
 *         <p>
 *         Service
 *         <p>
 *         provide interface for controller
 */
@Service
public class ServerInfoService {

    private static Logger logger = LoggerFactory.getLogger(ServerInfoService.class);

    @Autowired
    private ServerInfoRepository serverInfoRepository;

    /**
     * save serverInfo to DB
     *
     * @param serverInfo
     *
     * @return
     */
    public boolean addServerInfo(ServerInfo serverInfo) {

        return serverInfoRepository.save(serverInfo) == null;
    }

    /**
     * delete serverInfo by id
     *
     * @param id
     */
    public void deleteServerInfo(int id) {
        serverInfoRepository.delete(id);
    }
}
