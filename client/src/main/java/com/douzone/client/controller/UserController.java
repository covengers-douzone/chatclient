package com.douzone.client.controller;

import com.douzone.client.model.User;
import com.douzone.client.repository.UserRepository;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.apache.maven.model.Model;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Controller
@RequestMapping("/user")
public class UserController {
    private static String CODENUMBER = "-9999";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ResponseBody
    @PostMapping(value = "/sendSms")
    public String sendSms(HttpServletRequest request) throws Exception {
        String api_key = "NCS24FKUXYQBKKP5";
        String api_secret = "OPTNWRSSEW1D8ANZMB2DN6QICTIF9BMG";

        Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", (String)request.getParameter("to"));
        params.put("from", "01025271985");
        params.put("type", "SMS");
        params.put("text", (String)request.getParameter("text"));
        params.put("app_version", "test app 1.2"); // application name and version

        System.out.println(params);
        try {
            CODENUMBER = (String)request.getParameter("text");
            System.out.println("인증번호는 " + CODENUMBER +" 입니다.");
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());

        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
        return "suc";
    }
    @GetMapping(value = "/join")
    public String join() {
        return "user/join";
    }

    @PostMapping(value = "/join")
    public String join(User user, BindingResult result, Model model, HttpServletRequest request){
        System.out.println(user);

        /* 문자인증 기능 잠시 정지.
        if(!(CODENUMBER.equals((String) request.getParameter("text")))){
            System.out.println(CODENUMBER);
            System.out.println(request.getParameter("text"));
            System.out.println("실패");
            return "user/join";
        }
        */


        //password를 인코딩하는 과정.
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);

//        if(result.hasErrors()){
//            System.out.println("in");
//            List<ObjectError> list = result.getAllErrors();
//            for(ObjectError error : list) {
//                System.out.println(error);
//            }
//            //odel.addAllAttributes(result.getModel());
//            return "user/join";
//        }
        return "redirect:/";
    }
}
