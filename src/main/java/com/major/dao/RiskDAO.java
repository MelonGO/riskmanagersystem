package com.major.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.mybatis.spring.annotation.MapperScan;

import com.major.model.Risk;

@MapperScan
public interface RiskDAO {
	String TABLE_NAME = "risk";
    String INSET_FIELDS = " name, content, probability, influence, triggerOrThreshold, "
    		+ "submitter, stalker, date ";
    String SELECT_FIELDS = " id, name, content, probability, influence, triggerOrThreshold, submitter, stalker, date ";

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{name},#{content},#{probability},#{influence},#{triggerOrThreshold},#{submitter},#{stalker},#{date})"})
    @SelectKey(statement="select last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class,
            statementType = StatementType.PREPARED)
    int addRisk(Risk risk);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Risk selectById(int id);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);
    
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME})
    List<Risk> selectAll();
    
    
}
