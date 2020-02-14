package com.douzone.mysite.action.guestbook;



import com.douzone.mysite.action.main.MainAction;
import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class GuestBookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		
		switch(actionName) {
		case "deleteform" : return new GuestBookADeleteFormAction();	
		case "insert" : return new GuestBookInsertAction(); 
		case "delete" : return new GuestBookDeleteAction(); 
		case "list" : return new GuestBookListAction();

		default : return new MainAction();
	}
		
	}

}
