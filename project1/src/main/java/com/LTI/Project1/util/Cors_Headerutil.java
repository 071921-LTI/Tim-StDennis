package com.LTI.Project1.util;

import javax.servlet.http.HttpServletResponse;

public class Cors_Headerutil {
	public static void addCorsHeader(String requestURI, HttpServletResponse rs) {
		rs.addHeader("Access-Control-Allow-Origin", "*");
		rs.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
		rs.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization, ifo_UserInfo, ifo_ReimRecords, ifo_ShortReimList, ifo_DetailedReimList,ifo_EmployeeList");
		rs.addHeader("Access-Control-Expose-Headers", "Content-Type, Accept, Authorization, ifo_UserInfo, ifo_ReimRecords, ifo_ShortReimList, ifo_DetailedReimList,ifo_EmployeeList");
	}
}
