package org.fsarmiento.invoicing.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.zkoss.spring.context.annotation.EventHandler;
import org.zkoss.spring.util.GenericSpringComposer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericAutowireComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

@org.springframework.stereotype.Component("helloController")
@Scope("desktop")
public class HelloController extends GenericSpringComposer {
	
	private HelloPerson person = new HelloPerson();
	
	@Autowired
	private Textbox name;
	
	@Autowired
	private Button btnHello;
	
	@Autowired
	private Label nameLabel;
	
	@EventHandler("btnHello.onClick")
	public void showGreeting(Event evt) throws WrongValueException, InterruptedException {
        /*Messagebox.show("Hello " + name.getValue() + "!");*/
		person.setName(name.getValue());
	}

	public HelloPerson getPerson() {
		return person;
	}

	public void setPerson(HelloPerson person) {
		this.person = person;
	}	
}
