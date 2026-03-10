package com.curable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.MenuRoleMapping;
import com.curable.service.dto.custom.CustomMenuDTO;

@Repository
public interface MenuRoleMappingRepository
		extends JpaRepository<MenuRoleMapping, Long>, QuerydslPredicateExecutor<MenuRoleMapping> {

	@Query("select new com.curable.service.dto.custom.CustomMenuDTO(c.menu,c.menuOrder,c.url ,group_concat(d.privilage) ) from MenuRoleMapping a left join MenuPrivilegeMapping b on b.id=a.menuPrivilegeMapping.id "
			+ "left join MenuMaster c on c.id=b.menu.id left join PrivilegeMaster d on d.id=b.privilege.id "
			+ "where a.employeeRoleMaster.id= :roleId group by b.menu.id,c.menuOrder,c.url order by c.menuOrder")
	List<CustomMenuDTO> getMenuByRole(Long roleId);

}
