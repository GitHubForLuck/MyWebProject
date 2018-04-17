package com.ljm;

import com.ljm.config.Wisely2Settings;
import com.ljm.config.WiselySettings;
import com.ljm.servlet.MyServlet1;
import com.ljm.utils.SpringUtil2;
import com.ljm.utils.SpringUtil3;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import javax.servlet.MultipartConfigElement;

/**
 * @Project MyWebProject
 * @ClassName App
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/2 15:28
 * @Version 1.0
 **/
// 其中@SpringBootApplication申明让spring boot自动给程序进行必要的配置，等价于以默认属性使用@Configuration，@EnableAutoConfiguration和@ComponentScan
@SpringBootApplication
@ServletComponentScan   // 扫描相应的servlet（带注解@WebServlet的servlet）包
@ComponentScan(basePackages = {"com.ljm", "cn.ljm", "org.ljm"})     // 改变自动扫描的包
@Import(value={SpringUtil3.class})
@EnableConfigurationProperties({WiselySettings.class, Wisely2Settings.class})
public class App extends SpringBootServletInitializer {

    /**注册Spring Util
     * 这里为了和上一个冲突，所以方面名为：springUtil2
     * 实际中使用springUtil
     */
    @Bean
    public SpringUtil2 springUtil2(){return new SpringUtil2();}

    /**
     * 注册Servlet.不需要添加注解：@ServletComponentScan
     * @return
     */
    @Bean
    public ServletRegistrationBean MyServlet1(){
        return new ServletRegistrationBean(new MyServlet1(),"/myServlet1/*");
    }

    /**
     *
     参数里VM参数设置为：
     -javaagent:.\lib\springloaded-1.2.4.RELEASE.jar-noverify   idea????
     * @param args
     */
    public static void main(String[] args) {
//        SpringApplication.run(App.class, args);
        SpringApplication application = new SpringApplication(App.class);
               /*
                *  启动标志
                * Banner.Mode.OFF:关闭;
                * Banner.Mode.CONSOLE:控制台输出，默认方式;
                * Banner.Mode.LOG:日志输出方式;
                */
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
        factory.setMaxFileSize("128KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("256KB");
        //Sets the directory location wherefiles will be stored.
        //factory.setLocation("路径地址");
        return factory.createMultipartConfig();
    }
}
