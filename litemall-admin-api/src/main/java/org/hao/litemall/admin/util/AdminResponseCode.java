package org.hao.litemall.admin.util;

public class AdminResponseCode {
    /**
     * 管理员模块错误码
     */
    // 601：管理用户名格式失效
    public static final Integer ADMIN_INVALID_NAME = 601;
    // 602：管理员密码格式失效
    public static final Integer ADMIN_INVALID_PASSWORD = 602;
    // 602：管理员用户名已存在
    public static final Integer ADMIN_NAME_EXIST = 602;
    // 603：禁止修改管理员账户
    public static final Integer ADMIN_ALTER_NOT_ALLOWED = 603;
    // 604：禁止删除管理员账户
    public static final Integer ADMIN_DELETE_NOT_ALLOWED = 604;
    // 605：管理员账户状态异常
    public static final Integer ADMIN_INVALID_ACCOUNT = 605;
    //606：验证码无效
    public static final Integer ADMIN_INVALID_KAPTCHA = 606;
    //607：验证码未填写
    public static final Integer ADMIN_INVALID_KAPTCHA_REQUIRED = 607;
}
