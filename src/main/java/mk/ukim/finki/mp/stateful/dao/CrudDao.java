package mk.ukim.finki.mp.stateful.dao;

import java.util.List;

public interface CrudDao<T> {

	public void insert(T entity);

	public void update(T entity);

	public T getById(int id);

	public void delete(int id);

	public List<T> getAll();
}
