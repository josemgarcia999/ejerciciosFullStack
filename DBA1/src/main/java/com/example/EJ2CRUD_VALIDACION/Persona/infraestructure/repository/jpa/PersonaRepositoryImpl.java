package com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.repository.jpa;

import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.ControladorPersona.*;


public class PersonaRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;


    public List<PersonaEntity> getData(HashMap<String, Object> conditions)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PersonaEntity> query= cb.createQuery(PersonaEntity.class);
        Root<PersonaEntity> root = query.from(PersonaEntity.class);


        String ordenar = "";
        ordenar= String.valueOf(conditions.get("ordenar"));

        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach((field,value) ->
        {
            switch (field)
            {
                case "usuario":
                    predicates.add(cb.equal(root.get(field), (String)value));
                    break;
                case "name":
                    predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                    break;
                case "surname":
                    predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                    break;
                case "created_date":
                    String dateCondition=(String) conditions.get("dateCondition");
                    switch (dateCondition)
                    {
                        case GREATER_THAN:
                            predicates.add(cb.greaterThan(root.<Date>get(field),(Date)value));
                            break;
                        case LESS_THAN:
                            predicates.add(cb.lessThan(root.<Date>get(field),(Date)value));
                            break;
                        case EQUAL:
                            predicates.add(cb.equal(root.<Date>get(field),(Date)value));
                            break;
                    }
                    break;
            }

        });
        if(ordenar==null){
            query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        }else{
            if(ordenar.equalsIgnoreCase("usuario") || ordenar.equalsIgnoreCase("name")){
                query.select(root).where(predicates.toArray(new Predicate[predicates.size()])).orderBy(cb.asc(root.get(ordenar)));
            }else{
                query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
            }
        }

        return entityManager.createQuery(query).getResultList();
    }



}
