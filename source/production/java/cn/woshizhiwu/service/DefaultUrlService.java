package cn.woshizhiwu.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cn.woshizhiwu.entity.Url;
import cn.woshizhiwu.repository.UrlRepository;


@Service
public class DefaultUrlService implements UrlService {
	
	@Inject UrlRepository urlRepository;
	
	public List<Url> getAllUrl(){
		List<Url> list = this.urlRepository.getAll();
		return list;
	}
	
	public Url getUrl(long id) {
		return this.urlRepository.get(id);
	}

	@Override
	public List<Url> getUserUrl(long id, int cat) {
		List<Url> list;
		if(cat == 0) {
			list = this.urlRepository.getAll(id);
		}
		else if(cat == 3) {
			list = this.urlRepository.getArchive(id);
		}
		else {
			list = this.urlRepository.getAll(id,cat);
		}
			return list;
	}

	@Override
	public List<Url> getUserTagUrl(long id, String tag, int cat) {
		List<Url> list = this.urlRepository.getAll(id,tag,cat);
		return list;
	}

	@Override
	public void delete(long uID,long id) {
		this.urlRepository.delete(uID,id);
	}

	@Override
	public void saveUrl(Url url, String tag) {
		if(url.getTitle() == null || url.getTitle().length() == 0) {
			url.setTitle(url.getUrl().getHost());
		}
		this.urlRepository.add(url);
	}


}
