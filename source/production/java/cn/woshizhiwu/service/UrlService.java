package cn.woshizhiwu.service;

import java.util.Collection;
import java.util.List;

import cn.woshizhiwu.entity.Url;

public interface UrlService {
	public List<Url> getAllUrl();
	public Url getUrl(long id);
	public void saveUrl(Url url, String string);
	public List<Url>  getUserUrl(long id, int cat);
	public List<Url>  getUserTagUrl(long id, String tag, int cat);
	public void delete(long uID, long id);

}
