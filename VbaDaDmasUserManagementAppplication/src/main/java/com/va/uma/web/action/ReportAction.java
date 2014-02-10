package com.va.uma.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.va.uma.model.Team;
import com.va.uma.model.UserInfo;
import com.va.uma.model.UserInfo.UserStatus;

@Controller
public class ReportAction extends BaseAction {

	@RequestMapping("report/list.do")
	public String viewReportList(HttpServletRequest request,
			HttpServletResponse response) {

		return "report/report";

	}

	@RequestMapping("/report/runReport.do")
	public void runReport(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "team", required = false, defaultValue = "") String teamName,
			@RequestParam(value = "appName", required = false, defaultValue = "") String appName,
			@RequestParam(value = "status", required = false, defaultValue = "") UserStatus status) {
		JSONArray ja = new JSONArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "files", "createdBy", "lastUpdatedBy", "id", "code", "password", "email", "login", 
				"createDate", "approverEmail", "approverPhone", "approverFullname", "requestDetail", "alert",
				"type", "middleName", "userAppAccessList"});
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		try {
			Team team = appService.getTeamInfoByName(teamName);
			List<UserInfo> list = userService.getReport(team, appName, status);
			ja.addAll(list, jsonConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}

		responseJsonWithCodeAndDataStr(response, "ok", ja.toString());
	}

}
