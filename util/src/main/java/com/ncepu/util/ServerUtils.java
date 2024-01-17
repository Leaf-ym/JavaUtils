package com.ncepu.util;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author wengym
 * @version 1.0
 * @desc 服务器工具类
 * @date 2024/1/5 9:33
 */
public class ServerUtils {

    /**
     * 获取会话连接
     *
     * @param host     主机：主机名或IP
     * @param port     端口：一般是22
     * @param userName 用户名
     * @param password 密码
     * @return void
     * @author wengym
     * @date 2024/1/5 9:59
     */
    public static Session getSessionConnect(String host, Integer port, String userName, String password) {
        JSch jsch = new JSch();
        Session session;
        try {
            session = jsch.getSession(userName, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        } catch (JSchException e) {
            e.printStackTrace();
            return null;
        }
        return session;
    }

    /**
     * 关闭会话连接
     *
     * @param session
     * @return void
     * @author wengym
     * @date 2024/1/5 9:59
     */
    public static void disconnectSession(Session session) {
        if (!isSessionConnectAvailable(session)) {
            return;
        }
        session.disconnect();
    }

    /**
     * 获取sftp连接
     *
     * @param session
     * @return com.jcraft.jsch.ChannelSftp
     * @author wengym
     * @date 2024/1/5 10:11
     */
    public static ChannelExec getExecConnect(Session session) {
        if (!isSessionConnectAvailable(session)) {
            return null;
        }
        ChannelExec channel;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            channel.connect();
        } catch (JSchException e) {
            e.printStackTrace();
            return null;
        }
        return channel;
    }

    /**
     * 关闭Exec连接
     *
     * @param channel
     * @return void
     * @author wengym
     * @date 2024/1/5 9:59
     */
    public static void disconnectExec(ChannelExec channel) {
        if (!isExecConnectAvailable(channel)) {
            return;
        }
        channel.disconnect();
    }

    /**
     * 获取sftp连接
     *
     * @param session
     * @return com.jcraft.jsch.ChannelSftp
     * @author wengym
     * @date 2024/1/5 10:11
     */
    public static ChannelSftp getSftpConnect(Session session) {
        if (!isSessionConnectAvailable(session)) {
            return null;
        }
        ChannelSftp channel;
        try {
            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
        } catch (JSchException e) {
            e.printStackTrace();
            return null;
        }
        return channel;
    }

    /**
     * 关闭sftp连接
     *
     * @param channel
     * @return void
     * @author wengym
     * @date 2024/1/5 9:59
     */
    public static void disconnectSftp(ChannelSftp channel) {
        if (!isSftpConnectAvailable(channel)) {
            return;
        }
        channel.disconnect();
    }

    /**
     * Exec连接是否可用
     *
     * @param channel
     * @return java.lang.Boolean
     * @author wengym
     * @date 2024/1/5 9:54
     */
    public static Boolean isExecConnectAvailable(ChannelExec channel) {
        if (channel == null) {
            System.out.println("Exec连接为null");
            return false;
        }
        if (channel.isClosed()) {
            System.out.println("Exec连接已关闭");
            return false;
        }
        if (!channel.isConnected()) {
            System.out.println("Exec连接已断开");
            return false;
        }
        return true;
    }

    /**
     * Sftp连接是否可用
     *
     * @param channel
     * @return java.lang.Boolean
     * @author wengym
     * @date 2024/1/5 9:54
     */
    public static Boolean isSftpConnectAvailable(ChannelSftp channel) {
        if (channel == null) {
            System.out.println("Sftp连接为null");
            return false;
        }
        if (channel.isClosed()) {
            System.out.println("Sftp连接已关闭");
            return false;
        }
        if (!channel.isConnected()) {
            System.out.println("Sftp连接已断开");
            return false;
        }
        return true;
    }

    /**
     * 会话连接是否可用
     *
     * @param session
     * @return java.lang.Boolean
     * @author wengym
     * @date 2024/1/5 14:27
     */
    public static Boolean isSessionConnectAvailable(Session session) {
        if (session == null) {
            System.out.println("会话连接为null");
            return false;
        }
        if (session == null || !session.isConnected()) {
            System.out.println("会话连接已断开");
            return false;
        }
        return true;
    }

    /**
     * 下载文件
     *
     * @param channel
     * @param remoteFilePath
     * @param localFilePath
     * @return java.lang.String
     * @author wengym
     * @date 2024/1/5 9:56
     */
    public static String downloadFile(ChannelSftp channel, String remoteFilePath, String localFilePath) {
        if (!isSftpConnectAvailable(channel)) {
            return "连接不可用";
        }
        try {
            channel.get(remoteFilePath, localFilePath);
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return "下载成功";
    }

    /**
     * 上传文件
     *
     * @param channel
     * @param remoteFilePath
     * @param localFilePath
     * @return java.lang.String
     * @author wengym
     * @date 2024/1/5 9:56
     */
    public static String uploadFile(ChannelSftp channel, String remoteFilePath, String localFilePath) {
        if (!isSftpConnectAvailable(channel)) {
            return "连接不可用";
        }
        try {
            channel.put(localFilePath, remoteFilePath);
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return "上传成功";
    }

    /**
     * 执行命令
     *
     * @param channel
     * @param command
     * @return java.lang.String
     * @author wengym
     * @date 2024/1/5 14:37
     */
    public static String execCommand(ChannelExec channel, String command) {
        if (!isExecConnectAvailable(channel)) {
            return "命令行连接不可用！";
        }
        if (command == null || (command.trim()).isEmpty()) {
            disconnectExec(channel);
            return "命令为空！";
        }
        try {
            // 设置命令
            channel.setCommand(command);
            // 连接并执行命令
            channel.connect();
        } catch (JSchException e) {
            e.printStackTrace();
            disconnectExec(channel);
            return "命令行连接异常，异常信息为：" + e.getMessage();
        }
        StringBuilder line = new StringBuilder();
        try {
            // 获取命令输出流
            InputStream inputStream = channel.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // 读取命令输出
            while (true) {
                String data = reader.readLine();
                if (data == null) {
                    break;
                }
                line.append(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
            disconnectExec(channel);
            return "命令行执行输出接收异常，异常信息为：" + e.getMessage();
        }
        disconnectExec(channel);
        return line.toString();
    }


    public static void main(String[] args) {
        Session session = ServerUtils.getSessionConnect("8.130.51.202", 22, "root", "Cna2022!@#");
        ChannelSftp channelSftp = getSftpConnect(session);
    }
}
