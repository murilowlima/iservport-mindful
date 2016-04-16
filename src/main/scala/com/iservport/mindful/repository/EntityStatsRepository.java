package com.iservport.mindful.repository;

import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.core.internal.SimpleCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Repositório de estatísticas de entidades.
 * 
 * @author mauriciofernandesdecastro
 */
public interface EntityStatsRepository
	extends JpaRepository<Entity, Integer>
{
	
	// contagem por qualificador
	//
	
	/**
	 * Lista contagem de entidades ativas  por tipo.
	 * 
	 * @param contextId
	 */
	@Query("select new " +
			"org.helianto.core.internal.SimpleCounter"
			+ "(entity.entityType, count(entity)) "
			+ "from Entity entity "
			+ "where entity.operator.id = ?1 "
			+ "and entity.activityState = 'A' "
			+ "group by entity.entityType ")
	List<SimpleCounter> countActiveEntitiesGroupByType(int contextId);

}