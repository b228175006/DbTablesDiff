package com.enovell.yunwei.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/6/8.
 */
@Mapper
@Component
public interface TablesMapper {
    @Select("SELECT TABLE_NAME FROM USER_TABLES")
//    @Results({@Result(column = "TABLE_NAME", property="tableName")})
    public List<String> getTablesNames();
}
