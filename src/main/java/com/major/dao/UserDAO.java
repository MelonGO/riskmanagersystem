package com.major.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import com.major.model.User;

@Mapper
public interface UserDAO {
    String table_name = "user";
    String insert_fields = " name, password, role ";
    String select_fields = " id, name, password, role ";

    @Insert({"insert into ", table_name, "(", insert_fields,
            ") values (#{name},#{password},#{role})"})
    @SelectKey(statement="select last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class,
            statementType = StatementType.PREPARED)
    int addUser(User user);

    @Select({"select ", select_fields, " from ", table_name, " where id=#{id}"})
    User selectById(int id);

    @Select({"select ", select_fields, " from ", table_name, " where name=#{name}"})
    User selectByName(String name);

    @Update({"update ", table_name, " set password=#{password} where id=#{id}"})
    void updatePassword(User user);

    @Delete({"delete from ", table_name, " where id=#{id}"})
    void deleteById(int id);
    
    @Select({"select ", select_fields, " from ", table_name})
    List<User> getAll();
}
