package ge.tbc.testautomation.data;
import org.testng.annotations.DataProvider;

import java.sql.*;

public class DBDataProvider {

//    @DataProvider(name = "registrationDataProvider")
//    public Object[][] getRegistrationData() {
//        try (Connection connection = MSSQLConnection.connect()) {
//
//            ResultSet resultSet = DatabaseSteps.getEveryUserWithAPhone(connection);
//
//            resultSet.last();
//            int rowCount = resultSet.getRow();
//            resultSet.beforeFirst();
//
//            Object[][] data = new Object[rowCount][3];
//
//            int rowIndex = 0;
//            while (resultSet.next()) {
//                data[rowIndex][0] = resultSet.getString("firstName");
//                data[rowIndex][1] = resultSet.getString("lastName");
//                data[rowIndex][2] = resultSet.getString("phoneNumber");
//                rowIndex++;
//            }
//
//            return data;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}