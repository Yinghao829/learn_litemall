package org.hao.litemall.db.service;

import org.hao.litemall.db.domain.LitemallGoodsSpecification;

import java.util.List;

public interface LitemallGoodsSpecificationService {

    public List<LitemallGoodsSpecification> queryByGid(Integer gid);

    public LitemallGoodsSpecification findById(Integer id);

    public void deleteByGid(Integer gid);

    public void add(LitemallGoodsSpecification goodsSpecification);


    /**
     * [
     *  {
     *      name: '',
     *      valueList: [ {}, {}]
     *  },
     *  {
     *      name: '',
     *      valueList: [ {}, {}]
     *  }
     * ]
     * @param id id
     * @return re
     */
    public  Object getSpecificationVoList(Integer id);

    public void updateById(LitemallGoodsSpecification goodsSpecification);

}
