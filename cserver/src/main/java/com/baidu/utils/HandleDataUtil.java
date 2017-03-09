package com.baidu.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.baidu.dal.model.ServerInfo;

/**
 *         <p>
 *         Created by mayongbin01 on 2017/1/18.
 *         <p>
 *         util, in order to process execute shell result data
 */
@Component
public class HandleDataUtil {

    private Logger logger = LoggerFactory.getLogger(HandleDataUtil.class);

    /**
     * handle execute shell result data , and
     * save information into List<ServerInfo>
     * collection</>
     *
     * @param stdout
     */
    public List<ServerInfo> handleData(ArrayList<String> stdout) {

        for (String string : stdout) {
            logger.info(string);
        }

        ArrayList<ServerInfo> serverInfos = new ArrayList<>();

        String key = "";

        String value = "";

        for (String string : stdout) {
            string = string.replaceAll(" +", " ");
            if (!string.contains(" ")) {
                key = string;
            } else {
                if (!string.contains("00:00:00")) {
                    if (key.equals("tomcat")) {
                        String[] datas = string.split(" ");
                        for (String data1 : datas) {
                            if (data1.startsWith("-Dcatalina.home=")) {
                                String[] data = data1.split("/");

                                value += data[data.length - 1] + "/";
                            }
                        }
                    } else {
                        serverInfos.add(new ServerInfo(key, key));
                    }
                }
            }
        }
        serverInfos.add(new ServerInfo("tomcat", value));

        return serverInfos;
    }
}
