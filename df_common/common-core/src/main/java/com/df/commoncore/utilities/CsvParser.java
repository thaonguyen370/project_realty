package com.df.commoncore.utilities;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvParser<T> {
    private String filePath;
    private char separator = ';';

    public List<T> parse(Class<T> clazz, Map<String, String> columnMapping) throws IOException {
        List<T> records = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(this.filePath));
        ) {

            HeaderColumnNameTranslateMappingStrategy<T> strategy = new HeaderColumnNameTranslateMappingStrategy<>();
            strategy.setType(clazz);
            strategy.setColumnMapping(columnMapping);

            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(this.separator)
                    .build();

            Iterator<T> iterator = csvToBean.iterator();
            iterator.forEachRemaining(records::add);

            return records;
        } catch (IOException ex) {
            throw ex;
        }
    }
}
