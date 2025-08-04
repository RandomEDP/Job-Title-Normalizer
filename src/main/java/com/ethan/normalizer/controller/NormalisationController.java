package com.ethan.normalizer.controller;

import com.ethan.normalizer.service.NormalisationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotBlank;


/**
 * REST controller exposing the /api/normalize endpoint.
 * <p>
 * Validates input and delegates to JobTitleNormaliserService.
 * Returns 400 on missing/blank title.
 * </p>
 */
@RestController
@RequestMapping("/api/normalize")
@Validated
public class NormalisationController {
    private final NormalisationService normaliser;

    public NormalisationController(NormalisationService normaliser) {
        this.normaliser = normaliser;
    }

    @GetMapping
    @Operation(
            summary = "Normalize a job title",
            description = "Returns the best match for a given raw job title"
    )
    public NormalisationResponse normalize(
            @Parameter(description = "Raw job title to normalize", required = true)
            @RequestParam("title")
            @NotBlank(message = "title must not be blank")
            String title
    ) {
        String normalized = normaliser.normalise(title);
        return new NormalisationResponse(normalized);
    }
}
