package com.example.totolist.ultis;

import com.example.totolist.model.Todo;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
public class ExcelReader implements IFileReader {

    @Override
    public List<Todo> readFile(String filePath) {
        List<Todo> todoList = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Cell idCell = row.getCell(0);
                Cell titleCell = row.getCell(1);
                Cell statusCell = row.getCell(2);

                int id;
                String title;
                boolean status;

                if (idCell.getCellType() == CellType.NUMERIC) {
                    id = (int) idCell.getNumericCellValue();
                } else {
                    continue;
                }

                if (titleCell.getCellType() == CellType.STRING) {
                    title = titleCell.getStringCellValue();
                } else {
                    continue;
                }

                if (statusCell.getCellType() == CellType.BOOLEAN) {
                    status = statusCell.getBooleanCellValue();
                } else {
                    continue;
                }

                Todo todo = new Todo(id, title, status);
                todoList.add(todo);
            }
        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
        return todoList;
    }

}
