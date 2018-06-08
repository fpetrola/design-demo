package com.almundo.customerprocessor;

import java.sql.SQLException;
import java.util.List;

public interface RowProcessor {
    void processRow(List<String> fields) throws SQLException;
}
