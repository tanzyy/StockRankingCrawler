package VO;

/**
 * Created by i852841 on 6/2/18.
 */
public class ExcelProp {

    String  workBookName;
    String  sheetName;
    Integer sheetIndex;
    Integer columnIndex;
    String  fileLoc;
    String  backFileLoc;

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

    public String getFileLoc() {
        return fileLoc;
    }

    public void setFileLoc(String fileLoc) {
        this.fileLoc = fileLoc;
    }

    public String getBackFileLoc() {
        return backFileLoc;
    }

    public void setBackFileLoc(String backFileLoc) {
        this.backFileLoc = backFileLoc;
    }

    @Override
    public String toString() {
        return "ExcelProp{" +
                "workBookName='" + workBookName + '\'' +
                ", sheetName='" + sheetName + '\'' +
                ", sheetIndex=" + sheetIndex +
                ", columnIndex=" + columnIndex +
                ", fileLoc='" + fileLoc + '\'' +
                ", backFileLoc='" + backFileLoc + '\'' +
                '}';
    }
}
