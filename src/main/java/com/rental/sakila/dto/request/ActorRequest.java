package com.rental.sakila.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.util.Optional;

@Getter
public class ActorRequest
{
    @NotNull(groups = {ActorValidation.Update.class, ActorValidation.Delete.class})
    public Short id;

    /* Regex would require more consideration */
    @Min(1)
    @Max(40)
    @NotNull(groups = ActorValidation.Create.class)
    public String firstName;

    @Min(1)
    @Max(40)
    @NotNull(groups = ActorValidation.Create.class)
    public String lastName;
}
