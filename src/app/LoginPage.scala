package app

import org.apache.wicket.feedback.ComponentFeedbackMessageFilter
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.PasswordTextField
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.model.Model

import app.WelcomePage
import dbLayer.Database



class LoginPage extends WebPage{
  
   
    var username1  = new TextField("user", Model.of(""))
    username1.setRequired(true)
   
		var password1  = new PasswordTextField("pass", Model.of(""))
    password1.setRequired(true)
   // add(new FeedbackPanel("feed"))
     
     
      val fk=new Form("form"){
      override def onSubmit(){
        val username=username1.getModelObject().toString()
        val password=password1.getModelObject().toString()
        
         val  db  = new Database()
         val  flag = db.isLogin(username, password)
         if(flag==true){
              setResponsePage(classOf[WelcomePage])
         }else {
           
           info("sry you have entered wrong username or password")
           
         }
      }
    }
     
   fk.add(new FeedbackPanel("userfeedback",new ComponentFeedbackMessageFilter(username1)))
   fk.add(new FeedbackPanel("passfeedback",new ComponentFeedbackMessageFilter(password1)))   
     val link=new Link("register"){
        
        def onClick(){
          setResponsePage(classOf[Register])
        }
      }
      
      val link1=new Link("forgot"){
        def onClick(){
          
          setResponsePage(classOf[ForgotPassword])
        }
        
      }
             
    fk.add(username1)
    fk.add(password1)
    fk.add(link)
    fk.add(link1)
    add(fk)
     
  
}