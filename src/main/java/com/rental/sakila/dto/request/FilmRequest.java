package com.rental.sakila.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FilmRequest
{
    @NotNull(groups = {RequestValidation.Update.class, RequestValidation.Delete.class})
    public Short id;

    @NotNull(groups = RequestValidation.Create.class)
    private String title;
}
