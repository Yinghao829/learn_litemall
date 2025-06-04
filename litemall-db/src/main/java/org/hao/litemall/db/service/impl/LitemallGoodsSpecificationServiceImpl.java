package org.hao.litemall.db.service.impl;

import org.hao.litemall.db.dao.LitemallGoodsSpecificationMapper;
import org.hao.litemall.db.domain.LitemallGoodsSpecification;
import org.hao.litemall.db.domain.LitemallGoodsSpecificationExample;
import org.hao.litemall.db.service.LitemallGoodsSpecificationService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LitemallGoodsSpecificationServiceImpl implements LitemallGoodsSpecificationService {

    @Resource
    LitemallGoodsSpecificationMapper litemallGoodsSpecificationMapper;

    @Override
    public List<LitemallGoodsSpecification> queryByGid(Integer gid) {
        LitemallGoodsSpecificationExample example=new LitemallGoodsSpecificationExample();
        example.or().andGoodsIdEqualTo(gid).andDeletedEqualTo(false);
        return litemallGoodsSpecificationMapper.selectByExample(example);
    }

    @Override
    public LitemallGoodsSpecification findById(Integer id) {
        return litemallGoodsSpecificationMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteByGid(Integer gid) {
        LitemallGoodsSpecificationExample example = new LitemallGoodsSpecificationExample();
        example.or().andGoodsIdEqualTo(gid);
        litemallGoodsSpecificationMapper.logicalDeleteByExample(example);
    }


    @Override
    public void add(LitemallGoodsSpecification goodsSpecification) {
        goodsSpecification.setAddTime(LocalDateTime.now());
        goodsSpecification.setUpdateTime(LocalDateTime.now());
        litemallGoodsSpecificationMapper.insertSelective(goodsSpecification);
    }

    @Override
    public Object getSpecificationVoList(Integer gid) {
        List<LitemallGoodsSpecification> goodsSpecificationList = queryByGid(gid);

        Map<String, VO> map = new HashMap<>();
        List<VO> specificationVoList = new ArrayList<>();

        for (LitemallGoodsSpecification goodsSpecification : goodsSpecificationList) {
            String specification = goodsSpecification.getSpecification();
            VO goodsSpecificationVo = map.get(specification);
            if (goodsSpecificationVo == null) {
                goodsSpecificationVo = new VO();
                goodsSpecificationVo.setName(specification);
                List<LitemallGoodsSpecification> valueList = new ArrayList<>();
                valueList.add(goodsSpecification);
                goodsSpecificationVo.setValueList(valueList);
                map.put(specification, goodsSpecificationVo);
                specificationVoList.add(goodsSpecificationVo);
            } else {
                List<LitemallGoodsSpecification> valueList = goodsSpecificationVo.getValueList();
                valueList.add(goodsSpecification);
            }

        }
        return specificationVoList;
    }

    @Override
    public void updateById(LitemallGoodsSpecification goodsSpecification) {
        goodsSpecification.setUpdateTime(LocalDateTime.now());
        litemallGoodsSpecificationMapper.updateByPrimaryKeySelective(goodsSpecification);
    }

    private class VO {
        private String name;
        private List<LitemallGoodsSpecification> valueList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<LitemallGoodsSpecification> getValueList() {
            return valueList;
        }

        public void setValueList(List<LitemallGoodsSpecification> valueList) {
            this.valueList = valueList;
        }
    }
}
