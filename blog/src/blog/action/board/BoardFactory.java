package blog.action.board;

import blog.action.Action;

public class BoardFactory {
	public static Action getAction(String cmd) {
		switch (cmd) {
		case "write":
			return new BoardWriteAction();
		case "list":
			return new BoardListAction();
		case "detail":
			return new BoardDetailAction();
		case "delete":
			return new BoardDeleteAction();
		case "updateForm":
			return new BoardUpdateFormAction();
		case "update":
			return new BoardUpdateAction();
		}
		return null;
	}
}
