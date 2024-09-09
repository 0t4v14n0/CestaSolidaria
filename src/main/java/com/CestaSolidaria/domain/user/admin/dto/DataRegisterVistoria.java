package com.CestaSolidaria.domain.user.admin.dto;

import com.CestaSolidaria.domain.user.enums.Status;

import jakarta.validation.constraints.NotNull;

public record DataRegisterVistoria(@NotNull
								   Long id,
								   @NotNull
								   Status status) {}
