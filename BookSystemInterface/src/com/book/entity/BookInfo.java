package com.book.entity;

import java.io.Serializable;

/**
 * 图书基本信息
 * @author RenYue
 *
 */
public class BookInfo implements Serializable{
	
	private static final long serialVersionUID = 2338203986016420625L;
	private String name;
	private String author;
	private String type;
	private long size;
	private String content;

	/**
	 * 获取图书内容
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置图书内容
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 设置图书名
	 * @return 图书名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 获取图书名
	 * @param name 图书名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取图书作者
	 * @return 作者
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * 设置图书作者
	 * @param author 作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * 获取图书分类
	 * @return 分类
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 设置图书分类
	 * @param type 分类
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取图书大小
	 * @return 大小
	 */
	public long getSize() {
		return size;
	}
	
	/**
	 * 设置图书大小
	 * @param size 大小
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * 判断两个图书的基本信息是否相同
	 */
	@Override
	public boolean equals(Object obj){
		if(obj !=null && obj instanceof BookInfo){
			BookInfo info = (BookInfo)obj;
			if(info.name != null && this.name !=null){
				if(!info.name.equals(this.name)){
					return false;
				}
			}else if(info.name != this.name){
				return false;
			}
			
			if(info.author != null && this.author !=null){
				if(!info.author.equals(this.author)){
					return false;
				}
			}else if(info.author != this.author){
				return false;
			}
			
			if(info.type != null && this.type !=null){
				if(!info.type.equals(this.type)){
					return false;
				}
			}else if(info.type != this.type){
				return false;
			}
			
			if(info.size !=this.size){
				return false;
			}
			return true;			
		}
		return false;
	}

}