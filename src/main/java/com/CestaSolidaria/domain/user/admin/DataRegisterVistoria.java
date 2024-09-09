package com.CestaSolidaria.domain.user.admin;

import com.CestaSolidaria.domain.user.enums.Status;

import jakarta.validation.constraints.NotNull;

public record DataRegisterVistoria(@NotNull
								   Long id,
								   @NotNull
								   Status status) {}
