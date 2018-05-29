package cn.woshizhiwu.repository;

import java.util.List;

import cn.woshizhiwu.entity.Url;
import cn.woshizhiwu.entity.User;

public interface UserInfoRepository {
	
	public long getId(String email);
	public User getUser(long id);
	public boolean add(User user);
	public List<User> getAllUser();
	
}
