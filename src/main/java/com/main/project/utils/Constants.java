package com.main.project.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public static String DEFAULT_NESTED_QUERY = "SELECT $requiredColumns$ FROM ($dbQuery$) AS tempTable";
	
	public static String DEFAULT_LOCATION = "/Users/bharatjoshi/Desktop/LogFiles/myCsv.csv";
	
	public static List<String> ALLOWEED_COLUMNS_FOR_AGENT = Arrays.asList("email","contact_number","salary","user_name","address");
	
	public static List<String> ALLOWEED_COLUMNS_FOR_Admin = Arrays.asList("id","address","contact_number","email","is_deleted","password","role","salary","user_name");

}
