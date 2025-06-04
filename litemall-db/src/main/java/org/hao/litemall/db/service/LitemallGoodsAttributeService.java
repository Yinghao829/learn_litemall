package org.hao.litemall.db.service;

import org.hao.litemall.db.domain.LitemallGoodsAttribute;

import java.util.List;

public interface LitemallGoodsAttributeService {

    /**
     * 根据商品id查询商品属性
     * @param goodsId
     * @return
     */
    public List<LitemallGoodsAttribute> queryByGoodsId(Integer goodsId);

    public void add(LitemallGoodsAttribute goodsAttribute);

    public LitemallGoodsAttribute findById(Integer id);

    public void updateById(LitemallGoodsAttribute goodsAttribute);

    public void deleteById(Integer id);

    public void deleteByGid(Integer gid);
}
