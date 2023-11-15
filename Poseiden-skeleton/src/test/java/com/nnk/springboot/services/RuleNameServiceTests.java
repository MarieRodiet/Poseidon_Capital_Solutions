package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
public class RuleNameServiceTests {

    @InjectMocks
    private RuleNameService ruleNameService;

    @Mock
    private RuleNameRepository ruleNameRepository;

    @Test
    public void findAllRuleNamesTest() {
        // GIVEN
        RuleName ruleName1 = createRuleName(1);
        RuleName ruleName2 = createRuleName(2);
        List<RuleName> expectedRuleNames = Arrays.asList(ruleName1, ruleName2);
        when(ruleNameRepository.findAll()).thenReturn(expectedRuleNames);

        // WHEN
        List<RuleName> actualRuleNames = ruleNameService.findAll();

        // THEN
        assertThat(actualRuleNames).isEqualTo(expectedRuleNames);
    }

    @Test
    public void saveRuleNameTest() {
        // GIVEN
        RuleName ruleName = createRuleName(1);

        // WHEN
        ruleNameService.save(ruleName);

        // THEN
        verify(ruleNameRepository, times(1)).save(ruleName);
    }

    @Test
    public void deleteByIdRuleNameTest() {
        // GIVEN
        int ruleNameId = 1;

        // WHEN
        ruleNameService.deleteById(ruleNameId);

        // THEN
        verify(ruleNameRepository, times(1)).deleteById(ruleNameId);
    }

    @Test
    public void findByIdRuleNameTest() {
        // GIVEN
        int ruleNameId = 1;
        RuleName expectedRuleName = createRuleName(ruleNameId);
        when(ruleNameRepository.findById(ruleNameId)).thenReturn(Optional.of(expectedRuleName));

        // WHEN
        RuleName actualRuleName = ruleNameService.findById(ruleNameId);

        // THEN
        assertThat(actualRuleName).isEqualTo(expectedRuleName);
    }

    @Test
    public void findByIdRuleNameNotFoundTest() {
        // GIVEN
        int ruleNameId = 1;
        when(ruleNameRepository.findById(ruleNameId)).thenReturn(Optional.empty());

        // WHEN
        RuleName actualRuleName = ruleNameService.findById(ruleNameId);

        // THEN
        assertThat(actualRuleName).isNull();
    }

    private RuleName createRuleName(int id) {
        RuleName ruleName = new RuleName();
        ruleName.setId(id);
        // Set other ruleName properties as needed
        return ruleName;
    }
}

