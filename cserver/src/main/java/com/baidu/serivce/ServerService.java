package com.baidu.serivce;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dal.dao.ServerRepository;
import com.baidu.dal.model.Server;

/**
 *         <p>
 *         create by mayongbin01 2017/01/22
 *         <p>
 *         Service
 *         <p>
 *         provide interface for controller
 */
@Service
public class ServerService {

    private static Logger logger = LoggerFactory.getLogger(ServerService.class);

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private ExecuteShellService executeShellService;

    /**
     * get List<ServerInfos>
     *
     * @return
     */
    public List<Server> findAllServers() {
        List<Server> servers = serverRepository.findAll();

        for (Server server : servers) {
            Collections.sort(server.getServerInfos());
        }

        return servers;
    }

    /**
     * add server
     *
     * @param server
     *
     * @return
     */
    public Server addServer(Server server) {

        logger.info("add server, the server is - " + ReflectionToStringBuilder.
                toString(server));

        return serverRepository.save(server);
    }

    /**
     * delete a server
     *
     * @param id
     */
    public void deleteServer(int id) {
        logger.info("delete server's id is : " + id);
        serverRepository.delete(id);
    }

    /**
     * findAllServer by destType
     *
     * @param destType
     *
     * @return
     */
    public List<Server> getServerByDestType(String destType) {

        logger.info("destType is : " + destType);
        List<Server> servers = serverRepository.findByDestType(destType);

        for (Server server : servers) {
            Collections.sort(server.getServerInfos());
        }
        return servers;
    }

    /**
     * findServer by server id
     *
     * @param id
     *
     * @return
     */
    public Server findOneServerById(int id) {

        return serverRepository.findOne(id);
    }

    /**
     * delete by destIp
     *
     * @param destIp
     */
    public void deleteServerByDestIp(String destIp) {
        Server server = serverRepository.findByDestIp(destIp);

        serverRepository.delete(server.getId());
    }

    /**
     * get a server by ip
     *
     * @param destIp
     *
     * @return
     */
    public Server findServerByIp(String destIp) {
        return serverRepository.findByDestIp(destIp);
    }
}
