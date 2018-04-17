package com.ljm.execption;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Project MyWebProject
 * @ClassName GlobalDefaultExceptionHandler
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/2 15:50
 * @Version 1.0
 **/
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value =Exception.class)
    public void defaultErrorHandler(HttpServletRequest req, Exception e) {
//      // If the exception is annotated with@ResponseStatusrethrow it and let
//      // the framework handle it - like theOrderNotFoundException example
//      // at the start of this post.
//      // AnnotationUtils is a Spring Frameworkutility class.
//      if (AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class) != null)
//          throw e;
//
//      // Otherwise setup and send the user to adefault error-view.
//      ModelAndView mav = newModelAndView();
//      mav.addObject("exception", e);
//      mav.addObject("url",req.getRequestURL());
//      mav.setViewName(DEFAULT_ERROR_VIEW);
//      return mav;

        //打印异常信息：
        e.printStackTrace();
        System.out.println("GlobalDefaultExceptionHandler.defaultErrorHandler()");

        /*
         * 返回json数据或者String数据：
         * 那么需要在方法上加上注解：@ResponseBody
         * 添加return即可。
         */

        /*
         * 返回视图：
         * 定义一个ModelAndView即可，
         * 然后return;
         * 定义视图文件(比如：error.html,error.ftl,error.jsp);
         *
         */
    }

}
