package VO;

/**
 * Created by i852841 on 6/2/18.
 */
public class ExcelProp {

    String workBookName;
    String sheetName;
    Integer sheetIndex;
    Integer columnIndex;

    public ExcelProp() {
        sheetIndex  = 0;
        columnIndex = 0;
    }

    public String getWorkBookName() {
        return workBookName;
    }

    public void setWorkBookName(String workBookName) {
        this.workBookName = workBookName;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }
}
