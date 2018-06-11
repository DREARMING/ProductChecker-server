package com.mvcoder.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationLifeCycleListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Application start!!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Application Destroy!!");
    }
}
