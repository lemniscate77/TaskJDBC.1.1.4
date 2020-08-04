package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/*  версия 1.0 - добавляем в новый репозиторий
коммитим этот проект на lemniscate77 git  */

public class Main {
    public static void main(String[] args) throws SQLException,
            ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {

        //Util util = new Util();
        // util.setConnection();
//        UserService userService = new UserServiceImpl();
//        userService.createUsersTable();
//        //userService.dropUsersTable();
//        //userService.cleanUsersTable();
//
//        userService.saveUser("Arny", "Schwartz", (byte) 65);
//        userService.saveUser("Jhony", "Depp", (byte) 45);
//        userService.saveUser("Kianu ", "Rivs", (byte) 47);
//        userService.getAllUsers();
//        userService.removeUserById(3);

        UserDaoHibernateImpl userServiceHbm =  new UserDaoHibernateImpl();
        userServiceHbm.dropUsersTable();
        userServiceHbm.createUsersTable();
        userServiceHbm.dropUsersTable();
        userServiceHbm.saveUser("Arny", "Schwartz", (byte) 65);
        userServiceHbm.saveUser("Jhony", "Depp", (byte) 45);
        userServiceHbm.saveUser("Kianu ", "Rivs", (byte) 47);
        userServiceHbm.getAllUsers();
        userServiceHbm.removeUserById(2);
        userServiceHbm.cleanUsersTable();
        userServiceHbm.dropUsersTable();
        Util.shutdown();
        System.out.println("КОНЕЦ РАБОТЫ ");

    }
}
