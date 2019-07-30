package app

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label

class WelcomePage extends WebPage {
  
  val header = new Header("metro")
  val articles= new ArticlesDisplaying("articles")
  add(header)
  add(articles)
}