package blog.action.reply;

import blog.action.Action;

public class ReplyFactory {
	public static Action getAction(String cmd) {
		switch (cmd) {
		case "list":
		return new ReplyListAction();
		case "delete":
			return new ReplyDeleteAction();
		case "write":
			return new ReplyWriteAction();
		}

		return null;

	}
}