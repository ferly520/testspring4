package com.dota.framework.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface LoginMapper {
    @Select("select user_code USERCODE,USER_PASSWORD USERPASSWORD from users where user_code=#{userCode}")
    HashMap<String,Object> queryUserForUserCode(@Param("userCode")String userCode);
    @Select("select pr.ROLE_CODE ROLECODE from perm_user_role pur join perm_role pr on pur.ROLE_PK = pr.PK  where pur.USER_CODE=#{userCode}")
    List<HashMap<String,Object>> queryRoleDetailForUserCode(@Param("userCode")String userCode);
    @Select("SELECT pr.ROLE_CODE ROLECODE, pr.ROLE_NAME ROLENAME, pfg.FUNCTION_URL FUNCTIONURL FROM perm_role pr JOIN perm_role_function prf ON pr.PK = prf.ROLE_PK JOIN perm_function_group pfg ON pfg.PK = prf.FUNCTION_PK order by pfg.FUNCTION_URL")
    List<HashMap<String,Object>> queryRoleUrl();
}
 