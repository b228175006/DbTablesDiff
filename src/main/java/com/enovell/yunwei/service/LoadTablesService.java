package com.enovell.yunwei.service;

import com.enovell.yunwei.mapper.TablesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库表格读取类
 * Created by Administrator on 2017/6/8.
 */
@Service
public class LoadTablesService {
    @Autowired
    private TablesMapper tablesMapper;
    @Value("${inFilePath}")
    private String inFilepath;
    @Value("${outFilePath}")
    private String outFilePath;

    //读取文件中的表格名称
    public List<String> loadFileTables(){
        File file = new File(inFilepath);
        List<String> tablesNameList = new ArrayList<String>();
        BufferedReader br = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);


            String line = null;
            while ((line = br.readLine()) != null){
                line = line.trim();
                if (line.startsWith("create")){
                    String tableName = line.substring("create table ".length()-1);
                    tablesNameList.add(tableName.trim());
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tablesNameList;
    }

    //读取数据库中的所有表格名称
    public List<String> loadOracketables(){
        return tablesMapper.getTablesNames();
    }

    //文件中表和数据库中表的差集
    public List<String> DifferenceSet(List<String> fileTableList,List<String>OracleTableList){
        fileTableList.removeAll(OracleTableList);
        return fileTableList;
    }

    //根据差集读取文件中的建表SQL
    public void createSqlFile(List<String> diffList){
        File inFile = new File(inFilepath);
        File outFile = new File(outFilePath);
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            FileInputStream fis = new FileInputStream(inFile);
            InputStreamReader isr = new InputStreamReader(fis);
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
            br = new BufferedReader(isr);

            for (String name:
                        diffList) {
                String line = null;
                boolean flag = false;
                br.mark((int)inFile.length());
                while ((line = br.readLine()) != null){
                    line = line.trim();
                        if(flag && line.startsWith("create")){
                            flag=false;
                        }
                        if (line.startsWith("create") && line.endsWith(name)){
                            flag=true;
                        }
                        if(flag){
                            pw.println(line);
                        }
                }
                br.reset();
            }
            pw.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pw!=null){
                try {
                    pw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
