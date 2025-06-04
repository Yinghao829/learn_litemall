package org.hao.litemall.db.service.impl;

import org.hao.litemall.db.dao.LitemallGoodsAttributeMapper;
import org.hao.litemall.db.domain.LitemallGoodsAttribute;
import org.hao.litemall.db.domain.LitemallGoodsAttributeExample;
import org.hao.litemall.db.domain.LitemallGoodsExample;
import org.hao.litemall.db.service.LitemallGoodsAttributeService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

public class LitemallGoodsAttributeServiceImpl implements LitemallGoodsAttributeService {

    @Resource
    private LitemallGoodsAttributeMapper litemallGoodsAttributeMapper;

    @Override
    public List<LitemallGoodsAttribute> queryByGoodsId(Integer goodsId) {
        LitemallGoodsAttributeExample example=new LitemallGoodsAttributeExample();
        example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return litemallGoodsAttributeMapper.selectByExample(example);
    }

    @Override
    public void add(LitemallGoodsAttribute goodsAttribute) {
        goodsAttribute.setAddTime(LocalDateTime.now());
        goodsAttribute.setUpdateTime(LocalDateTime.now());
        litemallGoodsAttributeMapper.insertSelective(goodsAttribute);
    }

    @Override
    public LitemallGoodsAttribute findById(Integer id) {
        return litemallGoodsAttributeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateById(LitemallGoodsAttribute goodsAttribute) {
        goodsAttribute.setUpdateTime(LocalDateTime.now());
        litemallGoodsAttributeMapper.updateByPrimaryKeySelective(goodsAttribute);
    }

    @Override
    public void deleteById(Integer id) {
        litemallGoodsAttributeMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public void deleteByGid(Integer gid) {
        LitemallGoodsAttributeExample example=new LitemallGoodsAttributeExample();
        example.or().andGoodsIdEqualTo(gid);
        litemallGoodsAttributeMapper.logicalDeleteByExample(example);
    }
}
