package org.mycode.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mycode.exceptions.RepoStorageException;
import org.mycode.model.Account;
import org.mycode.model.Developer;
import org.mycode.repository.DeveloperRepository;
import org.mycode.testutil.TestUtils;

import java.util.HashSet;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeveloperServiceTest {
    @InjectMocks
    private static DeveloperService testedDeveloperService;
    @Mock
    private DeveloperRepository currentRepo;
    private Developer createDeveloper = new Developer(5L, "Joe", "Tred", new HashSet<>(), new Account(2L));
    private Developer updateDeveloper = new Developer(5L, "Jony", "Fedorov", new HashSet<>(), new Account(1L));
    @BeforeClass
    public static void connect(){
        TestUtils.switchConfigToTestMode();
        try {
            testedDeveloperService = TestUtils.getApplicationContext().getBean(DeveloperService.class);
        } catch (RepoStorageException e) {
            e.printStackTrace();
        }
    }
    @AfterClass
    public static void backProperty(){
        TestUtils.switchConfigToWorkMode();
    }
    @Test
    public void shouldInvokeCreateInRepo() {
        try {
            testedDeveloperService.create(createDeveloper);
            verify(currentRepo, times(1)).create(createDeveloper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void shouldInvokeGetByIdInRepo() {
        try {
            testedDeveloperService.getById(1L);
            verify(currentRepo, times(1)).getById(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void shouldInvokeUpdateInRepo() {
        try {
            testedDeveloperService.update(updateDeveloper);
            verify(currentRepo, times(1)).update(updateDeveloper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void shouldInvokeDeleteInRepo() {
        try {
            testedDeveloperService.delete(2L);
            verify(currentRepo, times(1)).delete(2L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void shouldInvokeGetAllInRepo() {
        try {
            testedDeveloperService.getAll();
            verify(currentRepo, times(1)).getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}