package com.baidu.serivce;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dal.dao.ServerRepository;
import com.baidu.dal.model.Server;
import com.baidu.dal.model.ServerInfo;

/**
 *         <p>
 *         create by mayongbin01 2017/01/22
 *         <p>
 *         Service, in order to auto
 *         <p>
 *         update serverInfos
 */
@Service
public class UpdateServerInfosService {

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private ExecuteShellService executeShellService;

    @Autowired
    private HandleDataService handleDataService;

    private Logger logger = LoggerFactory.getLogger(UpdateServerInfosService.class);

    public List<Server> autoUpdate() {

        List<Server> canNotConnectServers = new ArrayList<>();
        List<Server> servers = serverRepository.findAll();

        for (Server server : servers) {
            try {
                ArrayList<String> stdout = executeShellService.executeShell(server);

                List<ServerInfo> serverInfos = handleDataService.handleData(stdout);

                server.getServerInfos().clear();
                server.getServerInfos().addAll(serverInfos);
                serverRepository.save(server);
            } catch (Exception e) {
                canNotConnectServers.add(server);
                logger.error(e.toString());
            }
        }
        return canNotConnectServers;
    }
}
