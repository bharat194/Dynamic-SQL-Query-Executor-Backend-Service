package com.main.project.response;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import com.main.project.utils.Constants;
import com.opencsv.CSVWriter;

@SuppressWarnings("resource")
public class ResponseOutput {

	public static void outputCSV(List<HashMap<String, Object>> resList) throws IOException {
		
		CSVWriter writer = new CSVWriter(new FileWriter(Constants.DEFAULT_LOCATION));
	
		List<String> vals = new ArrayList<>();
        
        resList.forEach(curMap -> {
        	vals.add(curMap.toString());
        });
        
        writer.writeNext(vals.toArray(new String[vals.size()]));
        
        writer.close();
	}
	
	public static String outputJSON(List<HashMap<String, Object>> resList) throws IOException {
		
		JSONArray jsonArray = new JSONArray();
		
		resList.forEach(curObject -> {
			JSONObject jsonObject = new JSONObject(curObject);
		    jsonArray.put(jsonObject);
		});
		
		return  jsonArray.toString();
	}
	
	public static String outputExcle(List<HashMap<String, Object>> resList) throws IOException {
		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Data");

		Row headerRow = sheet.createRow(0);
		int colIndex = 0;
		for (String key : resList.get(0).keySet()) {
		    Cell cell = headerRow.createCell(colIndex++);
		    cell.setCellValue(key);
		}
		int rowIndex = 1;
		for (Map<String, Object> data : resList) {
		    Row dataRow = sheet.createRow(rowIndex++);
		    colIndex = 0;
		    for (Object value : data.values()) {
		        Cell cell = dataRow.createCell(colIndex++);
		        if (value instanceof String) {
		            cell.setCellValue((String) value);
		        } else if (value instanceof Integer) {
		            cell.setCellValue((Integer) value);
		        } else if (value instanceof Double) {
		            cell.setCellValue((Double) value);
		        }
		    }
		}

		FileOutputStream fos = new FileOutputStream("data.xlsx");
		
		workbook.write(fos);
		
		return fos.toString();
	}
	
	public static String readCSV(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
  
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        reader.close();
        
        return sb.toString();
    }
	
}
