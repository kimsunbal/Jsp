package blog.action.comment;

import blog.action.Action;

public class CommentFactory {
	public static Action getAction(String cmd) {
		switch (cmd) {
		case "delete":
			return new CommentDeleteAction();
		case "write":
			return new CommentWriteAction();
		}

		return null;

	}
}