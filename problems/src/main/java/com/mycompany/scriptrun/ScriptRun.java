package com.mycompany.scriptrun;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Given a database of scripts. Each script has an arbitrary number of dependencies.
 * The dependencies are expressed as a list of scriptIds that need to be executed before a given script.
 * I want to come up with an execution plan so that I can run all of the scripts in a sane order.
 * <p>
 * Assumption : There are no circular dependencies.
 */
public class ScriptRun {

    private Map<Integer, VulnerabilityScript> scriptsDatabase;

    ScriptRun(Map<Integer, VulnerabilityScript> scriptsDatabase) {
        this.scriptsDatabase = scriptsDatabase;
    }

    /**
     * @param scriptId the script that has to be run
     * @return An ordered {List} of script ids which will be the execution plan for running scriptId
     */
    public List<Integer> getRunOrder(int scriptId) {
        List<Integer> runOrder = new ArrayList<>();
        VulnerabilityScript script = scriptsDatabase.get(scriptId);

        if (script.isExecuted()) {
            return runOrder;
        }

        if (!script.hasDependency()) {
            runOrder.add(script.getScriptId());
            script.setExecuted(true);
            return runOrder;
        }

        for (int dependencyScriptId : script.getDependencies()) {
            runOrder.addAll(getRunOrder(dependencyScriptId));
        }

        runOrder.add(script.getScriptId());
        return runOrder;
    }
}
