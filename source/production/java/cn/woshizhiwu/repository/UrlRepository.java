package cn.woshizhiwu.repository;

import java.util.List;

import cn.woshizhiwu.entity.Url;

public interface UrlRepository {
  public void add(Url url);
  public Url get(long id);
  public List<Url> getAll();	
  public List<Url> getAll(long id);
	public List<Url> getAll(long id, int cat);
	public List<Url> getAll(long id, String tag, int cat);
	public List<Url> getArchive(long id);
	public void delete(long uID, long id);
	public List<Url> getAll(String tag);
	public void delete(long id);
}
