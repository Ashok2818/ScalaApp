package app

import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.Model

class Header(id:String) extends Panel(id){
  
   add(new Label("metro",Model.of("METRO")))
}