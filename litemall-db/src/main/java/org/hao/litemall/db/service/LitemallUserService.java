package org.hao.litemall.db.service;

import org.hao.litemall.db.domain.LitemallUser;
import org.hao.litemall.db.domain.UserVo;

import java.util.List;

public interface LitemallUserService {
    /**
     * find by id
     * @param id
     * @return
     */
    public LitemallUser findById(Integer id);

    /**
     * find user vo by id
     * @param id
     * @return
     */
    public UserVo findUserVoById(Integer id);

    /**
     * query by o id
     * @param openId
     * @return
     */
    public LitemallUser queryByOid(String openId);

    /**
     * add
     * @param user
     */
    public void add(LitemallUser user);

    /**
     *
     * @param user
     * @return
     */
    public int updateById(LitemallUser user);

    /**
     *
     * @param username
     * @param mobile
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<LitemallUser> querySelective(String username, String mobile, Integer page, Integer size, String sort, String order);
    public int count();

    /**
     *
     * @param username
     * @return
     */
    public List<LitemallUser> queryByUsername(String username);

    /**
     *
     * @param username
     * @return
     */
    public boolean checkUsername(String username);

    /**
     *
     * @param mobile
     * @return
     */
    public List<LitemallUser> queryMobile(String mobile);

    /**
     *
     * @param openId
     * @return
     */
    public List<LitemallUser> queryByOpenId(String openId);

    /**
     *
     * @param id
     */
    public void deleteById(Integer id);
}
