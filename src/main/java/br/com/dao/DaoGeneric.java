package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;

public class DaoGeneric<E> {

	private EntityManager entityManager = JPAUtil.getEntityManager();

	public void salvar(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
	}
	
	public void delete(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(entidade);
		transaction.commit();
	}
	

	public E merge(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E retorno = entityManager.merge(entidade);
		transaction.commit();
		return retorno;
	}


	public E updateMerge(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E entidadeSalva = entityManager.merge(entidade);
		transaction.commit();
		return entidadeSalva;
	}

	public void deletarPorId(E entidade) {

		Object id = JPAUtil.getPrimaryKey(entidade);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.createNativeQuery(
				"delete from " + entidade.getClass().getSimpleName().toLowerCase() + " where id=" + id).executeUpdate();
		transaction.commit();
		

	}

	public E pesquisar(Long id, Class<E> entidade) {

		// Object id = JPAUtil.getPrimaryKey(entidade);

		E e = (E) entityManager.find(entidade, id);

		return e;
	}
	
	public List<E> listar(Class<E> entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		List<E> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();
		transaction.commit();
		return lista;
	}
	
	public List<E> getListEntity(Class<E> entidade) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		List<E> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();
		
		entityTransaction.commit();
		
		return lista;
	}

	
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
