package org.hao.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.hao.litemall.db.domain.LitemallAdmin;
import org.hao.litemall.db.domain.LitemallAdminExample;

public interface LitemallAdminMapper {
    long countByExample(LitemallAdminExample example);

    int deleteByExample(LitemallAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LitemallAdmin record);

    int insertSelective(LitemallAdmin record);

    LitemallAdmin selectOneByExample(LitemallAdminExample example);

    LitemallAdmin selectOneByExampleSelective(@Param("example") LitemallAdminExample example, @Param("selective") LitemallAdmin.Column ... selective);

    List<LitemallAdmin> selectByExampleSelective(@Param("example") LitemallAdminExample example, @Param("selective") LitemallAdmin.Column ... selective);

    List<LitemallAdmin> selectByExample(LitemallAdminExample example);

    LitemallAdmin selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallAdmin.Column ... selective);

    LitemallAdmin selectByPrimaryKey(Integer id);

    LitemallAdmin selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    int updateByExampleSelective(@Param("record") LitemallAdmin record, @Param("example") LitemallAdminExample example);

    int updateByExample(@Param("record") LitemallAdmin record, @Param("example") LitemallAdminExample example);

    int updateByPrimaryKeySelective(LitemallAdmin record);

    int updateByPrimaryKey(LitemallAdmin record);

    int logicalDeleteByExample(@Param("example") LitemallAdminExample example);

    int logicalDeleteByPrimaryKey(Integer id);
}