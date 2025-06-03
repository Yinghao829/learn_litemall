package org.hao.litemall.db.service.impl;

import com.github.pagehelper.PageHelper;
import org.hao.litemall.db.dao.LitemallAdminMapper;
import org.hao.litemall.db.domain.LitemallAdmin;
import org.hao.litemall.db.domain.LitemallAdminExample;
import org.hao.litemall.db.service.LitemallAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallAdminServiceImpl implements LitemallAdminService {

    /**
     * 定义一个包含多个数据库列的常量数组，
     * 通常用于指定需要操作的数据库字段（如查询、插入、更新时的字段列表）
     */
    private final LitemallAdmin.Column[] result = new LitemallAdmin.Column[]{LitemallAdmin.Column.id, LitemallAdmin.Column.username, LitemallAdmin.Column.avatar, LitemallAdmin.Column.roleIds};

    @Autowired
    private LitemallAdminMapper adminMapper;

    public List<LitemallAdmin> findAdmin(String username) {
        LitemallAdminExample example = new LitemallAdminExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }

    public LitemallAdmin findAdminById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }


    /**
     *  SELECT
     *     id,
     *     username,
     *     avatar,
     *     roleIds
     * FROM litemall_admin
     * WHERE username LIKE '%输入值%'
     *   AND deleted = false;
     *   result代表要查询的字段，就是id，username，avatar，roleIds
     *   example代表查询条件
     */
    public List<LitemallAdmin> querySelective(String username, Integer page, Integer limit, String sort, String order) {
        LitemallAdminExample example = new LitemallAdminExample();
        LitemallAdminExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return adminMapper.selectByExampleSelective(example, result);
    }

    public int updateById(LitemallAdmin admin) {
        admin.setUpdateTime(LocalDateTime.now());
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    public void deleteById(Integer id) {
        adminMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallAdmin admin) {
        admin.setAddTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.insertSelective(admin);
    }

    public LitemallAdmin findById(Integer id) {
        return adminMapper.selectByPrimaryKeySelective(id, result);
    }

    public List<LitemallAdmin> all() {
        LitemallAdminExample example = new LitemallAdminExample();
        example.or().andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }

}
