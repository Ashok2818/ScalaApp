package app

import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.repeater.data.DataView
import org.apache.wicket.markup.repeater.data.ListDataProvider
import java.util.ArrayList
import pojoClasses.ArticlesDisplaybean
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.WebMarkupContainer
import dbLayer.Database
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions._
import org.apache.wicket.ajax.markup.html.AjaxLink
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.markup.html.panel.FeedbackPanel

class ArticlesDisplaying(id: String) extends Panel(id) {

  val reference: ArticlesDisplaying = this
  var listView = ListBuffer[ArticlesDisplaybean]()
  val wmc = new WebMarkupContainer("wmc")
  wmc.setOutputMarkupId(true)

  val mv = new ModalWindow("window")
  val mw = new ModalWindow("remove")
  val model = new ModalWindow("window3")
   model.setInitialWidth(800);
          model.setInitialHeight(500);
          model.setTitle("Adding Articles");
         

  val f = new Form("form")
  getArticle();

  val Data = new DataView[ArticlesDisplaybean]("body", new ListDataProvider(bufferAsJavaList(listView))) {
    def populateItem(item: Item[ArticlesDisplaybean]) = {
      val articles = item.getModelObject().asInstanceOf[ArticlesDisplaybean]

      item.add(new Label("id", articles.article_id))
      item.add(new Label("name", articles.article_name))
      item.add(new Label("store", articles.Store_name))
      item.add(new Label("color", articles.article_color))
      
      item.add(new AjaxLink("update") {
        def onClick(target: AjaxRequestTarget) = {
          val update = new UpdatePanel(mv.getContentId, articles.article_id, articles.article_name, articles.Store_name, articles.article_color,
            reference, mv, wmc)

          mv.setInitialWidth(800);
          mv.setInitialHeight(500);
          mv.setTitle("Update Articles");
          mv.setContent(update);
          mv.show(target);
        }
      })

      item.add(new AjaxLink("delete") {
        def onClick(target: AjaxRequestTarget) = {
          val remove = new DeletePanel(mw.getContentId, articles.article_id, reference, mw, wmc)
          mw.setInitialHeight(180);
          mw.setInitialWidth(250);
          mw.setTitle("Deleting Article");
          mw.setContent(remove);
          mw.show(target)
        }

      })

     
    }

  }
   val ajax =new AjaxLink("red") {
       def onClick(target: AjaxRequestTarget) {
          val AddArticlePanel = new AddArticlesPanel(model.getContentId, reference, wmc, model)
          model.setContent(AddArticlePanel);
          model.show(target);
        }
      }
  wmc.add(ajax)
  wmc.add(Data)
  wmc.add(mv)
  wmc.add(mw)
  wmc.add(model)
  f.add(wmc)
  add(f)

  def getArticle() = {
    listView.clear()
    val db = new Database()
    listView.++=(db.getArticles())
  }

}