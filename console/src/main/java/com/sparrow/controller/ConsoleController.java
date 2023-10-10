package com.sparrow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 985492783@qq.com
 * @date 2023/10/10 13:21
 */
@RestController
public class ConsoleController {

    @GetMapping("/test")
    public String test() {
        return "hhaha";
    }
}
