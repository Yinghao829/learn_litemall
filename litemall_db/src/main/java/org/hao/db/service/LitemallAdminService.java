package org.hao.db.service;


import org.hao.db.dao.LitemallAdminMapper;
import org.hao.db.domain.LitemallAdmin;
import org.hao.db.domain.LitemallAdmin.Column;
import org.hao.db.domain.LitemallAdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallAdminService {

    /**
     * 定义一个包含多个数据库列的常量数组，
     * 通常用于指定需要操作的数据库字段（如查询、插入、更新时的字段列表）
     */
    private final Column[] result = new Column[]{Column.id, Column.username, Column.avatar, Column.roleIds};

    @Autowired
    private LitemallAdminMapper adminMapper;

    public List<LitemallAdmin> findAdmin(String username) {
        LitemallAdminExample example = new LitemallAdminExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }
}
