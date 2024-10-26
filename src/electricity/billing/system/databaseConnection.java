package electricity.billing.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class databaseConnection {

    Connection connection;
    Statement statement;
    databaseConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Billing_System", "root", "rachna@2021");
            statement=connection.createStatement();

        }
        catch (Exception e){
            e.printStackTrace();

        }
    }
}

