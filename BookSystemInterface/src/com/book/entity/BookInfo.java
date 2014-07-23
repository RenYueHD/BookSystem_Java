package com.book.entity;

import java.io.Serializable;

/**
 * ͼ�������Ϣ
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
	 * ��ȡͼ������
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * ����ͼ������
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * ����ͼ����
	 * @return ͼ����
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * ��ȡͼ����
	 * @param name ͼ����
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * ��ȡͼ������
	 * @return ����
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * ����ͼ������
	 * @param author ����
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * ��ȡͼ�����
	 * @return ����
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * ����ͼ�����
	 * @param type ����
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * ��ȡͼ���С
	 * @return ��С
	 */
	public long getSize() {
		return size;
	}
	
	/**
	 * ����ͼ���С
	 * @param size ��С
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * �ж�����ͼ��Ļ�����Ϣ�Ƿ���ͬ
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