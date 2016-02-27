package harbor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Section {
	
	private List<Container> storage;
	private LocalDate closestPickupDate;
	private int currentHeight = 0;
	private int height;
	
	public Section(int height) {
		storage = new ArrayList<Container>();
		this.height = height;
	}
	
	public boolean addContainer(Container container) {
		if(hasPlace()) {
			if(storage.isEmpty() || (closestPickupDate.isAfter(container.getPickupDate()) 
						|| closestPickupDate.isEqual(container.getPickupDate())) ) {
				add(container);
				return true;
			}
		}
		return false;
	}
	
	public boolean dispatchContainer(LocalDate date) {
		if(!storage.isEmpty() && (closestPickupDate.isBefore(date) || closestPickupDate.isEqual(date))) {
			storage.remove(currentHeight - 1);
			currentHeight--;
			return true;
		}
		return false;
	}
	
	private void add(Container container) {
		storage.add(container);
		closestPickupDate = container.getPickupDate();
		currentHeight++;

	}
	
	public boolean hasPlace() {
		return height > currentHeight;
	}

	public LocalDate getClosestPickupDate() {
		return closestPickupDate;
	}

	public void setClosestPickupDate(LocalDate closestPickupDate) {
		this.closestPickupDate = closestPickupDate;
	}

	public int getCurrentHeight() {
		return currentHeight;
	}

	public void setCurrentHeight(int currentHeight) {
		this.currentHeight = currentHeight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public List<Container> getContainers() {
		return storage;
	}

	public int getContainersQty() {
		return storage.size();
	}

	public List<Container> getStorage() {
		return storage;
	}

	public void setStorage(List<Container> storage) {
		this.storage = storage;
	}
	
	
}
