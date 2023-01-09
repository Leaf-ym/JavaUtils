package com.ncepu.util;

import java.io.IOException;

/**
 * @author wengym
 * @version 1.0
 * @desc Windows的相关操作工具
 * 以新文件方式，打开windows记事本，打开已有文件nowjava.txt，打开windows记事本
 * @date 2022/9/19 9:18
 */
public class WindowsUtils {
    public enum ProgramName {
        NOTEPAD("记事本", "notepad"),
        SNIPPING("截图工具", "snippingtool"),
        MSPAINT("画图工具", "mspaint");

        private String label;
        private String value;
        ProgramName(String label, String value) {
            this.label = label;
            this.value = value;
        }
        public String getLabel() {
            return this.label;
        }
        public String getValue() {
            return this.value;
        }
    }

    /**
     * 打开程序
     * 以新文件方式（notepad），打开windows记事本，打开已有文件nowjava.txt（notepad c:\\nowjava.txt），打开windows记事本
     *
     * @param programName
     *
     * @author wengym
     *
     * @date 2022/9/19 9:24
     *
     * @return void
     */
    public static void openProgram(String programName) {
        try {
            Runtime.getRuntime().exec(programName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
