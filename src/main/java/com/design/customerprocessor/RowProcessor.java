package com.design.customerprocessor;

import java.sql.SQLException;
import java.util.List;

public interface RowProcessor {
    void processRow(List<String> fields);
}
