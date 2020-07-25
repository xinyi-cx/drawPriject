package com.tt.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author：zzs
 * @Description：
 * @Date：Created in 14:09 2020/3/31
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApiOfScys() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("抽奖系统")
                .apiInfo(apiInfo("抽奖系统系统接口文档", "抽奖系统系统接口文档", "1.0"))
                .select()
                //调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                //控制暴露出去的路径下的实例
                //如果某个接口不想暴露,可以使用以下注解
                //@ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
                .apis(RequestHandlerSelectors.basePackage("com.tt.manage.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo(String title, String desc, String version) {
        return new ApiInfoBuilder()
                .title(title)//标题
                .description(desc)//描述
//                .termsOfServiceUrl("http://blog.csdn.net/saytime")//（不可见）条款地址，公司内部使用的话不需要配
                .contact(new Contact("name", "", "email@126.com"))//作者信息
                .version(version)
                .build();
    }

}
