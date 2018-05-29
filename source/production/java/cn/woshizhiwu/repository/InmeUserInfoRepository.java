package cn.woshizhiwu.repository;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.woshizhiwu.entity.User;

@Repository
public class InmeUserInfoRepository implements UserInfoRepository{
	
	private final static Map<Long,User> users = new Hashtable<>();
	private final static Map<String,Long> emails = new Hashtable<>();
	private volatile long userIdSequence = 2L; 
	
	static {
		User test = new User();
		test.setId(1L);
		test.setEmail("test@1.com");
		test.setPassword("11111Aa`");
		test.setUsername("test1");
		users.put(1L,test);
		emails.put("test@1.com", 1L);
	}

	@Override
	public long getId(String email) {
		if(emails.get(email) != null) {
			return emails.get(email);
		}
		else {
			return 0L;
		}
	}
	
	public User getUser(long id) {
		return users.get(id);
	}

	@Override
	public boolean add(User user) {
		if(emails.get(user.getEmail()) != null) {
			return false;
		}
		user.setId(this.getNextUserId());
		emails.put(user.getEmail(), user.getId());
		users.put(user.getId(), user);
		return true;
	}

	private synchronized long getNextUserId() {
		return this.userIdSequence++;
	}

	@Override
	public List<User> getAllUser() {
		return new ArrayList<User>(this.users.values());
	}


}
