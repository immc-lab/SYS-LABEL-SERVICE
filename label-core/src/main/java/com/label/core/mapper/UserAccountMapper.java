package com.label.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.label.core.pojo.entity.CurrentUserMessage;
import com.label.core.pojo.entity.UserAccount;
import com.label.core.pojo.vo.admin.GetAllUserRes;
import com.label.core.pojo.vo.admin.SaveNewUserReq;
import com.label.core.pojo.vo.admin.UserItem;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户账户 Mapper 接口
 * </p>
 *
 * @author 豆豆
 * @since 2022-04-23
 */
@Mapper
public interface UserAccountMapper extends BaseMapper<UserAccount> {

    List<UserAccount> searchUser(@Param("user_account") String account);

    List<UserAccount> userLogin(@Param("user_account") String account,@Param("user_password") String password);

    int  userRegister(@Param("user_account") String account,
                      @Param("user_password") String password,
                      @Param("name") String name,
                      @Param("user_id") String userId);

    CurrentUserMessage getCurrentUserMessage(@Param("user_key") String userKey);


    void saveNewUser(@Param("roles") String roles,
                     @Param("manageTeam")String manageTeam,
                     @Param("belongTeam")String belongTeam,
                     @Param("user_key")String user_key,
                     @Param("req")SaveNewUserReq req,
                     @Param("belongTeamName") String belongTeamName,
                     @Param("manageTeamName")String manageTeamName,
                     @Param("rolesName") String rolesName,
                     @Param("createTime") String createTime
                   );

    void updateUser(@Param("roles") String roles,
                    @Param("manageTeam")String manageTeam,
                    @Param("belongTeam")String belongTeam,
                    @Param("belongTeamName") String belongTeamName,
                    @Param("manageTeamName")String manageTeamName,
                    @Param("req")SaveNewUserReq req,
                    @Param("rolesName") String rolesName
                    );

    Long duplicateCheck(@Param("account") String account);

    List<UserItem> getAllUser();

    void updateUserLoginMessage(@Param("lastLoginTime") String lastLoginTime,
                                @Param("ip") String ip,
                                @Param("userKey") String userKey);

    void disableAccount(@Param("userKey") String userKey, @Param("state") String state);

}
