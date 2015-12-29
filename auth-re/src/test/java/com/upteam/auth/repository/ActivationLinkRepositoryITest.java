package com.upteam.auth.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.upteam.auth.ApplicationRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * Created by olegls2000 on 12/23/2015.
 */
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationRepoTest.class)
@DatabaseSetup(ActivationLinkRepositoryITest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { ActivationLinkRepositoryITest.DATASET })
@DirtiesContext
public class ActivationLinkRepositoryITest {
    protected static final String DATASET = "classpath:datasets/activationLink-set.xml";
    private static final String FIRST_ITEM = "Item 1";
    private static final String THIRD_ITEM = "Item 3";
    private static final String DESCRIPTION_FIELD = "description";

    @Autowired
    private ActivationLinkRepository repository;

    @Test
    public void findCheckedShouldReturnTwoItems() {
        assertThat(repository.getLinkByUUID(""));
    }
}
