package com.upteam.auth.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.upteam.auth.config.PersistanceConfig;
import com.upteam.auth.config.RepositoryConfig;
import com.upteam.auth.domain.ActivationLink;
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
 * Created by Администратор on 27.02.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITestDBConfig.class, RepositoryConfig.class, PersistanceConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})

@DatabaseSetup(value="classpath:datasets/dataset.xml")
public class ActivationLinkRepositoryITest {

    @Autowired
    private ActivationLinkRepository activationLinkRepository;

    @Test
    public void test1() {
        ActivationLink searchResults1 = activationLinkRepository.getLinkByUUID("");
        assertThat(searchResults1).isEqualTo(null);
        searchResults1 = activationLinkRepository.getLinkByUUID("XXX");
        assertThat(searchResults1).isNotEqualTo(null);
    }

    @Test
    public void test2() {
        ActivationLink searchResults2 = activationLinkRepository.getLinkBySystemUserID(0l);
        assertThat(searchResults2).isEqualTo(null);
        searchResults2 = activationLinkRepository.getLinkBySystemUserID(11l);
        assertThat(searchResults2).isNotEqualTo(null);
    }

}
