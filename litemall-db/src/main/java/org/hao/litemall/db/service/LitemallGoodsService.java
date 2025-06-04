package org.hao.litemall.db.service;

import org.hao.litemall.db.domain.LitemallGoods;

import java.util.List;

public interface LitemallGoodsService {
    /**
     * 获取热卖商品
     * @param offset 页面数量
     * @param limit 页面大小
     * @return List
     */
    public List<LitemallGoods> queryByHot(int offset, int limit);

    /**
     * 获取新品上市
     * @param offset 页面数量
     * @param limit 页面大小
     * @return List
     */
    public List<LitemallGoods> queryByNew(int offset, int limit);

    /**
     * 获取分类下商品
     * @param catList
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallGoods> queryByCategory(List<Integer> catList, int offset, int limit);

    /**
     * 获取分类下商品
     * @param catId
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallGoods> queryByCategory(Integer catId, int offset, int limit);

    /**
     * 动态查询 + 分页排序
     * @param catId
     * @param brandId
     * @param keywords
     * @param isHot
     * @param isNew
     * @param offset
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    public List<LitemallGoods> querySelective(Integer catId, Integer brandId, String keywords, Boolean isHot, Boolean isNew, Integer offset, Integer limit, String sort, String order);

    /**
     * 精确/模糊查询，精确检索和分页展示
     * @param goodsId
     * @param goodsSn
     * @param name
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<LitemallGoods> querySelective(Integer goodsId, String goodsSn, String name, Integer page, Integer size, String sort, String order);

    /**
     * 获取某个商品信息，包含完整信息
     * @param id
     * @return
     */
    public LitemallGoods findById(Integer id);

    /**
     * 获取所有在售物品总数
     * @return
     */
    public Integer queryOnSale();

    /**
     * 获取某个商品信息 ，仅展示相关内容
     * @param id
     * @return
     */
    public LitemallGoods findByIdVO(Integer id);

    /**
     * update
     * @param goods
     * @return
     */
    public int updateById(LitemallGoods goods);

    /**
     * delete
     * @param id
     */
    public void deleteById(Integer id);

    /**
     *
     * @param goods
     */
    public void add(LitemallGoods goods);

    /**
     * 获取所有物品总数，包括在售的和下架的，但是不包括已删除的商品
     * @return
     */
    public int count();

    /**
     * 根据指定的条件查询商品分类ID列表
     * @param brandId
     * @param keywords
     * @param isHot
     * @param isNew
     * @return
     */
    public List<Integer> getCatIds(Integer brandId, String keywords, Boolean isHot, Boolean isNew);

    /**
     * 检查指定名称商品是否已存在
     * @param name
     * @return
     */
    public boolean checkExistByName(String name);

    /**
     * 查询多个商品
     * @param ids
     * @return
     */
    public List<LitemallGoods> queryByIds(Integer[] ids);
}
