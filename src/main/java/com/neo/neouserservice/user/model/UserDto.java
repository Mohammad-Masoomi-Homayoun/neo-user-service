package com.neo.neouserservice.user.model;

import com.neo.neouserservice.common.model.ID;
import lombok.Data;
import java.io.Serializable;

@Data
public class UserDto  implements Serializable{

    private ID id;

    private String firstName;

}
