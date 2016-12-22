package old;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Response {
	private boolean success;
	private int msgcode;
	private String msg;
	private List<User>users = new ArrayList<User>();
	public Response(){};
	public Response(boolean success, int errcode, String msg, List<User> users) {
		super();
		this.success = success;
		this.msgcode = errcode;
		this.msg = msg;
		this.users = users;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getMsgcode() {
		return msgcode;
	}
	public void setMsgcode(int errcode) {
		this.msgcode = errcode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
