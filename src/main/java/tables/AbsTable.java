package tables;

import db.IDBConnector;
import db.MySQLDBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbsTable {

    public IDBConnector idbConnector = new MySQLDBConnector();
    private String name ="";
    public AbsTable(String name){
        this.name = name;
    }

    public void createTable() throws SQLException {
        if (!isTableExist()) {
            String sqlRequest = String.format("CREATE TABLE %s (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) NOT NULL, age INT NOT NULL, weight INT NOT NULL, color VARCHAR(255) NOT NULL, type VARCHAR(255) NOT NULL);", name);
            idbConnector.execute(sqlRequest);
        }
    }

    private boolean isTableExist() throws SQLException {
        String sqlRequest = String.format("SELECT * FROM information_schema.tables WHERE table_name = '%s' LIMIT 1;", name);
        ResultSet resultSet = idbConnector.executeQuery(sqlRequest);
        return resultSet.next();
    }
}
