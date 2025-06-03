package org.hao.litemall.db.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.hao.litemall.db.dao.LitemallGoodsMapper;
import org.hao.litemall.db.domain.LitemallGoods;
import org.hao.litemall.db.domain.LitemallGoods.Column;
import org.hao.litemall.db.domain.LitemallGoodsExample;
import org.hao.litemall.db.domain.LitemallUserExample;
import org.hao.litemall.db.service.LitemallGoodsService;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

public class LitemallGoodsServiceImpl implements LitemallGoodsService {

    Column[] columns = new Column[]{Column.id, Column.name, Column.brief, Column.picUrl, Column.isHot, Column.isNew, Column.counterPrice, Column.retailPrice};

    @Resource
    private LitemallGoodsMapper goodsMapper;

    @Override
    public List<LitemallGoods> queryByHot(int offset, int limit) {
        LitemallGoodsExample example = new LitemallGoodsExample();
        example.or().andIsHotEqualTo(true).andIsOnSaleEqualTo(true).andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return goodsMapper.selectByExampleSelective(example, columns);
    }

    @Override
    public List<LitemallGoods> queryByNew(int offset, int limit) {
        LitemallGoodsExample example = new LitemallGoodsExample();
        example.or().andIsNewEqualTo(true).andIsOnSaleEqualTo(true).andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return goodsMapper.selectByExampleSelective(example, columns);
    }

    @Override
    public List<LitemallGoods> queryByCategory(List<Integer> catList, int offset, int limit) {
        LitemallGoodsExample example = new LitemallGoodsExample();
        example.or().andCategoryIdIn(catList).andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return goodsMapper.selectByExampleSelective(example, columns);
    }

    @Override
    public List<LitemallGoods> queryByCategory(Integer catId, int offset, int limit) {
        LitemallGoodsExample example = new LitemallGoodsExample();
        example.or().andCategoryIdEqualTo(catId).andDeletedEqualTo(false);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return goodsMapper.selectByExampleSelective(example, columns);
    }

    @Override
    public List<LitemallGoods> querySelective(Integer catId, Integer brandId, String keywords, Boolean isHot, Boolean isNew, Integer offset, Integer limit, String sort, String order) {
        LitemallGoodsExample example = new LitemallGoodsExample();
        LitemallGoodsExample.Criteria criteria1 = example.or();
        LitemallGoodsExample.Criteria criteria2 = example.or();
        if (catId != null && catId != 0) {
            criteria1.andCategoryIdEqualTo(catId);
            criteria2.andCategoryIdEqualTo(catId);
        }
        if (brandId != null && brandId != 0) {
            criteria1.andBrandIdEqualTo(brandId);
            criteria2.andBrandIdEqualTo(brandId);
        }
        if (isNew != null) {
            criteria1.andIsNewEqualTo(isNew);
            criteria2.andIsNewEqualTo(isNew);
        }
        if (isHot != null) {
            criteria1.andIsHotEqualTo(isHot);
            criteria2.andIsHotEqualTo(isHot);
        }
        if (keywords != null && !keywords.isEmpty()) {
            criteria1.andKeywordsLike("%" + keywords + "%");
            criteria2.andNameLike("%" + keywords + "%");
        }

        if (!sort.isEmpty() && !order.isEmpty()) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(offset, limit);
        return goodsMapper.selectByExampleSelective(example, columns);
    }

    @Override
    public List<LitemallGoods> querySelective(Integer goodsId, String goodsSn, String name, Integer page, Integer size, String sort, String order) {
        return List.of();
    }

    @Override
    public LitemallGoods findById(Integer id) {
        return null;
    }

    @Override
    public Integer queryOnSale() {
        return 0;
    }

    @Override
    public LitemallGoods findByIdVO(Integer id) {
        return null;
    }

    @Override
    public int updateById(LitemallGoods goods) {
        return 0;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void add(LitemallGoods goods) {

    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<Integer> getCatIds(Integer brandId, String keywords, Boolean isHot, Boolean isNew) {
        return List.of();
    }

    @Override
    public boolean checkExistByName(String name) {
        return false;
    }

    @Override
    public List<LitemallGoods> queryByIds(Integer[] ids) {
        return List.of();
    }
}
