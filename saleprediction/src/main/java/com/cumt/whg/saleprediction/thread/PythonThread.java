package com.cumt.whg.saleprediction.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wanghaogang on 2017/7/1.
 */
public class PythonThread extends Thread{
    private static final String pythonPath = "python";
//    private static final String pythonPath = "/root/anaconda2/bin/python";
//    private static final String pythonPath = "/Users/wanghaogang/Public/anaconda2/bin/python";
    private String pyPath = null;
    private String arguments = null;
    public PythonThread(String pyPath) {
        this.pyPath = pyPath;
    }
    public PythonThread(String pyPath, String arguments) {
        this.pyPath=pyPath;
        this.arguments=arguments;
    }
    public void run() {
        try {
            String command = pythonPath+" "+pyPath;
            String s;
            if(arguments!=null&&!arguments.equals("")) {
                command+=" "+arguments;
            }
            System.out.println(command);
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
