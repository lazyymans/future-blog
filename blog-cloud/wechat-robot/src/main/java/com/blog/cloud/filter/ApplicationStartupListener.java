package com.blog.cloud.filter;

import com.blog.cloud.config.WechatApiServiceInternal;
import com.blog.cloud.service.LoginService;
import com.blog.cloud.utils.QRCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 在容器启动完成后 执行
 *
 * @author luoyc
 * @create 2019/01/19 17:01
 */
@Slf4j
@Component
public class ApplicationStartupListener implements BeanFactoryAware, ApplicationListener<ContextRefreshedEvent> {

    private DefaultListableBeanFactory beanFactory;

    @Autowired
    private LoginService loginService;



    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //启动微信机器人
        loginService.login();
    }
}