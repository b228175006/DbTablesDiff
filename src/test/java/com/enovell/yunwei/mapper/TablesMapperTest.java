package com.enovell.yunwei.mapper;

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
public class TablesMapperTest {
    @Autowired
    private TablesMapper tablesMapper;

    @Test
    public void getTablesNames() throws Exception {
        List<String> tableNames = tablesMapper.getTablesNames();
        for (String tableName:
             tableNames) {
            System.out.println(tableName);
        }
        System.out.println(tableNames.size());
    }

}