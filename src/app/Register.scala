package app

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.Model
import org.apache.wicket.markup.html.form.Form
import dbLayer.Database
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter
import org.apache.wicket.markup.html.panel.Panel
import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern
import org.apache.wicket.validation.validator.PatternValidator

class Register extends WebPage {

  var Name = new TextField("F_name", Model.of(""))
  Name.setRequired(true)

  var Store = new TextField("store", Model.of(""))
  Store.setRequired(true)

  var Country = new TextField("country", Model.of(""))
  Country.setRequired(true)
 
  var City = new TextField("city", Model.of(""))
  City.setRequired(true)

  var Area = new TextField("area", Model.of(""))
  Area.setRequired(true)

  var Email = new TextField("email", Model.of(""))
  Email.setRequired(true)

  var userName = new TextField("User_name", Model.of(""))
  userName.setRequired(true)
 
  var userPassword = new TextField("User_password", Model.of(""))
  userPassword.setRequired(true)
  
  var id = new TextField("emp_id", Model.of(""))
  id.setRequired(true)
  id.setOutputMarkupPlaceholderTag(true)
  
 
  val form = new Form("register") {
    
			override def onSubmit() {
			  
      val name = Name.getModelObject.toString
      val store = Store.getModelObject.toString
      val country = Country.getModelObject.toString
      val city = City.getModelObject.toString
      val area = Area.getModelObject.toString
      val email = Email.getModelObject.toString
      val username = userName.getModelObject.toString
      val userpassword = userPassword.getModelObject.toString
      val emp_id = id.getModelObject.toString

      val db = new Database()
      val flag = db.isRegister(name, store, country, city, area, email, username, userpassword, emp_id)
      if (flag == true) {
        info(" you are succesfully registered")
        setResponsePage(classOf[LoginPage])
      }
    }
  }
  
  form.add(new FeedbackPanel("NameFeedbackerror", new ComponentFeedbackMessageFilter(Name)))
  form.add(new FeedbackPanel("storeFeedbackerror", new ComponentFeedbackMessageFilter(Store)))
  form.add(new FeedbackPanel("countryFeedbackerror", new ComponentFeedbackMessageFilter(Country)))
  form.add(new FeedbackPanel("cityFeedbackerror", new ComponentFeedbackMessageFilter(City)))
  form.add(new FeedbackPanel("AreaFeedbackerror", new ComponentFeedbackMessageFilter(Area)))
  form.add(new FeedbackPanel("emailFeedbackerror", new ComponentFeedbackMessageFilter(Email)))
  form.add(new FeedbackPanel("userFeedbackerror", new ComponentFeedbackMessageFilter(userName)))
  form.add(new FeedbackPanel("passwordFeedbackerror", new ComponentFeedbackMessageFilter(userPassword)))
  form.add(new FeedbackPanel("idFeedbackerror", new ComponentFeedbackMessageFilter(id)))
  form.add(new Label("result", "Note : please fill below registeration form"))
  form.add(Name);
  form.add(Store);
  form.add(Country);
  form.add(City);
  form.add(Area);
  form.add(Email);
  form.add(userName);
  form.add(userPassword);
  form.add(id);
  form.setOutputMarkupId(true)
  form.setOutputMarkupPlaceholderTag(true)
  add(form)


}