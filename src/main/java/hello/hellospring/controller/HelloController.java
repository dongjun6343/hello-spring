package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // "hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        // getter/setter - 자바 빈.
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}


/**
 * @ResponseBody
 * 내장 톰캣 -> helloController -> HttpMessageConvert 동작. (JsonConverter, StringConverter)
 *
 * 객체가 오면 JsonConverter가 디폴트로 json형식으로 만들어서 웹 브라우저한테 응답을 준다.
 *
 * Spring은 Jackson을 기본으로 씀.
 *
 */