package harbor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Harbor {
	
	int sectionDefaultHeight;
	List<Section> warehouse;
	boolean receiveBeforePickup;
	int maxStoredContainers = 0;
	int actualContainers = 0;
	int maxUsedSections = 0;
	int actualSections = 0;
	
	public Harbor(int sectionDefaultHeight, boolean receiveBeforePickup) {
		this.receiveBeforePickup = receiveBeforePickup;
		warehouse = new ArrayList<Section>();
		this.sectionDefaultHeight = sectionDefaultHeight;
		Section section = new Section(sectionDefaultHeight);
		maxUsedSections++;
		warehouse.add(section);
	}
	
	public void receiveContainer(Container container) {
		boolean success = false;
		for(Section section : warehouse) {
			if( success = section.addContainer(container) ) {
				actualContainers++;
				return;
			} 
		}
		if(!success) {
			Section section = new Section(sectionDefaultHeight);
			maxUsedSections++;
			actualContainers++;
			section.addContainer(container);
			warehouse.add(section);
		}
	}
	
	public void dispatchContainers(LocalDate date) {
		for(Section section : warehouse) {
			if(section.dispatchContainer(date)) {
				actualContainers--;
			}
			
		}
	}
	
	public void receiveAllContainers(List<Container> containers) {
		for(Container c : containers) {
			if(receiveBeforePickup) {
				receiveContainer(c);
				dispatchContainers(c.getDateOfArrival());
				calculateMaxStoredContainers();
			} else {
				dispatchContainers(c.getDateOfArrival());
				receiveContainer(c);
				calculateMaxStoredContainers();
			}
		}
	}
	
	public void calculateMaxStoredContainers() {
		if(maxStoredContainers < getContainersQty()) {
			maxStoredContainers = getContainersQty();
		}
	}
	
	public void setSectionDefaultHeight(int height) {
		sectionDefaultHeight = height;
	}
	
	public void printHarborContent() {
		int counter = 1;
		for(Section section : warehouse) {
			System.out.println("Section " + counter);
			for(Container c : section.getContainers()) {
				System.out.println(c);
			}
			System.out.println();
			counter++;
		}
	}
	
	public int getContainersQty() {
		int containersQty = 0;
		for(Section section : warehouse) {
			containersQty += section.getContainersQty();
		}
		return containersQty;
	}

	public int getMaxStoredContainers() {
		return maxStoredContainers;
	}

	public void setMaxStoredContainers(int maxStoredContainers) {
		this.maxStoredContainers = maxStoredContainers;
	}

	public int getActualContainers() {
		return actualContainers;
	}

	public void setActualContainers(int actualContainers) {
		this.actualContainers = actualContainers;
	}

	public int getSectionDefaultHeight() {
		return sectionDefaultHeight;
	}
	
	public int getActualSections() {
		return calculateActualSections();
	}
	
	public int calculateActualSections() {
		for(Section section : warehouse) {
			if(!section.getStorage().isEmpty()) {
				actualSections++;
			}
		}
		return actualSections;
	}
	
	public int getMaxUsedSections() {
		return maxUsedSections;
	}
	
}
