package org.hao.litemall.core.util;

import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 响应操作结果
 * <pre>
 *  {
 *      errno： 错误码，
 *      errmsg：错误消息，
 *      data：  响应数据
 *  }
 * </pre>
 *
 * <p>
 * 错误码：
 * <ul>
 * <li> 0，成功；
 * <li> 4xx，前端错误，说明前端开发者需要重新了解后端接口使用规范：
 * <ul>
 * <li> 401，参数错误，即前端没有传递后端需要的参数；
 * <li> 402，参数值错误，即前端传递的参数值不符合后端接收范围。
 * </ul>
 * <li> 5xx，后端错误，除501外，说明后端开发者应该继续优化代码，尽量避免返回后端错误码：
 * <ul>
 * <li> 501，验证失败，即后端要求用户登录；
 * <li> 502，系统内部错误，即没有合适命名的后端内部错误；
 * <li> 503，业务不支持，即后端虽然定义了接口，但是还没有实现功能；
 * <li> 504，更新数据失效，即后端采用了乐观锁更新，而并发更新时存在数据更新失效；
 * <li> 505，更新数据失败，即后端数据库更新失败（正常情况应该更新成功）。
 * </ul>
 * <li> 6xx，小商城后端业务错误码，
 * 具体见litemall-admin-api模块的AdminResponseCode。
 * <li> 7xx，管理后台后端业务错误码，
 * 具体见litemall-wx-api模块的WxResponseCode。
 * </ul>
 */
public class ResponseUtil {
    /**
     * 构建一个通用的成功响应（无数据）。
     * <p>
     * 响应格式示例：
     * {
     *   "errno": 0,
     *   "errmsg": "成功"
     * }
     * </p>
     *
     * @return 包含成功状态的对象，无数据字段。
     */
    public static Object ok() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", 0);
        obj.put("errmsg", "成功");
        return obj;
    }

    /**
     * 构建一个携带数据的成功响应。
     * <p>
     * 响应格式示例：
     * {
     *   "errno": 0,
     *   "errmsg": "成功",
     *   "data": {具体数据对象}
     * }
     * </p>
     *
     * @param data 需要返回的业务数据对象。
     * @return 包含成功状态和数据的对象。
     */
    public static Object ok(Object data) {
        Map<String , Object> obj = new HashMap<String, Object>();
        obj.put("errno", 0);
        obj.put("errmsg", "成功");
        obj.put("data", data);
        return obj;
    }

    /**
     * 构建一个携带列表数据的成功响应，自动适配分页信息。
     * <p>
     * 如果传入的列表是 {@link Page} 类型（分页查询结果），则响应格式包含分页元数据：
     * {
     *   "errno": 0,
     *   "errmsg": "成功",
     *   "data": {
     *     "data": [列表数据],
     *     "total": 总记录数,
     *     "page": 当前页码,
     *     "limit": 每页数量,
     *     "pages": 总页数
     *   }
     * }
     * <p>
     * 如果是普通列表，则按单页处理：
     * {
     *   "errno": 0,
     *   "errmsg": "成功",
     *   "data": {
     *     "data": [列表数据],
     *     "total": 列表长度,
     *     "page": 1,
     *     "limit": 列表长度,
     *     "pages": 1
     *   }
     * }
     * </p>
     *
     * @param list 需要返回的列表数据，可以是普通列表或分页对象（如 PageHelper 的 Page）。
     * @return 包含成功状态和分页/非分页数据的对象。
     */
    public static Object okList(List list) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", list);

        if (list instanceof Page) {
            Page page = (Page) list;
            data.put("total", page.getTotal());
            data.put("page", page.getPageNum());
            data.put("limit", page.getPageSize());
            data.put("pages", page.getPages());
        } else {
            data.put("total", list.size());
            data.put("page", 1);
            data.put("limit", list.size());
            data.put("pages", 1);
        }

        return ok(data);
    }

    /**
     * 构建一个通用的失败响应（无详细错误数据）。
     * <p>
     * 响应格式示例：
     * {
     *   "errno": -1,
     *   "errmsg": "错误"
     * }
     * </p>
     *
     * @return 包含失败状态的对象，无具体错误数据。
     */
    public static Object fail() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", -1);
        obj.put("errmsg", "错误");
        return obj;
    }

    /**
     * 构建一个携带自定义错误码和错误信息的失败响应。
     * <p>
     * 响应格式示例：
     * {
     *   "errno": 1001,
     *   "errmsg": "参数校验失败"
     * }
     * </p>
     *
     * @param errno  错误码（建议使用项目内定义的常量）。
     * @param errmsg 错误描述信息（建议国际化）。
     * @return 包含自定义错误码和信息的对象。
     */
    public static Object fail(int errno, String errmsg) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", errno);
        obj.put("errmsg", errmsg);
        return obj;
    }

    /**
     * 构建一个携带错误码、错误信息和附加错误数据的失败响应。
     * <p>
     * 响应格式示例：
     * {
     *   "errno": 1002,
     *   "errmsg": "资源不存在",
     *   "data": "用户ID: 12345"
     * }
     * </p>
     *
     * @param errno  错误码（建议使用项目内定义的常量）。
     * @param errmsg 错误描述信息。
     * @param data   附加的错误数据（可选，如无效的输入值）。
     * @return 包含错误码、信息和附加数据的对象。
     */
    public static Object fail(int errno, String errmsg, String data) {
        Map<String, Object> obj = new HashMap<String, Object>(3);
        obj.put("errno", errno);
        obj.put("errmsg", errmsg);
        obj.put("data", data);
        return obj;
    }

    public static Object badArgument() {
        return fail(401, "参数不对");
    }

    public static Object badArgumentValue() {
        return fail(402, "参数值不对");
    }
    public static Object unlogin() {
        return fail(501, "请登录");
    }

    public static Object serious() {
        return fail(502, "系统内部错误");
    }

    public static Object unsupport() {
        return fail(503, "业务不支持");
    }

    public static Object updatedDateExpired() {
        return fail(504, "更新数据已经失效");
    }

    public static Object updatedDataFailed() {
        return fail(505, "更新数据失败");
    }

    public static Object unauthz() {
        return fail(506, "无操作权限");
    }
}
