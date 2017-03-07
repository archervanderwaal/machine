package com.baidu.serivce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dal.model.ServerInfo;
import com.baidu.utils.HandleDataUtil;

/**
 * @author mayongbin01
 *         <p>
 *         create by mayongbin01 2017/01/22
 *         <p>
 *         Service, in order to handle data
 *         that execute shell result</>
 *         <p>
 *         provide interface for controller
 */
@Service
public class HandleDataService {

    //handleDataUtil
    @Autowired
    private HandleDataUtil handleDataUtil;

    /**
     * handle data that execute shell result
     *
     * @param std
     *
     * @return
     */
    public List<ServerInfo> handleData(ArrayList<String> std) {
        return handleDataUtil.handleData(std);
    }
}
