package com.springboot.notes.controller;

import com.springboot.notes.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ForwardController {

    /**
     * http://localhost:8080/forward
     * @param request
     * @return
     */
    @GetMapping("/forward")
    public String forwardFunction(HttpServletRequest request){
        request.setAttribute("returnCode", "0");
        request.setAttribute("returnMsg", "success");
        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map success(HttpServletRequest request, @RequestAttribute("returnCode") String returnCode,
                       @RequestAttribute("returnMsg") String returnMsg){
        Map<String,Object> map = new HashMap<>();
        map.put("returnCode",returnCode);
        map.put("returnMsg",returnMsg);
        return map;
    }

    /**
     * 内容协商原理：
     *      1、判断当前响应头中是否已经有确定的媒体类型。MediaType
     *      2、获取客户端（PostMan、浏览器）支持接收的内容类型。（获取客户端Accept请求头字段）：application/xml等
     *      3、遍历循环所有当前系统的 MessageConverter，看谁支持操作这个对象（Person）
     *      4、找到支持操作Person的converter，把converter支持的媒体类型统计出来。
     *      5、客户端与服务端进行媒体类型匹配，确定最佳媒体类型（客户端需要：application/xml。服务端：10种、json、xml）
     *      6、通过最佳匹配媒体类型的converter对返回数据进行转换
     *
     * 自定义内容协商，实现如下功能：
     *      1、浏览器发起请求直接，则返回：Xml    [application/xml]        Jackson2XmlConverter
     *      2、如果是Ajax发起请求，则返回：Json      [application/json]      Jackson2JsonConverter
     *      3、如果指定App发起请求，则返回：自定义协议数据       [application/x-appname]     xxxAppConverter
     * 步骤：
     *      1、添加自定义的MessageConverter到系统中
     *      2、DispatcherServlet底层会统计出所有MessageConverter能操作的类型
     *      3、与客户端进行内容协商
     *
     * 测试:
     *      http://localhost:8080/test/person?format=json   默认
     *      http://localhost:8080/test/person?format=xml    默认
     *      http://localhost:8080/test/person?format=x-guigu   自定义
     * @return
     */
    @ResponseBody
    @GetMapping("/test/person")
    public Person getPerson(){
        Person person = new Person();
        person.setName("李云海");
        person.setAge(18);
        person.setSex("男");
        return person;
    }
}
