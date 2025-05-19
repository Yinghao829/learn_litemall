package org.hao.litemall.admin.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hao.litemall.admin.annotation.RequiresPermissionsDesc;
import org.hao.litemall.core.util.ResponseUtil;
import org.hao.litemall.core.validator.Order;
import org.hao.litemall.core.validator.Sort;
import org.hao.litemall.db.domain.LitemallUser;
import org.hao.litemall.db.service.LitemallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
@Validated // validate：验证
public class UserController {

    @Autowired
    private LitemallUserService userService;

    @RequiresPermissions("admin:user:list")
    @RequiresPermissionsDesc(menu = {"用户管理", "会员管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String username, String mobile,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<LitemallUser> userList = userService.querySelective(username, mobile, page, limit, sort, order);
        return ResponseUtil.okList(userList);
    }

    @RequiresPermissions("admin:user:list")
    @RequiresPermissionsDesc(menu = {"用户管理", "会员管理"}, button = "详情")
    @GetMapping("/detail")
    public Object userDetail(@RequestParam("id") Integer id) {
        LitemallUser user = userService.findById(id);
        return ResponseUtil.ok(user);
    }
    @RequiresPermissions("admin:user:list")
    @RequiresPermissionsDesc(menu = {"用户管理", "会员管理"}, button = "编辑")
    @GetMapping("/update")
    public Object userUpdate(@RequestBody LitemallUser user) {
        return ResponseUtil.ok(userService.updateById(user));
    }
}
