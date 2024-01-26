package br.com.springboot.course_springboot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * A sample greetings controller to return greeting text
 */
@Controller
@CrossOrigin(origins = "*")
public class GreetingsController {
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/pagina", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String paginaInicial() {
    	return "index";
    }
}
