package app

import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.Page

class MyScalaApp extends WebApplication{
   
 @Override
	def  getHomePage()={
		 classOf[LoginPage] //return default page
	}
}