

package app

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.Model
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter
import org.apache.wicket.validation.validator.StringValidator
import dbLayer.Database
import javax.mail.Session
import javax.mail.internet.MimeMessage
import javax.mail.internet.InternetAddress
import javax.mail.Transport
import javax.mail.Message
import javax.mail.PasswordAuthentication


class ForgotPassword extends WebPage {
  
  var mail=""
 var Email= new TextField("mail",Model.of(""))
 Email.setRequired(true)



  
    var form =new Form("form"){
    
    override def onSubmit(){
      
     val mail = Email.getModelObject.toString()
     var db=new Database
     var email=db.isEmailFound(mail)
     if(email == true){
       sendingOtp()
       info("succesfully sent")
     }
     else{
       info("the mail you entered is not registered, please enter register mail")
     }
    }
    
  }
 
 form.add(Email)
 form.add(new Label("display","enter your email which is used in login potal"))
 form.add(new FeedbackPanel("feedbackerror", new ComponentFeedbackMessageFilter(Email)))
 form.setOutputMarkupId(true)
 form.setOutputMarkupPlaceholderTag(true)
 this add(form)
 
 def sendingOtp(){
   val to=mail
    val from="ashok.reddy3919@gmail.com"
    val password ="9959147480"
    val host="localhost"
     var value= 391934
    
     val props=System.getProperties()
    props.setProperty("mail.transport.protocol", "smtp");
          props.setProperty("mail.host", "smtp.gmail.com");
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.port", "465");
          props.put("mail.smtp.socketFactory.port", "465");
          props.put("mail.smtp.socketFactory.class",
          "javax.net.ssl.SSLSocketFactory");
          props.put("mail.smtp.socketFactory.fallback", "false");
          props.setProperty("mail.smtp.quitwait", "false");
          
           val session=Session.getDefaultInstance(props,
    new javax.mail.Authenticator(){
            
     override  def  getPasswordAuthentication():PasswordAuthentication = {
             new PasswordAuthentication( from, password)// Specify the Username and the PassWord
        }
             
})
      val message=new MimeMessage(session)
      message.setFrom(new InternetAddress(from))
       message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
       message.setSubject("otp");
       message.setText(" the one time password for techwave authencation is :" +value);  
        Transport.send(message);  
         
 }
  
}