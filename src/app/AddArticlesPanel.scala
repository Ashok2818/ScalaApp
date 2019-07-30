package app

import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.Model
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.ajax.AjaxRequestTarget
import dbLayer.Database
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.ajax.markup.html.AjaxLink
import org.apache.wicket.behavior.AttributeAppender
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter
import java.util.Formatter.DateTime


class AddArticlesPanel(id:String,reference:ArticlesDisplaying,wmc:WebMarkupContainer,model:ModalWindow) extends Panel(id) {
  
  val ArticleId = new TextField("myTextField", Model.of(""));
		ArticleId.setRequired(true);
		val ArticleName = new TextField("name", Model.of(""));
		ArticleName.setRequired(true);
		val ArticleStore = new TextField("store", Model.of(""));
		ArticleStore.setRequired(true);
		val Articlecolor = new TextField("color", Model.of(""));
		Articlecolor.setRequired(true);
		Articlecolor.setOutputMarkupId(true)
		
		
		
		val f=new Form("Adding"){
		}
		f.add(new FeedbackPanel("idFeedback",new ComponentFeedbackMessageFilter(ArticleId)))
	 f.add(new FeedbackPanel("nameFeedback",new ComponentFeedbackMessageFilter(ArticleName)))
		f.add(new FeedbackPanel("storeFeedback",new ComponentFeedbackMessageFilter(ArticleStore)))
		f.add(new FeedbackPanel("colorFeedback",new ComponentFeedbackMessageFilter(Articlecolor)))
		val button= new AjaxButton("search"){
		  
		  override def onSubmit(target : AjaxRequestTarget,form : Form[_]){
		    
		    val id1 = ArticleId.getModelObject.toString();
				val name = ArticleName.getModelObject().toString();
				val store = ArticleStore.getModelObject.toString();
				val color = Articlecolor.getModelObject().toString();

				 val db= new Database
				 val flag=db.isInsert(id1, name, store, color) 
				 if(flag==true){
				   reference.getArticle()
				   target.add(wmc)
				   target.add(reference.ajax)
				   model.close(target)
				 }else{
				   info("fields could not be empty")
				 }
		  }
		}
		/*val AjaxLink2= new AjaxLink("no"){
		   override def onClick(target : AjaxRequestTarget){
		     target.add(reference.ajax)
		     model.close(target)
		   }
		}*/
		
		
		
		f.add(ArticleId);
		f.add(ArticleName);
		f.add(ArticleStore);
		f.add(Articlecolor);
		f.add(button);
	//	f.add(AjaxLink2)
		add(f);
		
}