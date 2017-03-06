package com.baidu.timework;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.baidu.serivce.UpdateServerInfosService;
/**
 * @author mayongbin01
 *
 * Created by mayongbin01 on 2017/1/23.
 */



@Component
public class ScheduledTasks {

    @Autowired
    private UpdateServerInfosService updateServerInfosService;

    //logging
    private static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(fixedDelay = 24 * 60 * 60 * 24000)
    public void updateServers() {
        updateServerInfosService.autoUpdate();
        logger.info("auto update!......");
    }
}
