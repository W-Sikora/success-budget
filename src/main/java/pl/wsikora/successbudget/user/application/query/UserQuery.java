package pl.wsikora.successbudget.user.application.query;


import pl.wsikora.successbudget.user.domain.User;

import java.util.Optional;

public interface UserQuery {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    User getById(Long id);

}
