package com.douzone.mysite.action.board;



import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch(actionName) {
			case "writeform" : return new BoardWriteFormAction();	
			case "modifyform" : return new BoardModifyFormAction(); 
			case "viewform" : return new BoardViewFormAction(); 
			case "delete" : return new BoardDeleteAction();
			case "write" : return new BoardInsertAction();
			case "update" : return new BoardUpdateAction();
			case "search" : return new BoardSearchAction();
			
		
			
			default : return new BoardListAction();
		}
	}

}
