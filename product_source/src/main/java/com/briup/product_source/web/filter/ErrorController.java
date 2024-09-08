package com.briup.product_source.web.filter;

import com.briup.product_source.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorController {

    @GetMapping("/filterError")
    public Result handleError(HttpServletRequest request) throws Exception{
        Exception ex = (Exception) request.getAttribute("exception");
        throw ex;
    }
}
