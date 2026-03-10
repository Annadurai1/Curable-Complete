package com.curable.domain;

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
@Table(name = "menurolemaster")
public class MenuRoleMapping extends AbstractAuditingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "menuId")
	private MenuMaster menuMaster;

	@ManyToOne
	@JoinColumn(name = "roleId")
	private EmployeeRoleMaster employeeRoleMaster;

	@ManyToOne
	@JoinColumn(name = "menuPrivilegeId")
	private MenuPrivilegeMapping menuPrivilegeMapping;

	public MenuPrivilegeMapping getMenuPrivilegeMapping() {
		return menuPrivilegeMapping;
	}

	public void setMenuPrivilegeMapping(MenuPrivilegeMapping menuPrivilegeMapping) {
		this.menuPrivilegeMapping = menuPrivilegeMapping;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MenuMaster getMenuMaster() {
		return menuMaster;
	}

	public void setMenuMaster(MenuMaster menuMaster) {
		this.menuMaster = menuMaster;
	}

	public EmployeeRoleMaster getEmployeeRoleMaster() {
		return employeeRoleMaster;
	}

	public void setEmployeeRoleMaster(EmployeeRoleMaster employeeRoleMaster) {
		this.employeeRoleMaster = employeeRoleMaster;
	}

	@Override
	public String toString() {
		return "MenuRoleMapping [id=" + id + "]";
	}

}
