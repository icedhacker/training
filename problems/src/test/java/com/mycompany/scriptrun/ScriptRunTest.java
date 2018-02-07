package com.mycompany.scriptrun;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ScriptRunTest {

    private ScriptRun scriptRun;

    @Before
    public void setup() {
        Map<Integer, VulnerabilityScript> scriptDb = createSampleScriptDb();
        scriptRun = new ScriptRun(scriptDb);
    }

    @Test
    public void shouldReturnOneScriptIfNoDependency() {
        List<Integer> actualRunOrder = scriptRun.getRunOrder(5);
        assertThat(actualRunOrder).isNotEmpty();
        assertThat(actualRunOrder.size()).isEqualTo(1);
        assertThat(actualRunOrder.get(0)).isEqualTo(5);
    }

    @Test
    public void shouldReturnOrderedScriptAsPerDependencies() {
        List<Integer> actualRunOrder = scriptRun.getRunOrder(3);
        assertThat(actualRunOrder).isNotEmpty();
        assertThat(actualRunOrder.size()).isEqualTo(2);
        List<Integer> expectedRunOrder = Arrays.asList(5, 3);
        assertThat(actualRunOrder).isEqualTo(expectedRunOrder);
    }

    @Test
    public void shouldReturnOrderedScriptAsPerDependencies_Root() {
        List<Integer> actualRunOrder = scriptRun.getRunOrder(1);
        assertThat(actualRunOrder).isNotEmpty();
        assertThat(actualRunOrder.size()).isEqualTo(6);
        List<Integer> expectedRunOrder = Arrays.asList(6, 4, 5, 2, 3, 1);
        assertThat(actualRunOrder).isEqualTo(expectedRunOrder);
    }

    // Create a Sample Script Dependency as following :
    //              1
    //          2       3
    //      4       5
    //  6
    // 1 depends on 2 & 3
    // 2 depends on 4 & 5
    // 3 depends on 5
    // 4 depends on 6
    // 5 & 6 have no dependency
    private Map<Integer, VulnerabilityScript> createSampleScriptDb() {
        VulnerabilityScript vulnerabilityScript5 = new VulnerabilityScript(5, new ArrayList<>());
        VulnerabilityScript vulnerabilityScript6 = new VulnerabilityScript(6, new ArrayList<>());

        List<Integer> dependency4 = new ArrayList<>();
        dependency4.add(6);
        VulnerabilityScript vulnerabilityScript4 = new VulnerabilityScript(4, dependency4);

        List<Integer> dependency3 = new ArrayList<>();
        dependency3.add(5);
        VulnerabilityScript vulnerabilityScript3 = new VulnerabilityScript(3, dependency3);

        List<Integer> dependency2 = new ArrayList<>();
        dependency2.add(4);
        dependency2.add(5);
        VulnerabilityScript vulnerabilityScript2 = new VulnerabilityScript(2, dependency2);

        List<Integer> dependency1 = new ArrayList<>();
        dependency1.add(2);
        dependency1.add(3);
        VulnerabilityScript vulnerabilityScript1 = new VulnerabilityScript(1, dependency1);

        Map<Integer, VulnerabilityScript> scriptMap = new HashMap<>();
        scriptMap.put(1, vulnerabilityScript1);
        scriptMap.put(2, vulnerabilityScript2);
        scriptMap.put(3, vulnerabilityScript3);
        scriptMap.put(4, vulnerabilityScript4);
        scriptMap.put(5, vulnerabilityScript5);
        scriptMap.put(6, vulnerabilityScript6);

        return scriptMap;
    }
}
