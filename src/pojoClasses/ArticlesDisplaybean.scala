
package pojoClasses


import java.io.Serializable;

case class ArticlesDisplaybean( article_id : String, article_name : String, Store_name : String,  article_color : String ) extends Serializable{
  
  
  var articleId :String =_
  var articleName :String = _
  var articleStore :String = _
  var articleColor :String = _
  
 
  
   def getArticleId() : String = {
    articleId;
  }
	 def SetArticleId(articleId:String)= {
		this.articleId = articleId;
	}
	 
	 def getArticleName() : String = {
    articleName;
  }
	 def setArticleName(articleName:String)= {
		this.articleName = articleName;
	}
  
	 def getArticleStore() : String = {
    articleStore;
  }
	 def setArticleStore(articleStore:String)= {
		this.articleStore = articleStore;
  }
	 
	  def getArticleColor() : String = {
    articleColor;
  }
	 def setArticleColor(articleColor:String)= {
		this.articleColor = articleColor;
  }
	 
}