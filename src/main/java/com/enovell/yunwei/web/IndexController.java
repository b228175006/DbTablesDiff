package com.enovell.yunwei.web;

import com.enovell.yunwei.service.LoadTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/6/8.
 */
@RestController
public class IndexController {
    @Autowired
    private LoadTablesService loadTablesService;
    @RequestMapping("/go")
    public String go(){
        List<String> diff = loadTablesService.DifferenceSet(
                loadTablesService.loadFileTables(),
                loadTablesService.loadOracketables()
        );
        loadTablesService.createSqlFile(diff);
        return "执行完毕!";
    }
}
