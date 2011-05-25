package org.fsarmiento.invoicing.entities;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.fsarmiento.invoicing.generics.db.GenericDao;

/**
 * The Class AbstractHibernateDaoTest.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public abstract class AbstractHibernateDaoTest<T extends AbstractEntity> {

	public void saveEntity(T entity) {
		assertThat(entity.getId(), nullValue());
		getDao().saveOrUpdate(entity);
		assertThat(entity.getId(), notNullValue());
	}

	public void updateEntity(T entity) {
		Long currentId = entity.getId();
		getDao().saveOrUpdate(entity);
		assertThat(entity.getId(), equalTo(currentId));
	}

	protected abstract GenericDao<T> getDao();
}
