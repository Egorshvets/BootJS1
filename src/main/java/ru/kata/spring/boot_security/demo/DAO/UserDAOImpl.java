package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.Model.Role;
import ru.kata.spring.boot_security.demo.Model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }


    @Override
    public void addUser(String userName, String email, int age, String password) {
        entityManager.persist(new User(userName, email, age, password));
    }

    @Override
    public User getUserById(int Id) {
        return entityManager.find(User.class, Id);
    }

    @Override
    public void deleteUserById(int Id) {
        entityManager.createQuery("DELETE User WHERE id =" + Id).executeUpdate();
    }

    @Override
    public void updateUser(int Id, String userName, String email, int age, String role, String password) {
        User user = entityManager.find(User.class, Id);
        user.setUserName(userName);
        user.setAge(age);
        user.setEmail(email);
        user.setRoles(getRoleByName(role));
        user.setPassword(password);
        entityManager.merge(user);

    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        User user = (User) entityManager.createQuery("from User where user_name='" + username + "'").getResultList().get(0);
        for(GrantedAuthority role : user.getAuthorities()) {
            role.getAuthority();
        }
        return user;
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String role) {
        Role role1 = (Role) entityManager.createQuery("from Role where role='" + role + "'").getResultList().get(0);
        return role1;
    }
}