package Experimemts;

/**
 * Created by i852841 on 5/16/18.
 */

import Utils.ExcelOpener;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
     HSSF is prefixed before the class name to indicate operations related to a Microsoft Excel 2003 file.
     XSSF is prefixed before the class name to indicate operations related to a Microsoft Excel 2007 file or later.
     XSSFWorkbook and HSSFWorkbook are classes which act as an Excel Workbook
     HSSFSheet and XSSFSheet are classes which act as an Excel Worksheet
     Row defines an Excel row
     Cell defines an Excel cell addressed in reference to a row.
 */
// Todo 1. Color
// Todo 2. Append Mode
public class POIDemo {

    //private static final String FILE_NAME = "/tmp/MyFirstExcel.xlsx";
    private static final String FILE_NAME = "/Users/i852841/Downloads/poi-shift-column-master/out.xlsx";
    private static final String FILE_LOC = "/Users/i852841/Downloads/poi-shift-column-master/";


    private void demoPOI() {

        File file = null;
        file = new File(FILE_NAME);
        if(file.exists())
            file.delete();

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
        Object[][] datatypes = {
                {"Datatype", "Type", "Size(in bytes)"},
                {"int", "Primitive", 2},
                {"float", "Primitive", 4},
                {"double", "Primitive", 8},
                {"char", "Primitive", 1},
                {"String", "Non-Primitive", "No fixed size"}
        };

        //Start: Set Color to Cell
        CellStyle style = workbook.createCellStyle();
        style = workbook.createCellStyle();
        //style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //End: Set Color to Cell

        int rowNum = 0;
        System.out.println("Creating excel");

        for (Object[] datatype : datatypes) {

            Row row = sheet.createRow(rowNum++);
            int colNum = 0;

            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                    //Use of Set color to cell
                    cell.setCellStyle(style);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

    private void colorDemo() throws IOException {

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(1);

        // Aqua background
        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(FillPatternType.BIG_SPOTS);
        Cell cell = row.createCell(1);
        cell.setCellValue("X");
        cell.setCellStyle(style);

        // Orange "foreground", foreground being the fill foreground not the font color.
        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell = row.createCell(2);
        cell.setCellValue("X");
        cell.setCellStyle(style);

        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("/tmp/workbook.xls")) {
            wb.write(fileOut);
        }

        wb.close();
    }


    //Working
    private void insertNewRankColumn() {

        try {

            List<String> latestRankings = new ArrayList<>();
            latestRankings.add("M:AA");
            latestRankings.add("ETSY:BB");
            latestRankings.add("NTNX:CC");
            latestRankings.add("CURO:DD");
            latestRankings.add("BRKB:EE");
            latestRankings.add("QQQ:FF");

            String dateStmap = "May_16";

            FileInputStream inputStream = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getLastRowNum();
            System.out.println("Row count is [" + rowCount + "]");

            //START:: Cell Style
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //END: Cell Style

            //START: Update date
            Row firstRow   = sheet.getRow(0);
            Cell firstRowSecondCell = firstRow.createCell(1);
            firstRowSecondCell.setCellValue(dateStmap);
            //END: Update date


            for(int rowIndex=1; rowIndex<=rowCount; rowIndex++){
                Row currentRow   = sheet.getRow(rowIndex);
                Cell currentCell = currentRow.createCell(1);
                currentCell.setCellValue(latestRankings.get(rowIndex - 1).split(":")[1]);
                currentCell.setCellStyle(style);
            }

            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
            ex.printStackTrace();
        }
    }



    public void testInsertNewColumn () throws InvalidFormatException, IOException {

        System.out.println("testInsertNewColumn()");
        int sheetIndex = 0;
        int columnIndex = 1;

        ExcelOpener op = new ExcelOpener(FILE_LOC + "test.xlsx", FILE_LOC + "out.xlsx");
        op.open();

        //int nrColumnsBefore = op.getNrColumns(sheetIndex);
        op.insertNewColumnBefore(sheetIndex, columnIndex);
        //int nrColumnsAfter = op.getNrColumns(sheetIndex);

        op.close();
    }





    public static void main(String[] args) throws IOException, InvalidFormatException {

        POIDemo poiDemo = new POIDemo();
        //poiDemo.demoPOI();
        //poiDemo.colorDemo();
        //poiDemo.insertNewRankColumn();
        poiDemo.testInsertNewColumn();
    }








}























