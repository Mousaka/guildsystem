package com.eddie.resolver.arguments;

import com.eddie.factory.PlayerFactory;
import com.eddie.model.User;
import com.eddie.model.pojo.Leader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LeaderResolver extends AbstractUserResolver implements HandlerMethodArgumentResolver {

    private PlayerFactory playerFactory;

    @Autowired
    public LeaderResolver(PlayerFactory playerFactory){
        this.playerFactory = playerFactory;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Leader.class);
    }

    @Override
    public Leader resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        User user = this.getUserFromSession(nativeWebRequest);
        return playerFactory.provideLeader(user);
    }
}
