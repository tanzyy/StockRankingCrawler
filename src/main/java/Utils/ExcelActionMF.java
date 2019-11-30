package Utils;

import VO.DataAttributes;
import VO.ExcelProp;
import VO.MFInfo;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ExcelActionMF {

    private static final Logger LOG  = Logger.getLogger(ExcelActionMF.class);

    String rankDate = CommonUtils.getMonthDay();
    String rankYear = CommonUtils.getYear();

    private static final String RANK_UNAVAILABLE  = "UN";

    /**
     * 1. Backup file
     * 2. Shift columns
     * 3. Insert Row
     * 4. Delete temp file
     * @param excelProp
     */
    //public void writeToOneXL(ExcelProp excelProp, List<RankInfo> allFetchedData, String outLOC, String backLOC, boolean isETF, List<String> attributes, boolean isNumeric) {
    public void writeToOneXL(DataAttributes dataAttributes, ExcelProp excelProp, List<MFInfo> mfInfoList) {

        String fileWithLOC       = excelProp.getFileLoc() + File.separator + excelProp.getWorkBookName();
        File targetFileHandler   = new File(fileWithLOC);

        String backupFileWithLOC = excelProp.getBackFileLoc() + File.separator + CommonUtils.getCurrentTime() + Constants.SEPARATOR_UNDERSCORE + excelProp.getWorkBookName();
        File backupFileHandler   = new File(backupFileWithLOC);

        String tempFileWithLOC   = excelProp.getFileLoc() + File.separator + Constants.TEMP_PREFIX + excelProp.getWorkBookName();
        File tempFileHandler     = new File(tempFileWithLOC);

        //Do backup
        //Rename actual file to temp file
        if(targetFileHandler.exists()) {
            try {
                Files.copy(targetFileHandler.toPath(), backupFileHandler.toPath());
                targetFileHandler.renameTo(tempFileHandler);
            } catch (IOException e) {
                LOG.error("IOException while BackingUp File  " + targetFileHandler, e);
            }
        }

        if(tempFileHandler.exists()) {
            LOG.info(excelProp.getWorkBookName() + " exists.");

            FileInputStream inputStream = null;
            Workbook workbook           = null;
            try {
                inputStream = new FileInputStream(tempFileHandler);
                workbook = WorkbookFactory.create(inputStream);

                //Check if sheet exist
                boolean isSheetExists = isSheetExist(excelProp.getSheetName().toUpperCase(), workbook);
                LOG.info(String.format("#####################  Sheet name [%s] existence [%s]", excelProp.getSheetName(), isSheetExists));

                //Shift columns, if sheet exists
                if(isSheetExists) {

                    shiftColumns(tempFileWithLOC, fileWithLOC, excelProp);

                    //Insert New Rank data
                    insertNewRankColumn(fileWithLOC, dataAttributes, excelProp, mfInfoList);

                } else {

                    //Insert New Rank data
                    //insertNewSheet(tempFileWithLOC, mfInfoList, excelProp);
                    tempFileHandler.renameTo(targetFileHandler);
                }

                //Delete Temp file
                if(tempFileHandler.exists())
                    tempFileHandler.delete();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            } finally {

                if(inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(workbook != null) {
                    try {
                        workbook.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else {
            LOG.info(excelProp.getWorkBookName() + " does not exist, creating new one.");
            //createNewWorkBook(fileWithLOC, allFetchedData, excelProp, isETF, attributes);
        }
    }

    private boolean isSheetExist(String sheetName, Workbook workbook) {

        Sheet sheet = workbook.getSheet(sheetName);

        return sheet == null ? false : true;

    }

    private void shiftColumns(String inputFile, String outputFile, ExcelProp excelProp) {

        int sheetIndex  = excelProp.getSheetIndex()  == 0 ? 0 : excelProp.getSheetIndex();
        int columnIndex = excelProp.getColumnIndex() == 0 ? 1 : excelProp.getColumnIndex();

        ExcelOpener op = new ExcelOpener(inputFile, outputFile);
        try {
            op.open();
        } catch (InvalidFormatException e) {
            LOG.error("InvalidFormatException while Shifting Column ", e);
        } catch (IOException e) {
            LOG.error("IOException while Shifting Column ", e);
        }

        op.insertNewColumnBefore(sheetIndex, columnIndex);

        op.close();
    }


    //private void insertNewRankColumn(String targetFile, List<RankInfo> allFetchedData, ExcelProp excelProp, boolean isETF, List<String> attributes, boolean isNumeric) {
    private void insertNewRankColumn(String targetFile, DataAttributes dataAttributes, ExcelProp excelProp, List<MFInfo> mfInfoList) {

        try {

            FileInputStream inputStream = new FileInputStream(new File(targetFile));
            Workbook        workbook    = WorkbookFactory.create(inputStream);
            Sheet           sheet       = workbook.getSheet(excelProp.getSheetName());

            //START: Update date
            Row firstRow = sheet.getRow(0);
            Cell firstRowSecondCell = firstRow.createCell(1);
            firstRowSecondCell.setCellValue(rankDate);
            //END: Update date

            for (int rowIndex = 1; rowIndex <= mfInfoList.size(); rowIndex++) {

                Row currentRow       = sheet.getRow(rowIndex);
                Cell currentCell     = null;
                MFInfo currentMFInfo = mfInfoList.get(rowIndex - 1);

                LOG.info(String.format("Processing [%s] ", currentMFInfo.getSymbol()));

                if (currentRow == null) {

                    currentRow      = sheet.createRow(rowIndex);
                    currentCell     = currentRow.createCell(1);
                    currentCell.setCellValue((String)currentMFInfo.getMFInfo(excelProp.getSheetName()));
                    Cell symbolCell = currentRow.createCell(0);
                    symbolCell.setCellValue(currentMFInfo.getSymbol());

//                } else if (isNumeric) {
//
//                    currentCell = currentRow.createCell(1);
//                    //TODO make it agnostic to nav
//                    //In case data is unavailable for the scheme
//                    Double currentCellVal = 0.0;
//                    if (currentRankInfo.getNav() != "UN") {
//                        currentCellVal = Double.valueOf(currentMFInfo.getNav());
//                    }
//
//                    Cell previousCell = currentRow.getCell(2);
//                    Double previousCellVal = 0.0;
//
//                    if (previousCell != null)
//                        previousCellVal = Double.valueOf(previousCell.toString());
//
//                    LOG.info(String.format(
//                            "For Symbol [%s] ,  PreviousCellStr [%s] ,  PreviousCellVal [%s] ,  CurrentCellVal [%s]",
//                            currentRow.getCell(0), previousCell, previousCellVal, currentCellVal));
//
//                    CellStyle style = workbook.createCellStyle();
//                    if (currentCellVal != 0.0 && previousCellVal != 0.0) {
//
//                        if (previousCellVal < currentCellVal) {
//
//                            style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
//                            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//
//                        } else if (previousCellVal > currentCellVal) {
//
//                            style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
//                            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//                        }
//                    }
//                    currentCell.setCellStyle(style);
//                    currentCell.setCellValue(currentCellVal);
                } else {

                    currentCell = currentRow.createCell(1);
                    String currentCellVal = (String)currentMFInfo.getMFInfo(excelProp.getSheetName());
                    Cell previousCellVal = currentRow.getCell(2);

//                    LOG.info(String.format(
//                            "For Symbol [%s] ,  PreviousCellVal [%s] ,  CurrentCellVal [%s]",
//                            currentRow.getCell(0), previousCellVal, currentCellVal));

                    if(previousCellVal != null && !previousCellVal.toString().trim().equals(currentCellVal.trim())) {

                        LOG.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                        CellStyle style = workbook.createCellStyle();
                        style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        currentCell.setCellStyle(style);
                    }

                    currentCell.setCellValue(currentCellVal);
                }
            }

            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(targetFile);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException ex) {
            LOG.error("Error Inserting New Column  " + targetFile, ex);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

//    private void createNewWorkBook(String targetFile, List<MFInfo> allFetchedData, ExcelProp excelProp, boolean isETF, List<String> attributes) {
//
//        try {
//
//            XSSFWorkbook workbook = new XSSFWorkbook();
//
//            XSSFSheet sheet = null;
//            if(excelProp.getSheetName() == null)
//                sheet = workbook.createSheet(rankYear);
//            else
//                sheet = workbook.createSheet(excelProp.getSheetName());
//
//            Row firstRow = sheet.createRow(0);
//            Cell firstRowSecondCell = firstRow.createCell(1);
//            firstRowSecondCell.setCellValue(rankDate);
//
//            LOG.info("firstRowSecondCell " + firstRowSecondCell);
//
//            if(isETF) {
//                for(int rowIndex=1; rowIndex<=allFetchedData.size(); rowIndex++){
//
//                    Row currentRow     = sheet.createRow((rowIndex));
//                    Cell symbolCell    = currentRow.createCell(0);
//                    Cell rankCell      = currentRow.createCell(1);
//
//                    RankInfo currentRankInfo = allFetchedData.get(rowIndex - 1);
//
//                    symbolCell.setCellValue(currentRankInfo.getSymbol());
//                    LOG.info("Symbol : " + currentRankInfo.getSymbol());
//
//                    rankCell.setCellValue(currentRankInfo.getETFInfo(attributes));
//                }
//            } else {
//
//                for(int rowIndex=1; rowIndex<=allFetchedData.size(); rowIndex++){
//
//                    Row currentRow     = sheet.createRow((rowIndex));
//                    Cell symbolCell    = currentRow.createCell(0);
//                    Cell rankCell      = currentRow.createCell(1);
//
//                    RankInfo currentRankInfo = allFetchedData.get(rowIndex - 1);
//
//                    symbolCell.setCellValue(currentRankInfo.getSymbol());
//                    LOG.info("Symbol : " + currentRankInfo.getSymbol());
//
//                    rankCell.setCellValue(getRankStrByRankInfo(currentRankInfo));
//                }
//            }
//
//
//            FileOutputStream outputStream = new FileOutputStream(targetFile);
//            workbook.write(outputStream);
//            workbook.close();
//            outputStream.close();
//
//        } catch (IOException | EncryptedDocumentException ex) {
//            LOG.error("Error while Creating New Sheet  " + targetFile, ex);
//        }
//    }

    /**
     * Fetches the rank from column "Rank Data
     * @param rankStr as "Rank (Price)"
     * @return "Rank" as number
     */
    public String getRankNumByStr(String rankStr) {

        return getRankNum(rankStr.split(Constants.SEPARATOR_WHITE_SPACE)[0]);
    }

    private String getRankNum(String rankStr) {

        switch(rankStr.trim()) {

            case "StrongBuy"  : return "1";
            case "Strong Buy" : return "1";
            case "Buy"        : return "2";
            case "Hold"       : return "3";
            case "Sell"       : return "4";
            case "StrongSell" : return "5";
            default           : return Constants.ZERO_VALUE;
        }
    }


//    private void insertNewSheet(String targetFile, List<RankInfo> allFetchedData, ExcelProp excelProp, boolean isETF, List<String> attributes) {
//
//        try {
//
//            FileInputStream inputStream = new FileInputStream(new File(targetFile));
//            Workbook workbook = WorkbookFactory.create(inputStream);
//
//            Sheet sheet = workbook.createSheet(excelProp.getSheetName());
//
//            Row firstRow   = sheet.createRow(0);
//            Cell firstRowSecondCell = firstRow.createCell(1);
//            firstRowSecondCell.setCellValue(rankDate);
//
//            if(isETF) {
//                for(int rowIndex=1; rowIndex<=allFetchedData.size(); rowIndex++) {
//
//                    Row currentRow           = sheet.createRow(rowIndex);
//                    Cell symbolCell          = currentRow.createCell(0);
//                    Cell rankCell            = currentRow.createCell(1);
//                    RankInfo currentRankInfo = allFetchedData.get(rowIndex - 1);
//
//                    LOG.info(String.format("For Symbol [%s] , CurrentCellStr [%s] ", currentRankInfo.getSymbol(), currentRankInfo.getETFInfo(attributes)));
//
//                    symbolCell.setCellValue(currentRankInfo.getSymbol());
//                    rankCell.setCellValue(currentRankInfo.getETFInfo(attributes));
//                }
//            } else {
//
//                for(int rowIndex=1; rowIndex<=allFetchedData.size(); rowIndex++) {
//
//                    Row currentRow           = sheet.createRow(rowIndex);
//                    Cell symbolCell          = currentRow.createCell(0);
//                    Cell rankCell            = currentRow.createCell(1);
//                    RankInfo currentRankInfo = allFetchedData.get(rowIndex - 1);
//
//                    LOG.info(String.format("For Symbol [%s] , CurrentCellStr [%s] ", currentRankInfo.getSymbol(), currentRankInfo.getRank()));
//
//                    symbolCell.setCellValue(currentRankInfo.getSymbol());
//                    rankCell.setCellValue(getRankStrByRankInfo(currentRankInfo));
//                }
//            }
//
//            inputStream.close();
//
//            FileOutputStream outputStream = new FileOutputStream(targetFile);
//            workbook.write(outputStream);
//            workbook.close();
//            outputStream.close();
//
//        } catch (IOException | EncryptedDocumentException ex) {
//            LOG.error("Error Inserting New Column  " + targetFile, ex);
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        }
//    }
}
