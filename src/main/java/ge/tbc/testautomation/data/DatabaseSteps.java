package ge.tbc.testautomation.data;

import java.sql.*;

public class DatabaseSteps {


    public static boolean checkIfRowExists(String query) {
        try (Connection connection = MSSQLConnection.connect()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery(query);
            result.last();
            System.out.println(result.getRow() != 0);
            return result.getRow() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static String[] getCredentialsOfUser(String user){
        try (Connection connection = MSSQLConnection.connect()) {
            String SelectSql = """
                    use Registration
                    select * from Users where username = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(SelectSql);
            preparedStatement.setString(1,user);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                return new String[] {username, password};
            } else {
                throw new RuntimeException("Error: User not found!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }
    }
