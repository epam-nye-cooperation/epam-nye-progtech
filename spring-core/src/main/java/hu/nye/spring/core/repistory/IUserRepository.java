package hu.nye.spring.core.repistory;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.nye.spring.core.entity.UserEntity;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, Long> {

    List<UserEntity> findAllByAge(int age);

}
