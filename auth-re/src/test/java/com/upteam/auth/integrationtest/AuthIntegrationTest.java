package com.upteam.auth.integrationtest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.upteam.auth.ITestDBConfig;
import com.upteam.auth.config.PersistanceConfig;
import com.upteam.auth.config.RepositoryConfig;
import com.upteam.auth.domain.SystemUser;
import com.upteam.auth.repository.SystemUserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by olegls2000 on 12/23/2015 and added by ros on 02/10/16.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITestDBConfig.class, RepositoryConfig.class, PersistanceConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:datasets/datasetSystemUser.xml")
public class AuthIntegrationTest {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Test
    public void testExample() {
        SystemUser searchResults = systemUserRepository.searchByEmail("");
        assertThat(searchResults).isEqualTo(null);
    }
}


