package edu.festu.ivankuznetsov.securitydemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name="roles")
public class Role {
    @Id
    private String roleName;

    @ManyToMany
    @JoinTable(name = "account_roles",
            joinColumns =@JoinColumn(name = "userId"),inverseJoinColumns = @JoinColumn(name = "roleId"))
    @JsonIgnore
    private List<Account> users;

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setUsers(List<Account> users) {
        this.users = users;
    }
    public Role() {

    }

    public Role(String roleName){
        this.roleName = roleName;
    }
}
