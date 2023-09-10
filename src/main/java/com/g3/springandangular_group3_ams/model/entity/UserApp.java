package com.g3.springandangular_group3_ams.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.g3.springandangular_group3_ams.model.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "app_user")
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,length = 50)
    private  String  name;
    @Column(nullable = false,length = 50)
    private String role;


    @ManyToMany
    @JoinTable(
            name = "bookmark",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "article_id", referencedColumnName = "id")
            }
    )
    private List<Article> bookmarks;


    public UserDto toDto (){
        return  new UserDto(this.id,this.name,this.role);
    }

    public UserApp(String name, String role) {
        this.name = name;
        this.role = role;
    }



    public UserApp(UUID id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}
