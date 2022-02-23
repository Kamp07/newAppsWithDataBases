package dataBaseAppsExperiments;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        System.out.println(3 & 1);
        Ite


        try {
            dataBase.getOrders();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
