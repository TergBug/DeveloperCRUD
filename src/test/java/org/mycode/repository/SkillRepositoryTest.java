package org.mycode.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mycode.BaseIT;
import org.mycode.exceptions.NoSuchEntryException;
import org.mycode.model.Skill;
import org.mycode.testutil.TestedEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

public class SkillRepositoryTest extends BaseIT {
    private static final Logger LOG = Logger.getLogger(SkillRepositoryTest.class);
    private static final String HQL_QUERY_GET_BY_ID = "select distinct s from Skill s " +
            "where s.id=unhex(replace('?', '-', ''))";
    @Autowired
    private SkillRepository sut;
    @Autowired
    private TestedEntities util;

    @Test
    @Transactional
    @Rollback
    public void shouldCreate() {
        sut.create(util.getEntity(Skill.class, TestedEntities.ENTITY_TO_CREATE));
        assertNotNull(sessionFactory.getCurrentSession().get(Skill.class,
                util.getEntity(Skill.class, TestedEntities.ENTITY_TO_CREATE).getId()));
        LOG.debug("Create");
    }

    @Test
    @Transactional
    @Rollback
    public void shouldGetById() {
        try {
            assertEquals(util.getEntity(Skill.class, TestedEntities.ENTITY_TO_GET),
                    sut.getById(util.getEntity(Skill.class, TestedEntities.ENTITY_TO_GET).getId()));
            LOG.debug("Read");
        } catch (NoSuchEntryException e) {
            fail("Test is failed");
        }
    }

    @Test
    @Transactional
    @Rollback
    public void shouldUpdate() {
        try {
            sut.update(util.getEntity(Skill.class, TestedEntities.ENTITY_TO_UPDATE));
            assertEquals(util.getEntity(Skill.class, TestedEntities.ENTITY_TO_UPDATE),
                    sessionFactory.getCurrentSession().get(Skill.class,
                            util.getEntity(Skill.class, TestedEntities.ENTITY_TO_UPDATE).getId()));
            LOG.debug("Update");
        } catch (NoSuchEntryException e) {
            fail("Test is failed");
        }
    }

    @Test
    @Transactional
    @Rollback
    public void shouldDelete() {
        try {
            sut.delete(util.getEntity(Skill.class, TestedEntities.ENTITY_TO_DELETE).getId());
            assertEquals(0, sessionFactory.getCurrentSession()
                    .createQuery(HQL_QUERY_GET_BY_ID.replace("?",
                            util.getEntity(Skill.class, TestedEntities.ENTITY_TO_DELETE).getId().toString()),
                            Skill.class)
                    .list().size());
            LOG.debug("Delete");
        } catch (NoSuchEntryException e) {
            fail("Test is failed");
        }
    }

    @Test
    @Transactional
    @Rollback
    public void shouldGetAll() {
        assertEquals(util.getEntityList(Skill.class), sut.getAll());
        LOG.debug("GetAll");
    }
}