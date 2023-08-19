package src;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

public class PlaneCSVParser {
    private static final CSVFormat FORMAT = CSVFormat.RFC4180.withDelimiter(',').withHeader();

    public PlaneCSVParser() {}

    public void parseData(String file, Function<Map<String, String>,
            Void> handleLoc) throws IOException {
        CSVParser parser = new CSVParser(new FileReader(file), FORMAT);

        for (CSVRecord record : parser) {
            handleLoc.apply(record.toMap());
        }
    }
}
