package com.CestaSolidaria.domain.user.dto;

import com.CestaSolidaria.domain.user.User;

public record DataDeteilsUser(String nome,
							  String Status) {

    public DataDeteilsUser(User user) {
        this(user.getNome(), user.getStatus().name());
    }
}
