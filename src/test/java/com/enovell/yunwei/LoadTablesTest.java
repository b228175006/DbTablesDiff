package com.enovell.yunwei;

import com.enovell.yunwei.service.LoadTablesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Administrator on 2017/6/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoadTablesTest {
    @Autowired
    private LoadTablesService loadTables;
    @Test
    public void loadFileTables() throws Exception {
        List<String> tableNames = loadTables.loadFileTables();
        for (String name :
                tableNames) {
            System.out.println("name = " + name);
        }
    }
    @Test
    public void DifferenceSet(){
        List<String> tableNames = loadTables.loadFileTables();
        List<String> oracleNames = loadTables.loadOracketables();
        List<String> diff = loadTables.DifferenceSet(tableNames,oracleNames);
        for (String name :
                diff) {
            System.out.println("name = " + name);
        }
    }
    @Test
    public void createSqlFile(){
        List<String> tableNames = loadTables.loadFileTables();
        List<String> oracleNames = loadTables.loadOracketables();
        List<String> diff = loadTables.DifferenceSet(tableNames,oracleNames);
        loadTables.createSqlFile(diff);
    }
}