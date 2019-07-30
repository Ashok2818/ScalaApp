package app

import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import dbLayer.Database
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.panel.FeedbackPanel


class UpdatePanel(id:String,id1:String,name:String,store:String,color:String,reference:ArticlesDisplaying,mv:ModalWindow,wmc:WebMarkupContainer) extends Panel(id){
  
  var ArticleId = new TextField("id", new CompoundPropertyModel(id1));
  add(new FeedbackPanel("feedback"))
  ArticleId.setRequired(true)
		var ArticleName = new TextField("name", new CompoundPropertyModel(name));
		ArticleName.setRequired(true)
		var ArticleStore = new TextField("store", new CompoundPropertyModel(store));
			ArticleStore.setRequired(true)
		var Articlecolor = new TextField("color", new CompoundPropertyModel(color));
		Articlecolor.setRequired(true)
		
		
		ArticleName.add(new OnChangeAjaxBehavior() {
			@Override
			protected def onUpdate(target : AjaxRequestTarget) {
				
			}
		});
		ArticleStore.add(new OnChangeAjaxBehavior() {
			@Override
			protected def onUpdate(target : AjaxRequestTarget) {
				
			}
		});
		Articlecolor.add(new OnChangeAjaxBehavior() {
			@Override
			protected def onUpdate(target : AjaxRequestTarget) {
			}
		});
  
		val f = new Form("edit") {}
		
		 f.add(  new AjaxButton("add"){
		     
		  override   def onSubmit(target : AjaxRequestTarget,form : Form[_])={
		    
		     val id1 = ArticleId.getDefaultModelObject().toString();
		     val name = ArticleName.getDefaultModelObject().toString();
				val store = ArticleStore.getDefaultModelObject().toString();
				val color = Articlecolor.getDefaultModelObject().toString();
				
				  val db=new Database
				  var flag= db.IsUpdate(id1, name, store, color)
				  if(flag==true){
				     reference.getArticle()
				     target.add(wmc);
					    mv.close(target);
				  }else{//
				    info("something went wrong")
				  }
		     }
		   }
		 )
		  // f.add(ajaxButton)
	
		   
		
		f.add(ArticleId);
		f.add(ArticleName);
		f.add(ArticleStore);
		f.add(Articlecolor);
		add(f);
}