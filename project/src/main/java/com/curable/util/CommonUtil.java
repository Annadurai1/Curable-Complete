package com.curable.util;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.curable.config.SecurityUtils;
import com.curable.service.dto.SearchCustomDTO;
import com.curable.service.dto.custom.MailCustomDTO;

@Component
public class CommonUtil {
	@Autowired
	private TemplateEngine templateEngine;

	public String processHtml(String templateName, String name, MailCustomDTO data) throws Exception {
		Context context = new Context();
		String processedHtml;
		try {
			Assert.notNull(templateName, "The templateName can not be null");
			context.setVariable(name, data);
			processedHtml = templateEngine.process(templateName, context);

			return processedHtml;
		} finally {
			context = null;
			templateName = null;
		}
	}

	public static String filter(SearchCustomDTO searchCustomDTO) {
		String filter = null;
		if (Constant.PROGRAM_COORDINATOR == searchCustomDTO.getRoleId()) {
			filter = handleProgramCoordinatorRoleOrCampCoordinator(searchCustomDTO, filter, true);

		} else if (Constant.CAMP_COORDINATOR == searchCustomDTO.getRoleId()) {
			filter = handleProgramCoordinatorRoleOrCampCoordinator(searchCustomDTO, filter, false);
		} else {
			if (searchCustomDTO.getStage() == 1) {
				filter = "a.employeeId=" + searchCustomDTO.getUserId();
			} else if (searchCustomDTO.getStage() == 2) {
				// TODO condition
			}
		}
		return filter;

	}

	public static String handleProgramCoordinatorRoleOrCampCoordinator(SearchCustomDTO searchCustomDTO, String filter,
			boolean fullAccess) {
		if (searchCustomDTO.getStage() == 1) {
			filter = fullAccess ? "b.hospitalId=" + searchCustomDTO.getHospitalId()
					: "(b.createdBy = '" + SecurityUtils.getCurrentUserLogin() + "' OR a.employeeId = "
							+ searchCustomDTO.getUserId() + ")";

		} else if (searchCustomDTO.getStage() == 2) {
			// TODO: Add condition for stage 2
		}
		return filter;
	}

	public static LocalDate getApproxDob(int age) {
		int yearOfBirth = LocalDate.now().getYear() - age;
		return LocalDate.of(yearOfBirth, 1, 1); // sets DOB as Jan 1 of that year
	}

	public static String objectToString(List<Object> obj) {
		return (obj == null || obj.isEmpty()) ? null : obj.get(0).toString();
	}

	public static int objectToInt(List<Object> obj) {
		return (obj == null || obj.isEmpty()) ? 0 : Integer.parseInt(obj.get(0).toString());
	}

	public static boolean objectToBoolean(List<Object> obj) {
		return (obj == null || obj.isEmpty()) ? null : (obj.get(0).toString().trim() == "Yes" ? true : false);
	}

	public static void main(String[] args) {
		String c = "30-40";
		int age =30;
		System.out.println(matchAge(c, age));
	}

	public static boolean matchAge(String condition, int age) {
		condition = condition.trim();

		// Regex for operator + number
		Pattern p = Pattern.compile("^(>=|<=|>|<|=|!=)(\\d+)$");
		Matcher m = p.matcher(condition);

		if (m.matches()) {
			String operator = m.group(1);
			int value = Integer.parseInt(m.group(2));

			if (operator.equals(">="))
				return age >= value;
			else if (operator.equals("<="))
				return age <= value;
			else if (operator.equals(">"))
				return age > value;
			else if (operator.equals("<"))
				return age < value;
			else if (operator.equals("="))
				return age == value;
			else if (operator.equals("!="))
				return age != value;
		}

		// Regex for range: 30-40
		if (condition.matches("^\\d+-\\d+$")) {
			String[] range = condition.split("-");
			int min = Integer.parseInt(range[0]);
			int max = Integer.parseInt(range[1]);
			return age >= min && age <= max;
		}

		throw new IllegalArgumentException("Invalid condition: " + condition);
	}

}
