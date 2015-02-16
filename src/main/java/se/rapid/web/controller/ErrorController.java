package se.rapid.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController
{
	
	 @RequestMapping(value = "/shared/accessDenied", method = RequestMethod.GET)
		public String accessDenied()
		{

			return "/shared/403";
		}
	 @RequestMapping(value = "/shared/default", method = RequestMethod.GET)
	 public String defaultErrorHandler()
	 {
		 
		 return "/shared/default";
	 }

		@RequestMapping(value = "/shared/pageNotFound", method = RequestMethod.GET)
		public String AdminPageNotFound()
		{

			return "/shared/404";
		}

		@RequestMapping(value = "/shared/unAuthorized", method = RequestMethod.GET)
		public String unAuthorized()
		{

			return "/shared/402";
		}

		@RequestMapping(value = "/shared/serverError", method = RequestMethod.GET)
		public String serverError()
		{

			return "/shared/500";
		}

		@RequestMapping(value = "/shared/badRequest", method = RequestMethod.GET)
		public String AdminBadRequest(ModelMap model)
		{
			return "/shared/400";
		}      

}