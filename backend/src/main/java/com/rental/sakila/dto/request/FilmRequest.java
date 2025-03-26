package com.rental.sakila.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class FilmRequest
{
    @NotNull(groups = {RequestValidation.Update.class, RequestValidation.Delete.class})
    public Short id;

    @NotNull(groups = RequestValidation.Create.class)
    private String title;

    private String description;

    @PositiveOrZero(groups = {RequestValidation.Create.class, RequestValidation.Update.class})
    @Nullable
    private Float price;
}
