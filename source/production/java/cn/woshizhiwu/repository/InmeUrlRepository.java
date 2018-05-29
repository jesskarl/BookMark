package cn.woshizhiwu.repository;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.woshizhiwu.entity.Url;


@Repository
public class InmeUrlRepository implements UrlRepository{
	
	private final Map<Long,Url> database = new Hashtable<Long,Url>();
	private volatile long urlIdSequence = 1L; 
	
	
	public Url get(long id) {
		return this.database.get(id);
	}
	
	public void add(Url url) {
		url.setId(this.getNextUrlId());
		this.database.put(url.getId(), url);
	}
	
	public synchronized long getNextUrlId() {
		return this.urlIdSequence++;
		
	}
	
	public List<Url> getAll(){
		return new ArrayList<Url>(this.database.values());
	}
	
	@Override
	public List<Url> getAll(long id) {
		List<Url> list = new ArrayList<Url>();
		for(long i : this.database.keySet()) {
			Url url = this.database.get(i);
			if(url.getUserId() == id && !url.getArchive()) {
				list.add(url);
			}
		}
		return list;
	}

	@Override
	public List<Url> getAll(long id, int cat) {
		List<Url> list = new ArrayList<Url>();
		for(long i : this.database.keySet()) {
			Url url = this.database.get(i);
			if(url.getUserId() == id && !url.getArchive() && url.getCategory().contains(cat)) {
				list.add(url);
			}
		}
		return list;
	}

	@Override
	public List<Url> getAll(long id, String tag, int cat) {
		List<Url> list = new ArrayList<Url>();
		for(long i : this.database.keySet()) {
			Url url = this.database.get(i);
			if(url.getUserId() == id && !url.getArchive() && 
					url.getCategory().contains(cat) && url.getTags().contains(tag)) {
				list.add(url);
			}
		}
		return list;
	}

	@Override
	public List<Url> getArchive(long id) {
		List<Url> list = new ArrayList<Url>();
		for(long i : this.database.keySet()) {
			Url url = this.database.get(i);
			if(url.getUserId() == id && url.getArchive()) {
				list.add(url);
			}
		}
		return list;
	}

	@Override
	public void delete(long uID,long id) {
		if(this.database.containsKey(id) && uID == this.database.get(id).getUserId()) {
			this.database.remove(id);
		}
	}

	@Override
	public List<Url> getAll(String tag) {
		List<Url> list = new ArrayList<Url>();
		for(long i : this.database.keySet()) {
			Url url = this.database.get(i);
			if(url.getTags().contains(tag)) {
				list.add(url);
			}
		}
		return list;
	}

	@Override
	public void delete(long id) {
		if(this.database.containsKey(id)) {
			this.database.remove(id);
		}
		
	}

}
