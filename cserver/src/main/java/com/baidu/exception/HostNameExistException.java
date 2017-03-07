package com.baidu.exception;

/**
 * <p>Created by mayongbin01 on 2017/3/7.</p>
 *
 *
 *
 */
public class HostNameExistException extends Exception {
    public HostNameExistException() {
        super();
    }

    public HostNameExistException(String message) {
        super(message);
    }

    public HostNameExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
