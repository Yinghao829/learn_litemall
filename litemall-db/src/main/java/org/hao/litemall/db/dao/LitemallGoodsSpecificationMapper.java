package org.hao.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.hao.litemall.db.domain.LitemallGoodsSpecification;
import org.hao.litemall.db.domain.LitemallGoodsSpecificationExample;

public interface LitemallGoodsSpecificationMapper {
    long countByExample(LitemallGoodsSpecificationExample example);

    int deleteByExample(LitemallGoodsSpecificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LitemallGoodsSpecification record);

    int insertSelective(LitemallGoodsSpecification record);

    LitemallGoodsSpecification selectOneByExample(LitemallGoodsSpecificationExample example);

    LitemallGoodsSpecification selectOneByExampleSelective(@Param("example") LitemallGoodsSpecificationExample example, @Param("selective") LitemallGoodsSpecification.Column ... selective);

    List<LitemallGoodsSpecification> selectByExampleSelective(@Param("example") LitemallGoodsSpecificationExample example, @Param("selective") LitemallGoodsSpecification.Column ... selective);

    List<LitemallGoodsSpecification> selectByExample(LitemallGoodsSpecificationExample example);

    LitemallGoodsSpecification selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallGoodsSpecification.Column ... selective);

    LitemallGoodsSpecification selectByPrimaryKey(Integer id);

    LitemallGoodsSpecification selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    int updateByExampleSelective(@Param("record") LitemallGoodsSpecification record, @Param("example") LitemallGoodsSpecificationExample example);

    int updateByExample(@Param("record") LitemallGoodsSpecification record, @Param("example") LitemallGoodsSpecificationExample example);

    int updateByPrimaryKeySelective(LitemallGoodsSpecification record);

    int updateByPrimaryKey(LitemallGoodsSpecification record);

    int logicalDeleteByExample(@Param("example") LitemallGoodsSpecificationExample example);

    int logicalDeleteByPrimaryKey(Integer id);
}