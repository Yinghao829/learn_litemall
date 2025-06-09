package org.hao.litemall.wx.annotation.support;

import org.apache.http.client.UserTokenHandler;
import org.hao.litemall.wx.annotation.LoginUser;
import org.hao.litemall.wx.service.UserTokenManager;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 自定义Spring MVC方法参数解析器，用于自动解析带由@LoginUser注解的Integer类型参数（用户ID）
 * 从请求头中提取token，并通过Token管理器获取用户ID
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    // 定义请求头中携带 Token 的键名
    public static final String LOGIN_TOKEN_KEY = "X-Litemall-Token";

    /**
     * 判断当前参数是否支持解析
     * 仅支持满足以下条件的参数：
     * 1. 参数类型为Integer或其子类
     * 2. 参数带有@LoginUser注解
     *
     * @param parameter 参数信息
     * @return 如果支持解析返回true，否则返回false
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class) && parameter.hasMethodAnnotation(LoginUser.class);
    }

    /**
     * 解析参数的具体实现逻辑
     * 1. 从请求头中获取Token
     * 2. 通过Token管理器验证Token并返回用户ID
     * 3. 如果Token无效或不存在，返回null
     *
     * @param parameter 参数信息
     * @param mavContainer ModelAndersonView容器
     * @param webRequest 当前请求对象
     * @param binderFactory 数据绑定工厂
     * @return 解析后参数值（用户ID或null）
     * @throws Exception 异常
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = webRequest.getHeader(LOGIN_TOKEN_KEY);
        if (token == null || token.isEmpty()) {
            return null;
        }
        return UserTokenManager.getUserId(token);
    }
}
