package cares.cwds.salesforce.utilities.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.JOptionPane;


public class PoiReadExcel
    {

		private PoiReadExcel() {
			// this is empty
		}

	
    	private static final Logger logger = LoggerFactory.getLogger(PoiReadExcel.class.getName());

    	public static Map<String, ArrayList<String>> fetchWithCondition(String sheetPath, String sheetName, List<String> whereClause) {
    		Map<String, ArrayList<String>> excelMap = coreListToMap(sheetPath, sheetName);
    		for (String clause : whereClause) {
    			HashMap<String, ArrayList<String>> finalMap = new HashMap<>();
    			ArrayList<Integer> addIndex = new ArrayList<>();
    			arrayIterator(excelMap, clause, finalMap, addIndex);
    			for (Map.Entry<String, ArrayList<String>> entry : excelMap.entrySet()) {
    				ArrayList<String> vals = new ArrayList<>();
    				if (!entry.getKey().equalsIgnoreCase(clause.split("::")[0])) {
    					for (int add : addIndex)
    						vals.add(entry.getValue().get(add));
    					finalMap.put(entry.getKey(), vals);
    				}
    			}
    			excelMap = finalMap;
    		}
    		return excelMap;
    	}

		private static void arrayIterator(Map<String, ArrayList<String>> excelMap, String clause,
				HashMap<String, ArrayList<String>> finalMap, ArrayList<Integer> addIndex) {
			for (Map.Entry<String, ArrayList<String>> entry : excelMap.entrySet()) {
				int k = 0;
				if (entry.getKey().equalsIgnoreCase(clause.split("::")[0])) {
					ArrayList<String> vals = new ArrayList<>();
					for (String val : new ArrayList<String>(entry.getValue())) {
						if (val.equalsIgnoreCase(clause.split("::")[1])) {
							vals.add(val);
							addIndex.add(k);
						}
						k++;
					}
					finalMap.put(entry.getKey(), vals);
				}
			}
		}
    	
    	public static Map<String, ArrayList<String>> coreListToMap(String sheetPath, String sheetName) {
    		List<ArrayList<String>> tempStorage = coreFetch(sheetPath, sheetName);
			if (tempStorage == null || tempStorage.isEmpty()) {
				return new HashMap<>();
			}

    		HashMap<String, ArrayList<String>> excelMap = new HashMap<>();
    		
    		ArrayList<ArrayList<String>> tempList = new ArrayList<>();

    		for(int j=0; j<tempStorage.get(0).size() ; j++){
    			ArrayList<String> eachCol = new ArrayList<>();
    			for(int i=1; i<tempStorage.size(); i++){
    				try{
    					eachCol.add(tempStorage.get(i).get(j));
    				}catch(IndexOutOfBoundsException e){
    					eachCol.add("");
    				}
    				
    			}
    			tempList.add(eachCol);
    		}
    		
    		for(int i=0; i<tempList.size(); i++){
    			excelMap.put(tempStorage.get(0).get(i), tempList.get(i));
    		}
    		return excelMap;
    	}

		public static List<ArrayList<String>> coreFetch(String sheetPath, String sheetName) {
			ArrayList<ArrayList<String>> tempStorage = new ArrayList<>();

			try (FileInputStream file = new FileInputStream(new File(sheetPath));
				 XSSFWorkbook workbook = new XSSFWorkbook(file)) {

				if (!(new File(sheetPath).exists())) {
					JOptionPane.showConfirmDialog(null, "File NOT found: " + sheetPath, "Warning", 2);
				}

				XSSFSheet sheet = workbook.getSheet(sheetName);

				Iterator<Row> rowIterator = sheet.iterator();
				int numOfHeaders = 0;

				if (rowIterator.hasNext()) {
					ArrayList<String> rowWise = new ArrayList<>();

					Row row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();

					int i = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						if (i != cell.getColumnIndex()) {
							rowWise.add("");
						}

						i = cell.getColumnIndex() + 1;

						switch (cell.getCellType()) {
							case STRING:
								rowWise.add(cell.getStringCellValue());
								break;
							case NUMERIC:
								rowWise.add(Integer.toString((int) (cell.getNumericCellValue())));
								break;
							default:
						}
					}
					numOfHeaders = rowWise.size();
					tempStorage.add(rowWise);
				}

				while (rowIterator.hasNext()) {
					ArrayList<String> rowWise = new ArrayList<>();

					Row row = rowIterator.next();
					rowIterator(numOfHeaders, rowWise, row);
					tempStorage.add(rowWise);
				}
			} catch (FileNotFoundException e) {
				logger.error("File not found. File path:" + sheetPath);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return tempStorage;
		}

		private static void rowIterator(int numOfHeaders, ArrayList<String> rowWise, Row row) {
			for(int cellNumber=0; cellNumber < numOfHeaders; cellNumber++){
				Cell  cell = row.getCell(cellNumber);
				String dataValue = "";
				if(cell != null){						
					switch (cell.getCellType()) {
						case STRING:
							String cellValue = cell.getStringCellValue();
   
							rowWise.add(cellValue);
							break;
						case NUMERIC:
							rowWise.add(Long.toString((long) (cell.getNumericCellValue())));
							break;
						default: rowWise.add("");
						  break;
					}												
				}else{
					rowWise.add(dataValue);
				}
			}
		}

		public static void checkIfClose(FileInputStream file, XSSFWorkbook workbook) {
			try {
			    if (workbook != null) {
			        workbook.close();
			    }
			    if (file != null) {
			        file.close();
			    }
			} catch (IOException e) {
			    logger.info("Error closing resources");
			}
		}    	
    }