#!/bin/sh


SCHEMA_FILE=${1:-/tmp/schema.txt}
NAWK=/usr/bin/awk
PROJECT_NAME=com.curable
PROJECT_TITLE="Curable Disease App"

if [ ! -f ${SCHEMA_FILE} ]; then
	printf "${SCHEMA_FILE} does not exist\n"
	exit -1;
fi
######################################################################################################
# There were some unnecessary classes when I have already .java classes.
# No time to debug. Hence removing java files before running.

rm -f *.java

${NAWK} -v QUOTE="'" -v logname=${LOGNAME} -v version=1.0 -v since=1.0 -v proj_name=${PROJECT_NAME} 'BEGIN { 
  table_name = ""; i = 0; k = 0;
  upper="ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
  lower="abcdefghijklmnopqrstuvwxyz"}

  # Function to find out if List needs to be imported in class
  # Argument file_name
  function import_list_if_required(il_filename) {
        for ( j = 1; j <= i; ++j )  {
                n=index(keys[j],".");
                if ( n != 0 ) {
                        printf("import java.util.List;\n")>>il_filename;
                        break;
                }
        }
  }

  # Function to return name of function
  # Argument index of references array

  function get_mangled_filenames(mindex) {
	for ( jdx = 1; jdx <= i; ++jdx )  {
		n=index(keys[jdx],".");
		if ( n != 0 && jdx == mindex ) {
			repRefField=keys[jdx];
			gsub(/\./,"_",repRefField);
			return "findBy" repRefField "AndIsRecordDeletedFalse";
		}
	}
  }

  function write_mapper_file() {
	entity_mapper_file_name="EntityMapper.java";
    	if ((getline < entity_mapper_file_name) < 0) {
		printf("Generating Entity Mapper Object Code for : %s %d\n", tmp_file_name, i);
		printf("package %s.service.mapper;\n\n", proj_name)>entity_mapper_file_name;
		printf("import java.util.List;\n\n")>>entity_mapper_file_name;
		printf("/**\n")>>entity_mapper_file_name;
		printf("*\n")>>entity_mapper_file_name;
		printf("* @param <D> - DTO type parameter.\n")>>entity_mapper_file_name;
		printf("* @param <E> - Entity type parameter.\n")>>entity_mapper_file_name;
		printf("*/\n\n")>>entity_mapper_file_name;
		printf("public interface EntityMapper <D,E> {\n")>>entity_mapper_file_name;
		printf("\tE toEntity(D dto);\n\n")>>entity_mapper_file_name;
		printf("\tD toDto(E entity);\n\n")>>entity_mapper_file_name;
		printf("\tList <E> toEntity(List<D> dtoList);\n\n")>>entity_mapper_file_name;
		printf("\tList <D> toDto(List<E> entityList);\n")>>entity_mapper_file_name;
		printf("}\n")>>entity_mapper_file_name;
	}

	printf("Generating Mapper Object Code for : %s %d\n", tmp_file_name, i);
	printf("package %s.service.mapper;\n\n", proj_name)>mapper_file_name;


	printf("import org.mapstruct.Mapper;\n")>>mapper_file_name;
	printf("import org.mapstruct.Mapping;\n")>>mapper_file_name;


	printf("import %s.domain.%s;\n", proj_name, tmp_file_name)>>mapper_file_name;
	printf("import %s.service.dto.%sDTO;\n\n", proj_name, tmp_file_name)>>mapper_file_name;
	FIRSTCHAR = substr(tmp_file_name,1,1);
	if (CHAR = index(upper, FIRSTCHAR)) {
		myClassVar = substr(lower, CHAR, 1) substr(tmp_file_name, 2);
	}

	firstClass = 0;
	atLeastOneRefClass = 0;
	refClassList = "";
	for ( j = 1; j <= i; ++j )  {
		n=index(keys[j],".");
		if ( n != 0 ) {
			atLeastOneRefClass = 1;
			refClass = substr(keys[j],0, n-1);
			if ( firstClass == 0 ) {
				refClassList = refClass "Mapper.class";
				firstClass = 1;
			} else {
				refClassList = refClassList "," refClass "Mapper.class";
			}
		} 
	}
	if ( atLeastOneRefClass == 0 ) 
		printf("@Mapper(componentModel = \"spring\", uses = {})\n")>>mapper_file_name;
	else {
		printf("@Mapper(componentModel = \"spring\", uses = {%s})\n", refClassList)>>mapper_file_name;
	}
	printf("public interface %sMapper extends EntityMapper<%sDTO, %s> {\n", tmp_file_name, tmp_file_name, tmp_file_name)>>mapper_file_name;

	toDtoToBeAdded=0;
	for ( j = 1; j <= i; ++j )  {
		n=index(keys[j],".");
		if ( n != 0 ) {
			refClass = substr(keys[j],0, n-1); refClassChar = substr(keys[j],1,1);
			if (CHAR = index(upper, refClassChar)) {
				refClassVar = substr(lower, CHAR, 1) substr(refClass, 2);
			}
			printf("\t@Mapping(source = \"%s.id\", target = \"%s\")\n", refClassVar, field_names[j])>>mapper_file_name;
			toDtoToBeAdded=1;
		}
	}
	
	# Adding this to add after all @Mappings
	if ( toDtoToBeAdded == 1 ) {
		printf("\t%sDTO toDto(%s %s);\n\n", tmp_file_name, tmp_file_name, myClassVar)>>mapper_file_name;
	}

	toEntityToBeAdded=0;
	for ( j = 1; j <= i; ++j )  {
		n=index(keys[j],".");
		if ( n != 0 ) {
			refClass = substr(keys[j],0, n-1);
			refClassChar = substr(keys[j],1,1);
			if (CHAR = index(upper, refClassChar)) {
				refClassVar = substr(lower, CHAR, 1) substr(refClass, 2);
			}
			printf("\t@Mapping(source = \"%s\", target = \"%s\")\n", field_names[j], refClassVar)>>mapper_file_name;
			toEntityToBeAdded=1;
		}
	}
	
	# Adding this to add after all @Mappings
	if ( toEntityToBeAdded == 1 ) {
		printf("\t%s toEntity(%sDTO %sDTO);\n\n", tmp_file_name, tmp_file_name, myClassVar)>>mapper_file_name;
	}
	
	printf("\tdefault %s fromId(Long id) {\n", tmp_file_name)>>mapper_file_name;
	printf("\t\tif (id == null) {\n")>>mapper_file_name;
	printf("\t\t\treturn null;\n")>>mapper_file_name;
	printf("\t\t}\n")>>mapper_file_name;
	printf("\t\t%s %s = new %s();\n", tmp_file_name, myClassVar, tmp_file_name)>>mapper_file_name;
	printf("\t\t%s.setId(id);\n", myClassVar)>>mapper_file_name;
	printf("\t\treturn %s;\n", myClassVar)>>mapper_file_name;
	printf("\t}\n")>>mapper_file_name;
	printf("}")>>mapper_file_name;
  }

  function write_dto_file() {
	printf("Generating DTO Object Code for : %s %d\n", tmp_file_name, i);
	printf("package %s.service.dto;\n\n", proj_name)>dto_file_name;
	
	printf("import org.springframework.format.annotation.DateTimeFormat;\n")>>dto_file_name;
	printf("import java.io.Serializable;\n")>>dto_file_name;
	printf("import java.time.Instant;\n\n")>>dto_file_name;
	
	printf("public class %sDTO implements Serializable {\n\n", tmp_file_name)>>dto_file_name;
	printf("\tprivate static final long serialVersionUID = 1L;\n")>>dto_file_name;
	for ( j = 1; j <= i; ++j )  {
		if ( field_types[j] == "Instant" ) {
			printf("\t@DateTimeFormat(pattern = \"YYYY-MM-DD\")\n")>>dto_file_name;
		}
  		printf("\tprivate %s %s;\n", field_types[j], field_names[j])>>dto_file_name;
	}
	
  	printf("\n")>>dto_file_name;
	# Get and Set Methods
        for ( j = 1; j <= i; ++j ) {
                FIRSTCHAR = substr(field_names[j],1,1);
                if (CHAR = index(lower, FIRSTCHAR)) {
               		dtoFieldName = substr(upper, CHAR, 1) substr(field_names[j], 2);
               	}
		# Get Method
		printf("\tpublic %s get%s() {\n", field_types[j], dtoFieldName)>>dto_file_name;
		printf("\t\treturn %s;\n", field_names[j])>>dto_file_name;
		printf("\t}\n")>>dto_file_name;
		printf("\n")>>dto_file_name;
	
		# Set Method
		printf("\tpublic void set%s(%s %s) {\n", dtoFieldName, field_types[j], field_names[j] )>>dto_file_name;
		printf("\t\tthis.%s = %s;\n", field_names[j], field_names[j])>>dto_file_name;
		printf("\t}\n")>>dto_file_name;
		printf("\n")>>dto_file_name;
	}

	# Start of toString
	printf("\tpublic String toString() {\n")>>dto_file_name;
	printf("\t\treturn \"%sDTO [\" +\n", tmp_file_name)>>dto_file_name;
       	for ( j = 1; j <= i; ++j ) {
		if ( field_types[j] == "Instant" ) {
			printf("\t\t\"%s=\" + %s.toString() +\n",field_names[j], field_names[j]) >> dto_file_name;
		} else {
			printf("\t\t\"%s=\" + %s +\n",field_names[j], field_names[j]) >> dto_file_name;
		}
	}
	printf("\t\t\"]\";\n")>>dto_file_name;
	printf("\t}\n")>>dto_file_name;
	# End of toString
  
	# Final closing
	printf("}\n")>>dto_file_name;

  }

  function write_domain_file() {
	printf("Generating Domain Object Code for : %s %d\n", tmp_file_name, i);
	printf("package %s.domain;\n\n", proj_name)>domain_file_name;
	printf("import java.time.Instant;\n")>>domain_file_name;
	printf("import javax.persistence.Column;\n")>>domain_file_name;
	printf("import javax.persistence.Entity;\n")>>domain_file_name;
	printf("import javax.persistence.FetchType;\n")>>domain_file_name;
	printf("import javax.persistence.GeneratedValue;\n")>>domain_file_name;
	printf("import javax.persistence.GenerationType;\n")>>domain_file_name;
	printf("import javax.persistence.Id;\n")>>domain_file_name;
	printf("import javax.persistence.JoinColumn;\n")>>domain_file_name;
	printf("import javax.persistence.ManyToOne;\n")>>domain_file_name;
	printf("import javax.persistence.Table;\n\n")>>domain_file_name;
	printf("import org.hibernate.envers.Audited;\n\n")>>domain_file_name;

	printf("@Audited\n")>>domain_file_name;
	printf("@Entity\n")>>domain_file_name;
	printf("@Table(name = \"%s\")\n", tolower(tmp_file_name))>>domain_file_name;
	printf("public class %s extends AbstractAuditingEntity {\n", tmp_file_name)>>domain_file_name;
	printf("\t@Id\n")>>domain_file_name;
	printf("\t@GeneratedValue(strategy = GenerationType.IDENTITY)\n")>>domain_file_name;
	for ( j = 1; j <= i; ++j )  {
		if ( j == 1 ) { 
			if ( default_values[j] != "" )
				printf("\t@Value(\"%s\")\n", default_values[j])>>domain_file_name;
  			printf("\tprivate %s %s;\n\n", field_types[j], field_names[j])>>domain_file_name;
		}


		else {
			n=index(keys[j],".");
			if ( n == 0 ) {
				printf("\t@Column(name = \"%s\")\n", field_names[j])>>domain_file_name;
				if ( default_values[j] != "" )
					printf("\t@Value(\"%s\")\n", default_values[j])>>domain_file_name;
  				printf("\tprivate %s %s;\n\n", field_types[j], field_names[j])>>domain_file_name;
			} else {
				n=index(keys[j],".");
	                        refClass = substr(keys[j],0, n-1);
       	                	refClassChar = substr(keys[j],1,1);
                        	if (CHAR = index(upper, refClassChar)) {
                                	refClassVar = substr(lower, CHAR, 1) substr(refClass, 2);
                        	}
				printf("\t@ManyToOne(fetch = FetchType.LAZY)\n")>> domain_file_name;
				printf("\t@JoinColumn(name = \"%s\")\n", field_names[j])>>domain_file_name;
				# Replacing the variable
				saved_field_names[j] = field_names[j];
				saved_field_types[j] = field_types[j];
				field_names[j] = refClassVar;	
				field_types[j] = refClass;	
				if ( default_values[j] != "" )
					printf("\t@Value(\"%s\")\n", default_values[j])>>domain_file_name;
				printf("\tprivate %s %s;\n\n", substr(keys[j],0,n-1), field_names[j])>>domain_file_name;
				
			}
		}
	}

	# Constructors
	printf ("\tpublic %s() {\n", tmp_file_name)>>domain_file_name;
	printf ("\t}\n\n", tmp_file_name)>>domain_file_name;

	# Constructor
        printf("\tpublic %s(", tmp_file_name)>>domain_file_name;
	firstTime = 0;
        for ( j = 1; j <= i; ++j ) {
          	n=split(field_names[j], a, "_");
          	FIRSTCHAR = substr(a[1],1,1);
          	if (CHAR = index(lower, FIRSTCHAR)) {
                	a[1] = substr(upper, CHAR, 1) substr(a[1], 2);
          	}
          	new_field_names[j] = a[1];
          	for ( idx = 2; idx <= n; idx++ ) {
                	FIRSTCHAR = substr(a[idx],1,1);
                	if (CHAR = index(lower, FIRSTCHAR)) {
                  		a[idx] = substr(upper, CHAR, 1) substr(a[idx], 2);
                	}
                	new_field_names[j] = new_field_names[j] a[idx];
          	}
	  	if ( keys[j] == "" || keys[j] == "key" )  {
          		if (field_types[j]  == "Integer" ) {
				if ( firstTime == 0 ) {
               		 		printf("Integer %s", field_names[j])>>domain_file_name;
					firstTime = 1;
				} else {
                			printf(", Integer %s", field_names[j])>>domain_file_name;
				}
          		} else if (field_types[j]  == "String" )  {
				if ( firstTime == 0 ) {
                			printf("String %s", field_names[j])>>domain_file_name;
					firstTime = 1;
				} else {
                			printf(", String %s", field_names[j])>>domain_file_name;
				}
          		} else if (field_types[j]  == "Instant" )  {
				if ( firstTime == 0 ) {
                			printf("Instant %s", field_names[j])>>domain_file_name;
					firstTime = 1;
				} else {
                			printf(", Instant %s", field_names[j])>>domain_file_name;
				}
          		} else if (field_types[j]  == "Double" )  {
				if ( firstTime == 0 ) {
                			printf("Double %s", field_names[j])>>domain_file_name;
					firstTime = 1;
				} else {
                			printf(", Double %s", field_names[j])>>domain_file_name;
				}
          		} else if (field_types[j]  == "Float" )  {
				if ( firstTime == 0 ) { 
                			printf("Float %s", field_names[j])>>domain_file_name;
					firstTime = 1;
				} else {
                			printf(", Float %s", field_names[j])>>domain_file_name;
				}
          		} else if (field_types[j]  == "Boolean" )  {
				if ( firstTime == 0 ) { 
                			printf("Boolean %s", field_names[j])>>domain_file_name;
					firstTime = 1;
				} else {
                			printf(", Boolean %s", field_names[j])>>domain_file_name;
				}
          		} else if (field_types[j]  == "Long" )  {
				if ( firstTime == 0 ) { 
                			printf("Long %s", field_names[j])>>domain_file_name;
					firstTime = 1;
				} else {
                			printf(", Long %s", field_names[j])>>domain_file_name;
				}
			} else {
				printf("Unknown java data type\n");
			}

          	} else {
			n=index(keys[j],".");
			refClass = substr(keys[j],0, n-1);
			refClassChar = substr(keys[j],1,1);
			if (CHAR = index(upper, refClassChar)) {
                     	  	refClassVar = substr(lower, CHAR, 1) substr(refClass, 2);
	                }
			if ( firstTime == 0 ) { 
                		printf("%s %s", refClass, refClassVar)>>domain_file_name;
				firstTime = 1;
			} else {
                		printf(", %s %s", refClass, refClassVar)>>domain_file_name;
			}
		}
	}
	printf(") {\n") >> domain_file_name;
       	for ( j = 1; j <= i; ++j ) {
		printf("\t\tthis.%s = %s;\n",field_names[j], field_names[j]) >> domain_file_name;
	}
	printf("\t}\n")>>domain_file_name;
	printf("\n")>>domain_file_name;


	# Get and Set Methods
        for ( j = 1; j <= i; ++j ) {
		# Get Method
		printf("\tpublic %s get%s() {\n", field_types[j], new_field_names[j])>>domain_file_name;
		printf("\t\treturn %s;\n", field_names[j])>>domain_file_name;
		printf("\t}\n")>>domain_file_name;
		printf("\n")>>domain_file_name;
	
		# Set Method
		printf("\tpublic void set%s(%s %s) {\n",new_field_names[j], field_types[j], field_names[j] )>>domain_file_name;
		printf("\t\tthis.%s = %s;\n", field_names[j], field_names[j])>>domain_file_name;
		printf("\t}\n")>>domain_file_name;
		printf("\n")>>domain_file_name;
	}
	#printf("\t@Override\n")>>domain_file_name;
	printf("\tpublic String toString() {\n")>>domain_file_name;
	printf("\t\treturn \"%s [\" +\n", tmp_file_name)>>domain_file_name;
       	for ( j = 1; j <= i; ++j ) {
		if ( field_types[j] == "Instant" ) {
			printf("\t\t\"%s=\" + %s.toString() +\n",field_names[j], field_names[j]) >> domain_file_name;
		} else {
			printf("\t\t\"%s=\" + %s +\n",field_names[j], field_names[j]) >> domain_file_name;
		}
	}
	printf("\t\t\"]\";\n")>>domain_file_name;
	printf("\t}\n")>>domain_file_name;
	printf("}\n")>>domain_file_name;

	# Clear old values
	for ( j = 1; j <= i; ++j )  {
		default_values[j]="";
	}
	close(domain_file_name);
  }

  function write_repository_file() {
	printf("Generating Repository Access Code for : %s\n", tmp_file_name);
	printf("package %s.repository;\n\n", proj_name)>rep_file_name;
	printf("import org.springframework.data.jpa.repository.JpaRepository;\n")>>rep_file_name;
	import_list_if_required(rep_file_name);
	printf("import org.springframework.data.querydsl.QuerydslPredicateExecutor;\n")>>rep_file_name;
	printf("import org.springframework.stereotype.Repository;\n\n")>>rep_file_name;
	printf("import %s.domain.%s;\n\n", proj_name, tmp_file_name)>>rep_file_name;
	printf("@Repository\n")>>rep_file_name;
	printf("public interface %sRepository extends JpaRepository<%s, %s>, QuerydslPredicateExecutor<%s> {\n\n", tmp_file_name, tmp_file_name, field_types[1], tmp_file_name)>>rep_file_name; # Assume first field is key

	for ( j = 1; j <= i; ++j )  {
		n=index(keys[j],".");
		if ( n != 0 ) {
			fn_name=get_mangled_filenames(j);
			printf("\tList<%s> %s(%s %s);\n", tmp_file_name, fn_name, saved_field_types[j], saved_field_names[j])>>rep_file_name; # Assume first field is key
		}
	}
	printf("}\n")>>rep_file_name;
	close(rep_file_name);
  }

  function write_service_file() {
	printf("Generating Service Access Code for : %s\n", tmp_file_name);
	printf("package %s.service;\n\n", proj_name)>service_file_name;

	printf("import java.util.LinkedList;\n")>>service_file_name;
	printf("import java.util.List;\n")>>service_file_name;
	printf("import java.util.Optional;\n")>>service_file_name;
	printf("import java.util.stream.Collectors;\n")>>service_file_name;
	printf("import java.util.stream.StreamSupport;\n\n")>>service_file_name;
	printf("import %s.service.mapper.%sMapper;\n\n", proj_name, tmp_file_name)>>service_file_name;

	printf("import javax.transaction.Transactional;\n\n")>>service_file_name;

	printf("import org.springframework.beans.factory.annotation.Autowired;\n")>>service_file_name;
	printf("import org.springframework.stereotype.Service;\n\n")>>service_file_name;

	printf("import %s.repository.%sRepository;\n", proj_name, tmp_file_name)>>service_file_name;
	printf("import %s.service.dto.%sDTO;\n", proj_name, tmp_file_name)>>service_file_name;
	printf("import %s.domain.%s;\n\n", proj_name, tmp_file_name)>>service_file_name

	printf("@Service\n")>>service_file_name;
	printf("@Transactional\n")>>service_file_name;
	printf("public class %sService {\n\n", tmp_file_name)>>service_file_name;

        serviceClassChar = substr(tmp_file_name,1,1);
	if (CHAR = index(upper, serviceClassChar)) {
        	serviceClassVar = substr(lower, CHAR, 1) substr(tmp_file_name, 2);
	}
	printf("\t@Autowired\n")>>service_file_name;
	printf("\tprivate %sRepository %sRepository;\n\n", tmp_file_name, serviceClassVar)>>service_file_name;

	printf("\t//@Autowired\n")>>service_file_name;
	printf("\tprivate final %sMapper %sMapper;\n\n", tmp_file_name, serviceClassVar)>>service_file_name;
	printf("\tpublic %sService(%sMapper %sMapper) {\n", tmp_file_name, tmp_file_name, serviceClassVar)>>service_file_name;
	printf("\t\tthis.%sMapper = %sMapper;\n", serviceClassVar, serviceClassVar)>>service_file_name;
	printf("\t}\n\n")>>service_file_name;

	printf("\tpublic List<%sDTO> getAll() {\n", tmp_file_name)>>service_file_name;
	printf("\t\treturn %sRepository.findAll().stream().map(%sMapper::toDto).collect(Collectors.toCollection(LinkedList::new));\n", serviceClassVar, serviceClassVar)>>service_file_name;
	printf("\t}\n\n")>>service_file_name;

	printf("\tpublic Optional<%sDTO> findBy(%s id) {\n", tmp_file_name, field_types[1])>>service_file_name; # Assuming first one is the key
	printf("\t\t//log.debug(\"Request to get %s : {}\", id);\n", tmp_file_name)>>service_file_name;
	printf("\t\treturn %sRepository.findById(id).map(%sMapper::toDto);\n", serviceClassVar, serviceClassVar)>>service_file_name;
	printf("\t}\n\n")>>service_file_name;

	printf("\tpublic %sDTO save(%sDTO %sDTO) {\n", tmp_file_name, tmp_file_name, serviceClassVar)>>service_file_name;
	printf("\t\t//log.debug(\"Request to save %s : {}\", %sDTO);\n", tmp_file_name, serviceClassVar)>>service_file_name;
	printf("\t\t%s %s = %sMapper.toEntity(%sDTO);\n", tmp_file_name, serviceClassVar, serviceClassVar, serviceClassVar)>>service_file_name;
	printf("\t\t%s = %sRepository.save(%s);\n", serviceClassVar, serviceClassVar, serviceClassVar)>>service_file_name;
	printf("\t\t%sDTO result = %sMapper.toDto(%s);\n", tmp_file_name, serviceClassVar, serviceClassVar)>>service_file_name;
	printf("\t\treturn result;\n")>>service_file_name;
	printf("\t}\n\n")>>service_file_name;

	# find by foreign key methods
	for ( j = 1; j <= i; ++j )  {
                n=index(keys[j],".");
                if ( n != 0 ) {
			fn_name=get_mangled_filenames(j);
			printf("\tpublic List<%sDTO> %s(%s %s) {\n", tmp_file_name, fn_name, saved_field_types[j], saved_field_names[j])>>service_file_name;
			printf("\t\t//log.debug(\"Request to get %s : {}\", %s);\n", tmp_file_name, saved_field_names[j])>>service_file_name;
			
			printf("\t\treturn %sRepository.%s(%s).stream().map(%sMapper::toDto).collect(Collectors.toCollection(LinkedList::new));\n", serviceClassVar, fn_name, saved_field_names[j], serviceClassVar)>>service_file_name;
                			}
        }
	printf("\t}\n\n")>>service_file_name;


	printf("\tpublic void delete(%s id) {\n", field_types[1])>>service_file_name; # Assume first one is key
	printf("\t\t//log.debug(\"Request to delete : {}\",id );\n", tmp_file_name, serviceClassVar)>>service_file_name;
	printf("\t\t%sRepository.deleteById(id);\n", serviceClassVar)>>service_file_name;
	printf("\t}\n")>>service_file_name;

	printf("}\n")>>service_file_name;
	close(service_file_name);
  } 
  
  function write_rest_file() {
	lower_table_name = tolower(table_name);
	printf("Generating REST Code for : %s\n", tmp_file_name, i);

	printf("package %s.rest;\n\n", proj_name)>rest_file_name;
 
	printf("import java.util.*;\n\n")>>rest_file_name;
 
	printf("import org.springframework.beans.factory.annotation.*;\n\n")>>rest_file_name;
 
	printf("import org.springframework.web.bind.annotation.*;\n")>>rest_file_name;
	printf("import %s.service.%sService;\n", proj_name, tmp_file_name)>>rest_file_name;
	printf("import %s.service.dto.%sDTO;\n\n", proj_name, tmp_file_name)>>rest_file_name;
 
	printf("@RestController\n")>>rest_file_name;
	printf("public class %sController {\n\n", tmp_file_name)>>rest_file_name;
 
	printf("\t@Autowired\n")>>rest_file_name;
	printf("\tprivate %sService service;\n\n", tmp_file_name)>>rest_file_name;
     
	printf("\t// RESTful API methods for Retrieval operation\n")>>rest_file_name;
	printf("\t@GetMapping(\"/%s\")\n", lower_table_name)>>rest_file_name;
	printf("\tpublic List<%sDTO> getAll%ss() {\n", tmp_file_name, tmp_file_name)>>rest_file_name;
	printf("\t\t//log.debug(\"REST request to get all %s\");\n", tmp_file_name)>>rest_file_name;
	printf("\t\treturn service.getAll();\n")>>rest_file_name;
	printf("\t}\n\n")>>rest_file_name;

	printf("\t@GetMapping(\"/%s/{id}\")\n", lower_table_name)>>rest_file_name;
	printf("\tpublic Optional<%sDTO> get%s(@PathVariable %s id) {\n", tmp_file_name, tmp_file_name, field_types[1])>>rest_file_name; # Assuming first field as key
	printf("\t\t//log.debug(\"REST request to get %ss : {}\", id);\n", tmp_file_name)>>rest_file_name;
	printf("\t\treturn service.findBy(id);\n", tmp_file_name, lower_table_name)>>rest_file_name;
	printf("\t}\n")>>rest_file_name;
	
	printf("\t@PostMapping(\"/%s\")\n", lower_table_name)>>rest_file_name;
	printf("\tpublic void post%s(@RequestBody %sDTO %s) {\n", tmp_file_name, tmp_file_name, lower_table_name)>>rest_file_name;
	printf("\t\t//log.debug(\"REST request to save %s : {}\", id);\n", lower_table_name)>>rest_file_name;
	printf("\t\tservice.save(%s);\n", lower_table_name)>>rest_file_name;
	printf("\t\n")>>rest_file_name;
	printf("\t}\n")>>rest_file_name;

	 # find by foreign key methods
        for ( j = 1; j <= i; ++j )  {
                n=index(keys[j],".");
                if ( n != 0 ) {
                        fn_name=get_mangled_filenames(j);
			printf("\t@GetMapping(\"/%s/%s/{id}\")\n", lower_table_name, tolower(field_names[j]))>>rest_file_name;
                	FIRSTCHAR = substr(field_names[j],1,1);
                	if (CHAR = index(lower, FIRSTCHAR)) {
               			newFieldName = substr(upper, CHAR, 1) substr(field_names[j], 2);
               		}
			printf("\tpublic List<%sDTO> get%s%s(@PathVariable %s id) {\n", tmp_file_name, tmp_file_name, newFieldName, saved_field_types[j])>>rest_file_name; # Assuming first field as key
			printf("\t\t//log.debug(\"REST request to get %ss : {}\", id);\n", tmp_file_name)>>rest_file_name;
			printf("\t\treturn service.%s(id);\n", fn_name)>>rest_file_name;
			printf("\t}\n")>>rest_file_name;
		}
	}
	
	printf("\t\n")>>rest_file_name;
	printf("}\n")>>rest_file_name;
	close(rest_file_name);
  }

  NF == 1 {
	# Generation of first table in schema file will be done before 
	# parsing next table
	if ( table_name != "" ) {
		# write_dto_file and write_mapper_file 
		# needs to be called before write_domain_file
		write_dto_file();
		write_mapper_file();
		write_domain_file();
		write_repository_file();
		write_service_file();
		write_rest_file();
	}
	i = 0;
	table_name=$1; 
	all_tables[k++] = table_name;
	tmp_file_name = table_name;
	gsub("_", "", tmp_file_name);
	domain_file_name=tmp_file_name ".java"
	dto_file_name=tmp_file_name "DTO.java"
	mapper_file_name=tmp_file_name "Mapper.java"
	rep_file_name=tmp_file_name "Repository.java"
	service_file_name=tmp_file_name "Service.java"
	rest_file_name=tmp_file_name "Controller.java"
  }
  NF == 2 || NF == 3 || NF == 4{ 
	i++;
	#field_names[i] = tolower($1);
	field_names[i] = $1;
	lower_two = tolower($2)
	if ( lower_two == "integer" || lower_two == "int" || lower_two == "number")
	  	field_types[i] = "Integer";
	else if ( lower_two ~ /varchar/ || lower_two == "long" || lower_two == "clob" ) {
	  	field_types[i] = "String";
	}
	else if (lower_two == "datetime" || lower_two == "timestamp" ) {
	  	field_types[i] = "Instant";
	}
	else if (lower_two == "decimal" || lower_two == "Decimal" || lower_two == "double" ) {
	  	field_types[i] = "Double";
	}
	else if (lower_two == "float" ) {
		field_types[i] = "Float";
	}
	else if (lower_two == "boolean" ) {
		field_types[i] = "Boolean";
	}
	else if (lower_two == "bigint" ) {
		field_types[i] = "Long";
	}
	orig_field_types[i] = lower_two;
	if ( $3 == "key" ) {
		keys[i] = $3;
  	}
	else if ( $3 == "references" ) {
	  	keys[i] = $4;
	}
	else if ( $3 == "not" ) {
		keys[i] = "not";
	} 
	else if ( $3 == "default" ) {
		keys[i] = "";
		default_values[i] = $4;
	}
	else {
		keys[i] = "";
  	}
  }
  END { 
  	  # write_dto_file and write_mapper_file 
	  # needs to be called before write_domain_file
	  write_dto_file();
	  write_mapper_file();
	  write_domain_file();
	  write_repository_file();
	  write_service_file();
	  write_rest_file();
  }' $SCHEMA_FILE
  
BASEDIR=`dirname $0`/..
######################################################################################################
rm -f /tmp/tables /tmp/rtables
