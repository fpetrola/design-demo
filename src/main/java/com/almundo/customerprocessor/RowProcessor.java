package com.almundo.customerprocessor;

import java.sql.SQLException;
import java.util.List;

public interface RowProcessor {
    void processLine(List<String> fields);
}
