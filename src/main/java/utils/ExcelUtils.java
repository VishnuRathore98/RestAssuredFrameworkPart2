package utils;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelUtils {
    public static List<LinkedHashMap<String ,String >> getExcelDataAsListOfMap(String excelFileName, String sheetName) throws IOException{
        List<LinkedHashMap<String ,String >> dataFromExcel = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(new File("src/test/resources/testdata/"+excelFileName+".xlsx"));
        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();
        LinkedHashMap<String,String> mapData;
        List<String> allKeys = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();
        for (int i = 0; i < totalRows; i++) {
            mapData = new LinkedHashMap<>();
            if (i == 0) {
                int totalCols = sheet.getRow(0).getPhysicalNumberOfCells();
                for (int j=0;j<totalCols;j++){
                    allKeys.add(sheet.getRow(0).getCell(j).getStringCellValue());
                }
            }
            else {
                int totalCols = sheet.getRow(i).getPhysicalNumberOfCells();
                for (int j = 0; j < totalCols; j++) {
                    String cellValue = dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));

//                  Generating Id based on the instructions provided in the Id column if provided user those instructions
//                  otherwise use the number given.

                    int size = 6;
                    if (cellValue.contains("RandomId")) {
//                     With size
                        if (cellValue.contains("_")) {
                            size = Integer.parseInt((cellValue.split("_"))[1]);
                        }
                        cellValue = String.valueOf(RandomDataGenerator.getRandomNumber(size,size));
                    }

                    mapData.put(allKeys.get(j), cellValue);
                }
                dataFromExcel.add(mapData);
            }
        }

//      Executing only those test scenarios where "isEnabled is set to be true, in the excel report."

        dataFromExcel = dataFromExcel.stream().filter(keyValuePair -> keyValuePair.get("isEnabled").equalsIgnoreCase("true"))
            .collect(Collectors.toList());

        return dataFromExcel;
    }
}