package com.upteam.auth.integrationtest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.upteam.auth.config.PersistanceConfig;
import com.upteam.auth.config.RepositoryConfig;
import com.upteam.auth.domain.SystemUser;
import com.upteam.auth.repository.SystemUserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by olegls2000 on 12/23/2015 and added by ros on 02/10/16.
 */
@Ignore
@EnableJpaRepositories
@EnableConfigurationProperties()
//@EntityScan({com.upteam.auth.domain"" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RepositoryConfig.class,
        PersistanceConfig.class
}, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("dataset_SystemUser.xml")
public class AuthIntegrationTest {

    @Autowired
    private SystemUserRepository systemUserRepository;
    /*@Autowired
    private TestController myClassUnderTest;
*/
    @Test
    public void test() {
        SystemUser searchResults = systemUserRepository.searchByEmail("");
        assertThat(searchResults).isEqualTo(null);
    }
// DEV TEST
//    @Before("")
//    public void setUp() throws Exception {
//        // загружаем набор с тестовыми данными
//        IDataSet dataSet = new FlatXmlDataSet(new InputStreamReader(new FileInputStream("datasets/scenario_dbUnit.xml"), "utf-8"));
//        tester.setDataSet(dataSet);
//        tester.onSetup();
//    }

   /* @Component
    static class TestController {

        @Autowired
        private SystemUserRepository systemUserRepository;

        public SystemUser searchByEmail() {
            return systemUserRepository.searchByEmail("ruslan.omelchenko.irtcits@gmail.com");
        }
    }
*/

//    st.close();
//    conn.close();
//
//    @Test
//    public void testSelest1() throws Exception {
//        //получаем ссылку на соединение с БД
//        Connection con = tester.getConnection().getConnection();
//        Statement st = con.createStatement();
//        st.executeUpdate("create SCHEMA rental;");
//        con.commit();
//        String schema = "rental";
//        DatabaseConnection databaseConnection = new DatabaseConnection(con, schema);
    // выполняем запрос на поиск некоторой записи
//        ResultSet rs = con.createStatement().executeQuery("select u from rental.systemuser u");
    // проверяем, что запись была найдена
//        while (rs.next()) {
//            System.out.println(rs.getString(1));
//        }
//        Assert.assertTrue(rs.next());
//        Assert.assertEquals(rs.getString(2), "Omelchenko");

//    }
//
//
//    @Test
//    public void testSelest2() throws Exception {
//        //получаем ссылку на соединение с БД
//        Connection con = tester.getConnection().getConnection();
//        // выполняем запрос на поиск некоторой записи
//        ResultSet rs = con.createStatement().executeQuery("select c from activationlink c where c.systemuser_id = 1");
//        // проверяем, что запись была найдена
//        Assert.assertTrue(rs.next());
//        Assert.assertEquals(rs.getString("ID"), "1");
//    }
//

//    @Test
//    public void testSelest3() throws Exception {
//        //получаем ссылку на соединение с БД
//        Connection con = tester.getConnection().getConnection();
//        // выполняем запрос на поиск некоторой записи
//        ResultSet rs = con.createStatement().executeQuery("select u from activationlink u where u.uuid = AnyString1");
//        // проверяем, что запись была найдена
//        Assert.assertTrue(rs.next());
//        Assert.assertEquals(rs.getString("ID"), "1");
//    }

//    public SystemUserRepository getSystemUserRepository() {
//        return systemUserRepository;
//    }
//
//    public void setSystemUserRepository(SystemUserRepository systemUserRepository) {
//        this.systemUserRepository = systemUserRepository;
//    }
}


