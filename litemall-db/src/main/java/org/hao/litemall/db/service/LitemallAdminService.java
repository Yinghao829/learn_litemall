package org.hao.litemall.db.service;

import org.hao.litemall.db.domain.LitemallAdmin;


import java.util.List;

public interface LitemallAdminService {
    /**
     *
     * @param username
     * @return
     */
    public List<LitemallAdmin> findAdmin(String username);

    /**
     *
     * @param id
     * @return
     */
    public LitemallAdmin findAdminById(Integer id);

    /**
     *
     * @param username
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    public List<LitemallAdmin> querySelective(String username, Integer page, Integer limit, String sort, String order);

    /**
     *
     * @param admin
     * @return
     */
    public int updateById(LitemallAdmin admin);

    /**
     *
     * @param id
     */
    public void deleteById(Integer id);

    /**
     *
     * @param id
     * @return
     */
    public LitemallAdmin findById(Integer id);

    /**
     *
     * @return
     */
    public List<LitemallAdmin> all();
}
