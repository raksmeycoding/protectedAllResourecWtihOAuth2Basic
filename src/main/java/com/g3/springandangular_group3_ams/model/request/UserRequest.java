package com.g3.springandangular_group3_ams.model.request;


import com.g3.springandangular_group3_ams.model.entity.UserApp;
import com.g3.springandangular_group3_ams.model.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private  String name;
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public UserApp toEntity(){
        return new UserApp(this.name,this.role.toString());
    }

    public UserApp toEntityById(UUID id){
        return new UserApp(id,this.name,this.role.toString());
    }

}
