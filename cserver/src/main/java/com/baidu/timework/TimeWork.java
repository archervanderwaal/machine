package com.baidu.timework;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.serivce.UpdateServerInfosService;

/**
 * @author mayongbin01
 *
 * regular tasks:
 * update db serverInfo for each server
 *
 * Created by mayongbin01 on 2017/1/22.
 */
public class TimeWork extends TimerTask {

    @Autowired
    private UpdateServerInfosService updateServerInfosService;

    @Override
    public void run() {
        updateServerInfosService.autoUpdate();
    }
}
