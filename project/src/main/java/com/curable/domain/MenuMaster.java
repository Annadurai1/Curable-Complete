package com.curable.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "menumaster")
public class MenuMaster extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "menu")
	private String menu;

	@ManyToOne
	@JoinColumn(name = "parentMenu")
	private MenuMaster parentMenu;

	@Column(name = "menuModule")
	private String menuModule;

	@Column(name = "url")
	private String url;

	@Column(name = "menuOrder")
	private Integer menuOrder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public MenuMaster getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(MenuMaster parentMenu) {
		this.parentMenu = parentMenu;
	}

	public String getMenuModule() {
		return menuModule;
	}

	public void setMenuModule(String menuModule) {
		this.menuModule = menuModule;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	@Override
	public String toString() {
		return "MenuMaster [id=" + id + ", menu=" + menu + ", menuModule=" + menuModule + ", url=" + url
				+ ", menuOrder=" + menuOrder + "]";
	}
	
	
}
