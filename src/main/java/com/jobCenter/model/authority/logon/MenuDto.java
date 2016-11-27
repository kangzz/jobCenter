package com.jobCenter.model.authority.logon;

import java.util.List;
/**
 * 描述：菜单
 * 作者 ：kangzz
 * 日期 ：2016-11-26 17:49:55
 */
public class MenuDto {

	private Integer id;
	/**
	 * 菜单名称 
	 */
	private String name;
	/**
	 * 菜单链接地址
	 */
	private String href ;
	
	private List<MenuDto> children;

	private MenuDto(){
		
	}

	public MenuDto(Integer id, String name, String href, List<MenuDto> children) {
		this.id = id;
		this.name = name;
		this.href = href;
		this.children = children;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getHref() {
		return href;
	}


	public void setHref(String href) {
		this.href = href;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<MenuDto> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDto> children) {
		this.children = children;
	}

}
