package com.scool.notifyme.user;



import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class User {

    @Id
    @SequenceGenerator(name="user_sequence",sequenceName = "user_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String alias;

    private boolean enabled;

    public User(String email, String alias) {
        this.email = email;
        this.alias = alias;
        enabled = false;
    }



}
