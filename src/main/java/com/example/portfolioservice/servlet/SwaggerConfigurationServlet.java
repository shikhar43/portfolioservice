package com.example.portfolioservice.servlet;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class SwaggerConfigurationServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setBasePath("/portfolio");
        beanConfig.setHost("localhost:8081");
        beanConfig.setTitle("Portfolio Docs");
        beanConfig.setResourcePackage("com.example.portfolioservice");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setVersion("1.0");
//

    }
}
