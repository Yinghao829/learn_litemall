package org.hao.litemall.db.service;

import org.hao.litemall.db.domain.LitemallGoodsProduct;

import java.util.List;

public interface LitemallGoodsProductService {

    public List<LitemallGoodsProduct> queryByGid(Integer gid);

    public LitemallGoodsProduct findById(Integer id);

    public void deleteById(Integer id);

    public void add(LitemallGoodsProduct goodsProduct);

    public int count();

    public void deleteByGid(Integer gid);

    public int addStock(Integer id, Short num);

    public int reduceStock(Integer id, Short num);

    public void updateById(LitemallGoodsProduct product);

}
