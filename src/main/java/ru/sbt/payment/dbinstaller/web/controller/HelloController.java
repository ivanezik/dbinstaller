package ru.sbt.payment.dbinstaller.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class HelloController {


	@Resource(mappedName="jdbc/DSTest")
	DataSource dataSource;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";

	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);
		try {
			Statement st = dataSource.getConnection().createStatement();
			st.execute( "create table t1 (f1 integer )" );
			if ( st != null )
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return model;

	}

}