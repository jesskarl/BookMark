package cn.woshizhiwu.entity;

public class Anonymous extends User{
	
	private String username = "anonymous";
	
	public Anonymous() {
	}
	
	@Override
	public String getUsername() {
		return this.username;
	}

}
