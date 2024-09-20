package com.CestaSolidaria.domain.user.dto;

import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.enums.Status;

public record DataDeteilsUser(String nome,
							  Status status) {

    public DataDeteilsUser(User user) {
        this(user.getNome(), user.getStatus());
    }
}
