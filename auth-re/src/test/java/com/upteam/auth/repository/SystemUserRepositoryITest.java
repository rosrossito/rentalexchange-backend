package com.upteam.auth.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.upteam.auth.config.PersistanceConfig;
import com.upteam.auth.config.RepositoryConfig;
import com.upteam.auth.domain.ActivationLink;
import com.upteam.auth.domain.SystemUser;
import com.upteam.auth.test.config.ITestDBConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 * Created by olegls2000 on 12/23/2015 and added by ros on 02/10/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITestDBConfig.class, RepositoryConfig.class, PersistanceConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})

@DatabaseSetup(value="classpath:datasets/datasetSystemUser.xml")
public class SystemUserRepositoryITest {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private ActivationLinkRepository activationLinkRepository;

    @Test
    public void test1() {
        SystemUser searchResults = systemUserRepository.searchByEmail("");
        assertThat(searchResults).isEqualTo(null);
        searchResults = systemUserRepository.searchByEmail("XXX");
        assertThat(searchResults).isNotEqualTo(null);
    }

    @Test
    public void test2() {
        ActivationLink searchResults1 = activationLinkRepository.getLinkByUUID("");
        assertThat(searchResults1).isEqualTo(null);
        searchResults1 = activationLinkRepository.getLinkByUUID("XXX");
        assertThat(searchResults1).isNotEqualTo(null);
    }

    @Test
    public void test3() {
        ActivationLink searchResults2 = activationLinkRepository.getLinkBySystemUserID(0l);
        assertThat(searchResults2).isEqualTo(null);
        searchResults2 = activationLinkRepository.getLinkBySystemUserID(11l);
        assertThat(searchResults2).isNotEqualTo(null);
    }
}


