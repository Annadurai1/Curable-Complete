package com.curable.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "menuprivilegemapping")
public class MenuPrivilegeMapping extends AbstractAuditingEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "menuId")
	private MenuMaster menu;

	@ManyToOne
	@JoinColumn(name = "privilegeId")
	private PrivilegeMaster privilege;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MenuMaster getMenu() {
		return menu;
	}

	public void setMenu(MenuMaster menu) {
		this.menu = menu;
	}

	public PrivilegeMaster getPrivilege() {
		return privilege;
	}

	public void setPrivilege(PrivilegeMaster privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return "MenuPrivilegeMapping [id=" + id + "]";
	}

}
