package com.cumt.whg.saleprediction.controller;

import com.cumt.whg.saleprediction.model.Agent;
import com.cumt.whg.saleprediction.thread.PythonThread;
import com.cumt.whg.saleprediction.util.FileUtils;
import com.cumt.whg.saleprediction.util.PythonData;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Scanner;

@Controller
@RequestMapping("/")
public class MainController {

    public static String basePath = null;
    public static String uploadPath = null;
    public static String functionalModelPath = null;
    static {
        if(basePath==null) {
//            basePath = request.getServletContext().getRealPath("/");
            basePath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
            if(!basePath.endsWith(File.separator))
                basePath += File.separator;
            uploadPath = basePath + "uploads";
            if(!new File(uploadPath).isDirectory()) {
                new File(uploadPath).mkdir();
            }
            functionalModelPath = basePath + "FunctionalModel";
        }
    }

    @RequestMapping(value = {"/uploadForm","/","/predictForm"})
    public ModelAndView uploadForm(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("data_load");
        modelAndView.addAllObjects(model.asMap());
        return modelAndView;
    }

    @RequestMapping(value = "/data_summary")
    public ModelAndView dataSummary(Model model) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(model.asMap());
        Scanner situations = new Scanner(new FileReader(uploadPath+File.separator+"situations.txt"));
        int maxDay;
        int agentNumber;
        int validPair;
        int invalidPair;
        int[] parts = new int[5];
        if(!situations.hasNextInt()) {
            modelAndView.addObject("process","wait");
            modelAndView.setViewName("redirect:uploadForm");
            return modelAndView;
        }
        maxDay = situations.nextInt();
        if(!situations.hasNextInt()) {
            modelAndView.addObject("process","wait");
            modelAndView.setViewName("redirect:uploadForm");
            return modelAndView;
        }
        agentNumber = situations.nextInt();
        if(!situations.hasNextInt()) {
            modelAndView.addObject("process","wait");
            modelAndView.setViewName("redirect:uploadForm");
            return modelAndView;
        }
        validPair = situations.nextInt();
        if(!situations.hasNextInt()) {
            modelAndView.addObject("process","wait");
            modelAndView.setViewName("redirect:uploadForm");
            return modelAndView;
        }
        invalidPair = situations.nextInt();
        if(!situations.hasNextInt()) {
            modelAndView.addObject("process","wait");
            modelAndView.setViewName("redirect:uploadForm");
            return modelAndView;
        }
        for(int i=0;i<5;i++)
            parts[i] = situations.nextInt();
        modelAndView.addObject("size", maxDay);
        modelAndView.addObject("agentNum", agentNumber);
        modelAndView.addObject("validate", validPair);
        modelAndView.addObject("invalidate", invalidPair);
        for(int i=0;i<5;i++)
            modelAndView.addObject("part"+i, parts[i]);
        modelAndView.setViewName("data_summary");
        return modelAndView;
    }

    @RequestMapping(value = "/data_agent")
    public ModelAndView dataAgent(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(model.asMap());
        modelAndView.setViewName("data_agent");
        return modelAndView;
    }

    @RequestMapping(value = "/data_marketrank")
    public ModelAndView dataMarketRank(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(model.asMap());
        modelAndView.setViewName("data_marketrank");
        return modelAndView;
    }

    @RequestMapping(value = "/upload")
    public ModelAndView uploadFile(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("data_load");
        modelAndView.addAllObjects(model.asMap());
        FileUtils.deleteAllFilesOfDir(new File(uploadPath));
        File destFile = new File(uploadPath + File.separator + file.getOriginalFilename());
        file.transferTo(destFile);
        modelAndView.addObject("File", "upload");
        // 耗时操作，先注释调试其他
        new PythonThread(functionalModelPath+File.separator+"read_and_filtrate.py", destFile.getAbsolutePath()).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/submit")
    public ModelAndView submit(@RequestParam("startDay") int startDay, @RequestParam("endDay") int endDay, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:data_summary");
        Scanner status = new Scanner(new FileReader(uploadPath+File.separator+"status.txt"));
        String st = status.nextLine();
        status.close();
        // 等待界面
        if(st.equals("wait")) {
            modelAndView.addObject("process","wait");
            modelAndView.setViewName("data_load");
            return modelAndView;
        }
        Scanner situations = new Scanner(new FileReader(uploadPath+File.separator+"situations.txt"));
        int maxDay = situations.nextInt();
        situations.close();
        if(maxDay+1<startDay || startDay>endDay) {
            modelAndView.addObject("param", "error");
            modelAndView.setViewName("data_load");
            return modelAndView;
        }
        File dayFile = new File(uploadPath+File.separator+"startDay.txt");
        if(!dayFile.exists()) {
            dayFile.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(dayFile);
        out.write((startDay+"").getBytes());
        out.write("\n".getBytes());
        out.write((endDay+"").getBytes());
        out.close();
        String pyPath = functionalModelPath+File.separator+"predict_and_pr.py";
        new PythonThread(pyPath).start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/agentAnalyse")
    public ModelAndView agentAnalyse(@RequestParam("nbr") String agentName) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("nbr", agentName);
        modelAndView.setViewName("data_agent");
        if(!agentName.startsWith("O")) {
            modelAndView.addObject("input", "error");
            return modelAndView;
        }
        String pyPath = functionalModelPath+File.separator+"update_for_nbr.py";
        FileReader dayFile = new FileReader(uploadPath+File.separator+"startDay.txt");
        Scanner scanner = new Scanner(dayFile);
        int startDay = scanner.nextInt();
        int endDay = scanner.nextInt();
        FileOutputStream outputStream = new FileOutputStream(uploadPath+File.separator+"status.txt");
        outputStream.write("wait".getBytes());
        outputStream.close();
        new PythonThread(pyPath, agentName+" "+startDay+" "+endDay).start();
        scanner.close();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true) {
            FileReader status = new FileReader(uploadPath+File.separator+"status.txt");
            scanner = new Scanner(status);
            String st = scanner.nextLine();
            System.out.println(st);
            scanner.close();
            if(st.equals("go"))
                break;
            else {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // 从python的输出获取信息
        List<Agent> agentList = PythonData.getPythonData(uploadPath+File.separator+"nbr_res.txt");
        if(agentList==null) {
            modelAndView.addObject("input", "error");
            return modelAndView;
        }
        modelAndView.addObject("agentList", agentList);
        //将List对象转化为JSON对象
        JSONArray json = JSONArray.fromObject(agentList);
        modelAndView.addObject("json", json.toString());
        return modelAndView;
    }

    @RequestMapping(value = "/dailyAnalyse")
    public ModelAndView dailyAnalyse(@RequestParam("day_id") String dayId, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        String pyPath = functionalModelPath+File.separator+"update_for_day.py";
        FileReader dayFile = new FileReader(uploadPath+File.separator+"startDay.txt");
        Scanner scanner = new Scanner(dayFile);
        int startDay = scanner.nextInt();
        FileOutputStream outputStream = new FileOutputStream(uploadPath+File.separator+"status.txt");
        outputStream.write("wait".getBytes());
        outputStream.close();
        new PythonThread(pyPath, dayId+" "+startDay).start();
        scanner.close();
        FileReader status = null;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true) {
            status = new FileReader(uploadPath+File.separator+"status.txt");
            scanner = new Scanner(status);
            String st = scanner.nextLine();
            scanner.close();
            if(st.equals("go"))
                break;
            else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // 从python的输出获取信息
        List<Agent> agentList = PythonData.getPythonData(uploadPath+File.separator+"daily_res.txt");
        modelAndView.addObject("day", dayId);
        modelAndView.addObject("agentList", agentList);
        modelAndView.setViewName("data_marketrank");
        return modelAndView;
    }

    @RequestMapping(value = "/reupload")
    public ModelAndView reUpload(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("File", "none");
        modelAndView.addObject("process", "go");
//            杀死所有python进程
        String command = "kill -9 $(ps -ef|grep python |awk '$0 !~/grep/ {print $2}' |tr -s '\\n' ' ')";
        Runtime.getRuntime().exec(command);
        FileUtils.deleteAllFilesOfDir(new File(uploadPath));
        modelAndView.setViewName("redirect:data_load");
        return modelAndView;
    }

    @RequestMapping(value = "/download/{filename}")
    public void download(@PathVariable("filename") String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置文件MIME类型
        response.setContentType(request.getServletContext().getMimeType(fileName));
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename="+fileName);
        //读取目标文件，通过response将目标文件写到客户端
        //获取目标文件的绝对路径
        String fullFileName = uploadPath + File.separator + fileName;
        //读取文件
        if(!new File(fullFileName).exists()) {
            return;
        }
        InputStream in = new FileInputStream(fullFileName);
        OutputStream out = response.getOutputStream();
        //写文件
        int b;
        while((b=in.read())!= -1) {
            out.write(b);
        }
        in.close();
        out.close();
    }

}
