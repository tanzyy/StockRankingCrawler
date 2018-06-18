package Financial;

import Utils.ExcelOpener;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class BaseRatings {

    private static final Logger LOG = Logger.getLogger(BaseRatings.class);

    public void loadProperties() {
        Thread.currentThread().getContextClassLoader().getResource("config.properties");
    }

    public void shiftColumns(String inputFile, String outputFile) {

        int sheetIndex = 0;
        int columnIndex = 1;

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

}
