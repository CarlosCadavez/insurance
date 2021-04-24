package br.com.csc.insurance.resource;

import br.com.csc.insurance.client.dto.ClientDTO;
import br.com.csc.insurance.client.dto.ClientResponseDTO;
import br.com.csc.insurance.client.exception.ClientAlreadyExistsException;
import br.com.csc.insurance.policy.dto.PolicyDTO;
import br.com.csc.insurance.policy.service.PolicyService;
import br.com.csc.insurance.policy.dto.PolicyResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/policies")
public class InsurancePolicyResource {


    private final PolicyService policyService;

    @GetMapping
    public List<PolicyResponseDTO> policies() {
        return policyService.policies();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PolicyResponseDTO addPolicy(@Valid @RequestBody PolicyDTO policyDTO) {
        return policyService.addPolicy(policyDTO);
    }

}
