package Utils;

import Financial.IndMFProcessor;
import VO.ExcelProp;
import VO.RankInfo;
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

public class ExcelAction {

    private static final Logger LOG  = Logger.getLogger(ExcelAction.class);

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
    public void writeToOneXL(ExcelProp excelProp, List<RankInfo> allFetchedData, String outLOC, String backLOC, boolean isETF) {

        String fileWithLOC       = outLOC + File.separator + excelProp.getWorkBookName();
        File targetFileHandler   = new File(fileWithLOC);

        String backupFileWithLOC = backLOC + File.separator + CommonUtils.getCurrentTime() + Constants.SEPARATOR_UNDERSCORE + excelProp.getWorkBookName();
        File backupFileHandler   = new File(backupFileWithLOC);

        String tempFileWithLOC   = outLOC + File.separator + Constants.TEMP_PREFIX + excelProp.getWorkBookName();
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
            Workbook workbook = null;
            try {
                inputStream = new FileInputStream(tempFileHandler);
                workbook = WorkbookFactory.create(inputStream);

                //Check if sheet exist
                boolean isSheetExists = isSheetExist(excelProp.getSheetName(), workbook);
                LOG.info(String.format("#####################  Sheet name [%s] existence [%s]", excelProp.getSheetName(), isSheetExists));

                //Shift columns, if sheet exists
                if(isSheetExists) {

                    shiftColumns(tempFileWithLOC, fileWithLOC, excelProp);

                    //Insert New Rank data
                    insertNewRankColumn(fileWithLOC, allFetchedData, excelProp, isETF);

                } else {

                    //Insert New Rank data
                    insertNewSheet(tempFileWithLOC, allFetchedData, excelProp, isETF);
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
            createNewWorkBook(fileWithLOC, allFetchedData, excelProp, isETF);
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

    private void insertNewSheet(String targetFile, List<RankInfo> allFetchedData, ExcelProp excelProp, boolean isETF) {

        try {

            FileInputStream inputStream = new FileInputStream(new File(targetFile));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.createSheet(excelProp.getSheetName());

            Row firstRow   = sheet.createRow(0);
            Cell firstRowSecondCell = firstRow.createCell(1);
            firstRowSecondCell.setCellValue(rankDate);

            if(isETF) {
                for(int rowIndex=1; rowIndex<=allFetchedData.size(); rowIndex++) {

                    Row currentRow           = sheet.createRow(rowIndex);
                    Cell symbolCell          = currentRow.createCell(0);
                    Cell rankCell            = currentRow.createCell(1);
                    RankInfo currentRankInfo = allFetchedData.get(rowIndex - 1);

                    LOG.info(String.format("For Symbol [%s] , CurrentCellStr [%s] ", currentRankInfo.getSymbol(), currentRankInfo.getETFInfo()));

                    symbolCell.setCellValue(currentRankInfo.getSymbol());
                    rankCell.setCellValue(currentRankInfo.getETFInfo());
                }
            } else {

                for(int rowIndex=1; rowIndex<=allFetchedData.size(); rowIndex++) {

                    Row currentRow           = sheet.createRow(rowIndex);
                    Cell symbolCell          = currentRow.createCell(0);
                    Cell rankCell            = currentRow.createCell(1);
                    RankInfo currentRankInfo = allFetchedData.get(rowIndex - 1);

                    LOG.info(String.format("For Symbol [%s] , CurrentCellStr [%s] ", currentRankInfo.getSymbol(), currentRankInfo.getRank()));

                    symbolCell.setCellValue(currentRankInfo.getSymbol());
                    rankCell.setCellValue(getRankStrByRankInfo(currentRankInfo));
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

    private void insertNewRankColumn(String targetFile, List<RankInfo> allFetchedData, ExcelProp excelProp, boolean isETF) {

        try {

            FileInputStream inputStream = new FileInputStream(new File(targetFile));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheet(excelProp.getSheetName());

            //START: Update date
            Row firstRow   = sheet.getRow(0);
            Cell firstRowSecondCell = firstRow.createCell(1);
            firstRowSecondCell.setCellValue(rankDate);
            //END: Update date

            if(isETF) {
                for(int rowIndex=1; rowIndex<=allFetchedData.size(); rowIndex++){

                    Row currentRow            = sheet.getRow(rowIndex);
                    Cell currentCell          = null;
                    RankInfo currentRankInfo  = allFetchedData.get(rowIndex - 1);

                    if(currentRow == null) {
                        currentRow = sheet.createRow(rowIndex);
                        currentCell = currentRow.createCell(1);
                        Cell symbolCell = currentRow.createCell(0);
                        symbolCell.setCellValue(currentRankInfo.getSymbol());
                    } else {
                        currentCell = currentRow.createCell(1);

                        String currentCellRankStr = currentRankInfo.getRank();
                        Integer currentCellRank   = Integer.valueOf(getRankNum(currentCellRankStr));

                        String previousCell        = currentRow.getCell(2).toString();
                        String previousCellRankStr = previousCell.substring(previousCell.indexOf("[") + 1, previousCell.indexOf("]"));
                        Integer previousCellRankVal  = Integer.valueOf(getRankNum(previousCellRankStr));

                        LOG.info(String.format(
                                "For Symbol [%s] ,  PreviousCellStr [%s] ,  PreviousCellVal [%s] , CurrentCellStr [%s] ,  CurrentCellVal [%s]",
                                currentRow.getCell(0), previousCell, previousCellRankVal, currentCellRankStr, currentCellRank));

                        if(currentCellRank != 0 && previousCellRankVal != 0) {

                            if(previousCellRankVal > currentCellRank) {

                                CellStyle style = workbook.createCellStyle();
                                style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                currentCell.setCellStyle(style);

                            } else if(previousCellRankVal < currentCellRank) {

                                CellStyle style = workbook.createCellStyle();
                                style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                                currentCell.setCellStyle(style);
                            }

                        } else if(currentCellRank != 0 && previousCellRankVal == 0) { //New Coverage

                            CellStyle style = workbook.createCellStyle();
                            style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
                            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            currentCell.setCellStyle(style);

                        } else if(currentCellRank == 0 && previousCellRankVal != 0) { //No More Coverage
                            CellStyle style = workbook.createCellStyle();
                            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            currentCell.setCellStyle(style);
                        }
                    }

                    currentCell.setCellValue(currentRankInfo.getETFInfo());
                }
            } else {

                for(int rowIndex=1; rowIndex<=allFetchedData.size(); rowIndex++){

                    Row currentRow            = sheet.getRow(rowIndex);
                    Cell currentCell          = null;
                    RankInfo currentRankInfo  = allFetchedData.get(rowIndex - 1);

                    if(currentRow == null) {
                        currentRow = sheet.createRow(rowIndex);
                        currentCell = currentRow.createCell(1);
                        Cell symbolCell = currentRow.createCell(0);
                        symbolCell.setCellValue(currentRankInfo.getSymbol());
                    } else {

                        currentCell = currentRow.createCell(1);
                        //String currentCellRankStr = currentRankInfo.getRank();
                        //Integer currentCellRank   = Integer.valueOf(getRankNum(currentCellRankStr));

                        String currentCellStr    = getRankStrByRankInfo(currentRankInfo);
                        Integer currentCellVal   = Integer.valueOf(getRankNumByStr(currentCellStr));

                        //String previousCell        = currentRow.getCell(2).toString();
                        //String previousCellRankStr = previousCell.substring(previousCell.indexOf("[") + 1, previousCell.indexOf("]"));
                        //Integer previousCellRankVal  = Integer.valueOf(getRankNum(previousCellRankStr));

                        //LOG.info(String.format(
                        //        "For Symbol [%s] ,  PreviousCellStr [%s] ,  PreviousCellVal [%s] , CurrentCellStr [%s] ,  CurrentCellVal [%s]",
                        //        currentRow.getCell(0), previousCell, previousCellRankVal, currentCellRankStr, currentCellRank));

                        Cell previousCell        = currentRow.getCell(2);
                        Integer previousCellVal  = 0;
                        if(previousCell != null)
                            previousCellVal = Integer.valueOf(getRankNumByStr(previousCell.toString()));

                        LOG.info(String.format(
                                "For Symbol [%s] ,  PreviousCellStr [%s] ,  PreviousCellVal [%s] , CurrentCellStr [%s] ,  CurrentCellVal [%s]",
                                currentRow.getCell(0), previousCell, previousCellVal, currentCellStr, currentCellVal));

                        CellStyle style = workbook.createCellStyle();
                        if(currentCellVal != 0 && previousCellVal != 0) {

                            if(previousCellVal > currentCellVal) {

                                style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                            } else if(previousCellVal < currentCellVal) {

                                style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            }

                        } else if(currentCellVal != 0 && previousCellVal == 0) { //New Coverage

                            style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
                            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                        } else if(currentCellVal == 0 && previousCellVal != 0) { //No More Coverage

                            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        }
                        currentCell.setCellStyle(style);
                        currentCell.setCellValue(currentCellStr);
                    }
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

    private void createNewWorkBook(String targetFile, List<RankInfo> allFetchedData, ExcelProp excelProp, boolean isETF) {

        try {

            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = null;
            if(excelProp.getSheetName() == null)
                sheet = workbook.createSheet(rankYear);
            else
                sheet = workbook.createSheet(excelProp.getSheetName());

            Row firstRow = sheet.createRow(0);
            Cell firstRowSecondCell = firstRow.createCell(1);
            firstRowSecondCell.setCellValue(rankDate);

            LOG.info("firstRowSecondCell " + firstRowSecondCell);

            if(isETF) {
                for(int rowIndex=1; rowIndex<=allFetchedData.size(); rowIndex++){

                    Row currentRow     = sheet.createRow((rowIndex));
                    Cell symbolCell    = currentRow.createCell(0);
                    Cell rankCell      = currentRow.createCell(1);

                    RankInfo currentRankInfo = allFetchedData.get(rowIndex - 1);

                    symbolCell.setCellValue(currentRankInfo.getSymbol());
                    LOG.info("Symbol : " + currentRankInfo.getSymbol());

                    rankCell.setCellValue(currentRankInfo.getETFInfo());
                }
            } else {

                for(int rowIndex=1; rowIndex<=allFetchedData.size(); rowIndex++){

                    Row currentRow     = sheet.createRow((rowIndex));
                    Cell symbolCell    = currentRow.createCell(0);
                    Cell rankCell      = currentRow.createCell(1);

                    RankInfo currentRankInfo = allFetchedData.get(rowIndex - 1);

                    symbolCell.setCellValue(currentRankInfo.getSymbol());
                    LOG.info("Symbol : " + currentRankInfo.getSymbol());

                    rankCell.setCellValue(getRankStrByRankInfo(currentRankInfo));
                }
            }


            FileOutputStream outputStream = new FileOutputStream(targetFile);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException ex) {
            LOG.error("Error while Creating New Sheet  " + targetFile, ex);
        }
    }

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

    /**
     * Builds the rank data column in- Rank (Price)
     * @param rankInfo
     * @return
     */
    public String getRankStrByRankInfo(RankInfo rankInfo) {

        StringBuilder result = new StringBuilder();
        switch(rankInfo.getRank()) {

            case "1" : result.append("StrongBuy ");            break;
            case "2" : result.append("Buy       ");                   break;
            case "3" : result.append("Hold      ");                  break;
            case "4" : result.append("Sell      ");                  break;
            case "5" : result.append("StrongSell");           break;
            default  : result.append(RANK_UNAVAILABLE + "        ");  break;
        }

        return result
                .append(Constants.SEPARATOR_WHITE_SPACE)
                .append(Constants.SEPARATOR_OPEN_BRACE)
                .append(rankInfo.getPrice())
                .append(Constants.SEPARATOR_CLOSE_BRACE)
                .toString();
    }
}
