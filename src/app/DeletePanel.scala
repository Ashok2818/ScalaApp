package app

import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.AjaxLink
import dbLayer.Database
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.basic.Label

class DeletePanel(id:String,id1:String,refernce:ArticlesDisplaying,mw:ModalWindow,wmc:WebMarkupContainer)  extends Panel(id){
  
   val AjaxLink =new AjaxLink("yes") {
			
			 def onClick(target : AjaxRequestTarget)={
			   
			   if(id1==null){
			     
			     val db=new Database;
			     val ststus=db.isRemove(id1)
			     refernce.getArticle();
					target.add(wmc);
					mw.close(target);
			   }
			   else{
			     
				val db=new Database;
				val status=db.isDelete(id1);
				
				if (status==true) {
					refernce.getArticle();
					target.add(wmc);
					mw.close(target);
				}
			   }
			}
		};
		
		val AjaxLink1=new AjaxLink("no") {
			 def onClick(target : AjaxRequestTarget) {
				mw.close(target);
				
			}
		};
		add(new Label("conform","do you want delete the article"));
		add(AjaxLink);
		add(AjaxLink1);
}