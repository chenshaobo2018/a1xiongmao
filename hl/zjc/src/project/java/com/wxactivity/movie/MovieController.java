/**
 * 
 */
package com.wxactivity.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/wxact")
public class MovieController {

	@RequestMapping(value = "/activity/v1/initMovie")
	public String initMovie(HttpServletRequest request, HttpServletResponse response){
		return "project/movie/movie.jsp";
	}
	
}
