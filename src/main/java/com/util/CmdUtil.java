package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CmdUtil {
    public boolean exec(String cmd, String msg) throws IOException, InterruptedException {

        final String METHOD_NAME = "runCMD[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]";
        String printstr = null != msg ? ": " + msg : "";
        System.out.println(METHOD_NAME + "#cmd: " + cmd + printstr);

        Process p = Runtime.getRuntime().exec(cmd);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String readLine = br.readLine();
            StringBuilder builder = new StringBuilder();
            while (readLine != null) {
                readLine = br.readLine();
                builder.append(readLine);
            }

//            System.out.println(METHOD_NAME + "#readLine: " + builder.toString());
            p.waitFor();
            int i = p.exitValue();
            //System.out.println(METHOD_NAME + "#exitValue = " + i);
            if (i == 0) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            System.err.println(METHOD_NAME + "#ErrMsg=" + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }


    public void sleep(int time) {
        try {
            System.out.println("sleep [" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]#time:" + time + "s");
            Thread.sleep(1000 * time);
        } catch (Exception e) {
            System.out.println("Got an exception!");
        }

    }

    public void sleep(long time) {
        try {
            System.out.println("sleep [" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]#time:" + time + "ms");
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println("Got an exception!");
        }
    }

    public void shutdown(int time) {
        try {
            System.out.println("shutdown -s -t " + time + "");
            time *= 60;
            exec("r"+ time, "关机");
        } catch (Exception e) {
            System.out.println("Got an exception!");
        }
    }


}
