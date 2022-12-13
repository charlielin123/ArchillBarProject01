package com.AchillBar.base.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
/* GlobalExceptionHandler.java类中的@ControllerAdvice注解只处理经过
 * Controller的不经过Controller的不进行处理由此可得出404的错误
 * @ControllerAdvice是不进行处理的。
*/
@ControllerAdvice
public class GlobalController {
    
    
    @ExceptionHandler(value = Exception.class)
    public ModelAndView errorCatch(Exception e,WebRequest request)throws Exception {
        System.out.println("全局異常處理500");
        ModelMap mmp = new ModelMap();
        mmp.addAttribute("ex", e);
        return new ModelAndView("errors.jsp",mmp);
    }
    
/*
 * @Controller+@ExceptionHandler、
 * HandlerExceptionResolver接口形式、
 * @ControllerAdvice+@ExceptionHandler优缺点说明：

在Spring4.3.0版本下，
1.优先级来说，@Controller+@ExceptionHandler优先级最高，
其次是@ControllerAdvice+@ExceptionHandler，最后才是HandlerExceptionResolver，
说明假设三种方式并存的情况 优先级越高的越先选择，而且被一个捕获处理了就不去执行其他的.

2. 三种方式都支持多种返回类型，@Controller+@ExceptionHandler、
@ControllerAdvice+@ExceptionHandler可以使用Spring支持的
@ResponseBody、ResponseEntity，而HandlerExceptionResolver方法声明返回值类型只能是 
ModelAndView，如果需要返回JSON、xml等需要自己实现.

3.缓存利用，@Controller+@ExceptionHandler的缓存信息在
ExceptionHandlerExceptionResolver的exceptionHandlerCache，
@ControllerAdvice+@ExceptionHandler的缓存信息在
ExceptionHandlerExceptionResolver的exceptionHandlerAdviceCache中, 
而HandlerExceptionResolver接口是不做缓存的，
在前面两种方式都fail的情况下才会走自己的HandlerExceptionResolver实现类，
多少有点性能损耗.
 */
}


