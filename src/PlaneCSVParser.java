package src;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

/**
 * The PlaneCSVParser class takes in a CSV file and uses the apache commons
 * CSV parser to parse the data.
 */
public class PlaneCSVParser {
    private static final CSVFormat FORMAT = CSVFormat.RFC4180.withDelimiter(',').withHeader();

    /**
     * Parses the data in a CSV file using a Function
     *
     * @param file - CSV file name
     * @param handleLoc - Function that handles the data
     * @throws IOException - Error thrown if any issue occurs
     */
    public void parseData(String file, Function<Map<String, String>,
            Void> handleLoc) throws IOException {
        CSVParser parser = new CSVParser(new FileReader(file), FORMAT);

        for (CSVRecord record : parser) {
            handleLoc.apply(record.toMap());
        }
    }
}
