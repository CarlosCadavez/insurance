package br.com.csc.insurance.resource;

import br.com.csc.insurance.policy.dto.PolicyResponseDTO;
import br.com.csc.insurance.policy.service.PolicyService;
import br.com.csc.insurance.resource.helper.ConvertJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith( MockitoExtension.class )
class InsurancePolicyResourceTest {

    private static final String POLICIES_URI = "/api/v1/policies";
    private static final String A_POLICE_NUMBER = "6f894076-b418-4f86-984e-70111041453d";
    private static final String A_LICENSE_PLATE = "FXR9915";

    @Mock
    private PolicyService policyService;

    @InjectMocks
    private InsurancePolicyResource insurancePolicyResource;

    private MockMvc mockMvc;
    private ConvertJson convertJson;

    @BeforeEach
    void setUp() {
        this.convertJson = new ConvertJson();
        this.mockMvc = MockMvcBuilders.standaloneSetup(insurancePolicyResource)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) ->  new MappingJackson2JsonView())
                .build();
    }

    @Test
    void should_return_a_list_of_policies_with_status_code_ok() throws Exception {
        when(policyService.policies()).thenReturn(buildAListOfPolicies());
        mockMvc.perform(MockMvcRequestBuilders.get(POLICIES_URI)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private List<PolicyResponseDTO> buildAListOfPolicies() {
        return Arrays.asList(
                PolicyResponseDTO.builder()
                        .daysForPayment(0l)
                        .daysOverdue(632l)
                        .amount(BigDecimal.TEN)
                        .licensePlate(A_LICENSE_PLATE)
                        .startPolicyPeriod(LocalDate.of(2017, 8, 01))
                        .endPolicyPeriod(LocalDate.of(2019, 8, 01))
                        .overduePolicy(true)
                        .policyNumber(A_POLICE_NUMBER)
                        .build(),
                PolicyResponseDTO.builder()
                        .daysForPayment(0l)
                        .daysOverdue(632l)
                        .amount(BigDecimal.TEN)
                        .licensePlate(A_LICENSE_PLATE)
                        .startPolicyPeriod(LocalDate.of(2017, 8, 1))
                        .endPolicyPeriod(LocalDate.of(2019, 8, 1))
                        .overduePolicy(true)
                        .policyNumber(A_POLICE_NUMBER)
                        .build()
        );
    }

}