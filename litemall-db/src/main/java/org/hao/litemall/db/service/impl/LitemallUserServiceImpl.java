package org.hao.litemall.db.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.hao.litemall.db.dao.LitemallUserMapper;

import org.hao.litemall.db.domain.LitemallUser;
import org.hao.litemall.db.domain.LitemallUserExample;
import org.hao.litemall.db.domain.UserVo;
import org.hao.litemall.db.service.LitemallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallUserServiceImpl implements LitemallUserService {

    @Autowired
    private LitemallUserMapper litemallUserMapper;

    // 根据id查找用户
    public LitemallUser findById(Integer id) {
        return litemallUserMapper.selectByPrimaryKey(id);
    }

    // 获取用户名称头像，通过UserVo，避免获取User其他信息
    public UserVo findUserVoById(Integer id) {
        LitemallUser user = findById(id);
        UserVo userVo = new UserVo();
        userVo.setNickname(user.getNickname());
        userVo.setAvatar(user.getAvatar());
        return userVo;
    }

    // 根据微信id查询用户
    public LitemallUser queryByOid(String openId) {
        LitemallUserExample example = new LitemallUserExample();
        example.or().andWeixinOpenidEqualTo(openId).andDeletedEqualTo(false);
        return litemallUserMapper.selectOneByExample(example);
    }

    // 添加用户
    public void add(LitemallUser user) {
        user.setAddTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        litemallUserMapper.insert(user);
    }

    public int updateById(LitemallUser user) {
        user.setUpdateTime(LocalDateTime.now());
        return litemallUserMapper.updateByPrimaryKeySelective(user);
    }

    // PageHelper.startPage(page, size) 的作用是 声明当前查询需要分页，并自动为 SQL 添加分页逻辑，简化开发流程
    public List<LitemallUser> querySelective(String username, String mobile, Integer page, Integer size, String sort, String order) {
        LitemallUserExample example = new LitemallUserExample();
        LitemallUserExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        if (!StringUtils.isEmpty(mobile)) {
            criteria.andMobileEqualTo(mobile);
        }
        criteria.andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, size);
        return litemallUserMapper.selectByExample(example);
    }

    public int count() {
        LitemallUserExample example = new LitemallUserExample();
        example.or().andDeletedEqualTo(false);
        return (int) litemallUserMapper.countByExample(example);
    }

    public List<LitemallUser> queryByUsername(String username) {
        LitemallUserExample example = new LitemallUserExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return litemallUserMapper.selectByExample(example);
    }

    public boolean checkUsername(String username) {
        LitemallUserExample example = new LitemallUserExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return litemallUserMapper.countByExample(example) != 0;
    }

    public List<LitemallUser> queryMobile(String mobile) {
        LitemallUserExample example = new LitemallUserExample();
        example.or().andMobileEqualTo(mobile).andDeletedEqualTo(false);
        return litemallUserMapper.selectByExample(example);
    }

    public List<LitemallUser> queryByOpenId(String openId) {
        LitemallUserExample example = new LitemallUserExample();
        example.or().andWeixinOpenidEqualTo(openId).andDeletedEqualTo(false);
        return litemallUserMapper.selectByExample(example);
    }

    public void deleteById(Integer id) {
        litemallUserMapper.logicalDeleteByPrimaryKey(id);
    }

}
