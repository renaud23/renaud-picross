package hama.view;

import java.util.ArrayList;
import java.util.List;

public class Store {

	private static List<StoreObserver> observers = new ArrayList<>();

	public static void dispatch(Action action) {
		observers.forEach(o -> o.notify(action));
	}

	public static void addObserver(StoreObserver observer) {
		observers.add(observer);
	}

}
