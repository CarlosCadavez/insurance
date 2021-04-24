package br.com.csc.insurance.resource;

import br.com.csc.insurance.policy.PolicyService;
import br.com.csc.insurance.policy.dto.PolicyResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/policies")
public class InsurancePolicyResource {


    private final PolicyService policyService;

    @GetMapping
    public List<PolicyResponseDTO> clients() {
        return policyService.policies();
    }

}
