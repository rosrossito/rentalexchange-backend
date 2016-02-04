package com.upteam.auth.integrationtest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by olegls2000 on 12/23/2015.
 */
@Ignore
public class AuthIntegrationTest {

    private static IDatabaseTester tester = null;

    @BeforeClass
    public static void setUpClass() throws Exception {
        tester = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/rental?useUnicode=true&characterSet=UTF-8", "postgres", "qa1234");
        //tester = new PropertiesBasedJdbcDatabaseTester();
        tester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        tester.setTearDownOperation(DatabaseOperation.NONE);
    }
// DEV TEST
    @Before("")
    public void setUp() throws Exception {
        // загружаем набор с тестовыми данными
        IDataSet dataSet = new FlatXmlDataSet(new InputStreamReader(new FileInputStream("datasets/scenario_dbUnit.xml"), "utf-8"));
        tester.setDataSet(dataSet);
        tester.onSetup();
    }

    @After("")
    public void tearDown() throws Exception {
        tester.onTearDown();
    }

    @Test
    public void testSelest1() throws Exception {
        //получаем ссылку на соединение с БД
        Connection con = tester.getConnection().getConnection();
//        String schema = "rental";
//        DatabaseConnection databaseConnection = new DatabaseConnection(con, schema);
        // выполняем запрос на поиск некоторой записи
        ResultSet rs = con.createStatement().executeQuery("select u from systemuser u where u.email = ruslan.omelchenko.irtcits@gmail.com");
        // проверяем, что запись была найдена
        Assert.assertTrue(rs.next());
        Assert.assertEquals(rs.getString("LOGIN"), "Omelchenko");
    }

    @Test
    public void testSelest2() throws Exception {
        //получаем ссылку на соединение с БД
        Connection con = tester.getConnection().getConnection();
        // выполняем запрос на поиск некоторой записи
        ResultSet rs = con.createStatement().executeQuery("select c from activationlink c where c.systemuser_id = 1");
        // проверяем, что запись была найдена
        Assert.assertTrue(rs.next());
        Assert.assertEquals(rs.getString("ID"), "1");
    }

    @Test
    public void testSelest3() throws Exception {
        //получаем ссылку на соединение с БД
        Connection con = tester.getConnection().getConnection();
        // выполняем запрос на поиск некоторой записи
        ResultSet rs = con.createStatement().executeQuery("select u from activationlink u where u.uuid = AnyString1");
        // проверяем, что запись была найдена
        Assert.assertTrue(rs.next());
        Assert.assertEquals(rs.getString("ID"), "1");
    }
}


