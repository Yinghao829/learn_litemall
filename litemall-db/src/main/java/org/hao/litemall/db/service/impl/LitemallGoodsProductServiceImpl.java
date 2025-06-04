package org.hao.litemall.db.service.impl;

import org.hao.litemall.db.dao.GoodsProductMapper;
import org.hao.litemall.db.dao.LitemallGoodsProductMapper;
import org.hao.litemall.db.domain.LitemallGoodsProduct;
import org.hao.litemall.db.domain.LitemallGoodsProductExample;
import org.hao.litemall.db.service.LitemallGoodsProductService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

public class LitemallGoodsProductServiceImpl implements LitemallGoodsProductService {

    @Resource
    private GoodsProductMapper goodsProductMapper;

    @Resource
    private LitemallGoodsProductMapper litemallGoodsProductMapper;

    @Override
    public List<LitemallGoodsProduct> queryByGid(Integer gid) {
        LitemallGoodsProductExample example=new LitemallGoodsProductExample();
        example.or().andGoodsIdEqualTo(gid).andDeletedEqualTo(false);
        return litemallGoodsProductMapper.selectByExample(example);
    }

    @Override
    public LitemallGoodsProduct findById(Integer id) {
        return litemallGoodsProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        litemallGoodsProductMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public void add(LitemallGoodsProduct goodsProduct) {
        goodsProduct.setAddTime(LocalDateTime.now());
        goodsProduct.setUpdateTime(LocalDateTime.now());
        litemallGoodsProductMapper.insertSelective(goodsProduct);
    }

    @Override
    public int count() {
        LitemallGoodsProductExample example=new LitemallGoodsProductExample();
        example.or().andDeletedEqualTo(false);
        return (int) litemallGoodsProductMapper.countByExample(example);
    }

    @Override
    public void deleteByGid(Integer gid) {
        LitemallGoodsProductExample example=new LitemallGoodsProductExample();
        example.or().andGoodsIdEqualTo(gid);
        litemallGoodsProductMapper.logicalDeleteByExample(example);
    }

    @Override
    public int addStock(Integer id, Short num) {
        return goodsProductMapper.addStock(id, num);
    }

    @Override
    public int reduceStock(Integer id, Short num) {
        return goodsProductMapper.reduceStock(id, num);
    }

    @Override
    public void updateById(LitemallGoodsProduct product) {
        product.setUpdateTime(LocalDateTime.now());
        litemallGoodsProductMapper.updateByPrimaryKeySelective(product);
    }
}
