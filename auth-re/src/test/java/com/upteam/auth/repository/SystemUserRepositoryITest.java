package com.upteam.auth.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.upteam.auth.ApplicationRepoTest;
import com.upteam.auth.domain.SystemUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.*;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
/**
 * Created by Vlad on 17.01.2016.
 */
/*@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationRepoTest.class)
@DatabaseSetup(SystemUserRepositoryITest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = {SystemUserRepositoryITest.DATASET })
@DirtiesContext*/
public class SystemUserRepositoryITest {
    protected static final String DATASET = "classpath:datasets/systemUserRepository.xml";

    private static final String FIRST_ITEM = "firstTestMail@rental.com";
    private static final String THIRD_ITEM = "thirdTestMail@rental.com";
    private static final String DESCRIPTION_FIELD = "description";

  /*  @Autowired
    private SystemUserRepository systemUserRepository;

    @Test
    public void findByEmail() {
        SystemUser systemUser = systemUserRepository.searchByEmail(FIRST_ITEM);
        assertThat(systemUser.getEmail().equals(FIRST_ITEM));
    }*/
}
