package deus.lava.setup;

import deus.lava.Lava;
import org.luaj.vm2.Globals;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class EnvironmentManager {
	private static final HashMap<String, Globals> user_environments = new HashMap<>();
	private static volatile Globals currentEnvironment = null;

	public static void createUserEnvironment(String name, Object caller) {
		if (!user_environments.containsKey(name)) {
			user_environments.put(name, LuaSandbox.createUserGlobals(caller));
			Lava.LOGGER.info("The {} Environment exists", name);

		} else {
			Lava.LOGGER.info("The {} Environment has been created", name);
		}
	}

	public static void setCurrentEnvironment(String name) {
		currentEnvironment = user_environments.get(name);
	}

	public static synchronized boolean removeEnvironment(String name) {
		if (!user_environments.containsKey(name)) {
			return false;
		}
		user_environments.remove(name);
		currentEnvironment = null;

		if (user_environments.containsKey(name)) return false;
		if (currentEnvironment!=null) return false;

		Lava.LOGGER.info("Deleted environment: {}", name);

		System.gc();
		return true;
	}


	public static Globals getCurrentEnvironment() {
		return currentEnvironment;
	}


	public static Globals getEnvironment(String name) {
		return user_environments.get(name);
	}

	public static Set<String> getEnvironmentsNames() {
		return user_environments.keySet();
	}

}
