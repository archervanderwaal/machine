package com.baidu.timework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.baidu.serivce.UpdateServerInfosService;

/**
 * @author mayongbin01
 *         <p>
 *         Created by mayongbin01 on 2017/1/23.
 */

@Component
public class ScheduledTasks {

    //logging
    private static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    @Autowired
    private UpdateServerInfosService updateServerInfosService;

    @Scheduled(fixedDelay = 24 * 60 * 60 * 24000)
    public void updateServers() {
        updateServerInfosService.autoUpdate();
        logger.info("auto update!......");
    }
}
