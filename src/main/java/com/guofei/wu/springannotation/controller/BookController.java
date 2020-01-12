package com.guofei.wu.springannotation.controller;

import com.guofei.wu.springannotation.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 20:25
 * @since v3.0
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
}
