package cn.woshizhiwu.entity;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter;

public class Url {
	
	private long id;
	private long userId;
	private URL url;
	private String title;
	private List<Integer> category;
	private boolean archive = false;
	private List<String> tags = new ArrayList<String>();
	
	public Url(){
		category = new ArrayList<Integer>();
		this.category.add(0);
	}
	
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getUserId() {
		return this.userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public URL getUrl() {
		return this.url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public boolean getArchive() {
		return this.archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	
	public List<String> getTags() {
		return this.tags;
	}
	public void addTag(String tag) {
		this.tags.add(tag);
	}
	
	public List<Integer> getCategory(){
		return this.category;
	}
	
	public boolean getFavorite() {
		return this.category.contains(1);
	}
	
	public boolean getVentilate() {
		return this.category.contains(2);
	}
}
